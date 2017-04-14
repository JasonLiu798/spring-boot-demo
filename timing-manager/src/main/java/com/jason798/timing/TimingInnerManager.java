package com.jason798.timing;

import com.jason798.common.DevContext;
import com.jason798.timing.api.TimingException;
import com.jason798.timing.api.TimingManager;
import com.jason798.timing.domain.TimingConstant;
import com.jason798.timing.task.CronTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * timing inner manager,for inner thread call
 */
@Component
public class TimingInnerManager {
    private static Logger LOG = LoggerFactory.getLogger(TimingInnerManager.class);
    @Resource
    DictService dictService;
    @Resource
    TimingDbHelper timingDbHelper;
    @Resource
    TimingManager timingManager;

    private volatile String managerStatus  = TimingConstant.MANAGER_STATUS_STOP;

    /**
     * init exeMethod,call after spring init
     */
    public void init() {
        managerStatus = TimingConstant.MANAGER_STATUS_STARTING;
        int poolSize = TimingConstant.DFT_POOL_SIZE;
        try {
            poolSize = dictService.getIntValueByPathKey(TimingConstant.POOL_SIZE_PK, TimingConstant.DFT_POOL_SIZE);
        } catch (Exception e) {
            LOG.error("get pool size error,use {}", poolSize);
            poolSize = TimingConstant.DFT_POOL_SIZE;
        }
        if (!TimingContext.buildTaskPool(poolSize)) {
            throw new TimingException("init pool fail");
        }
        //init inner threads
        if(DevContext.isTest()) {
            if (!initMonitorThread()) {
                LOG.error("init monitor thread fail");
            }
        }

        if(!initHealthThread()){
            LOG.error("init health thread fail");
        }

        managerStatus = TimingConstant.MANAGER_STATUS_STARTED;
    }


    public boolean canStart(){
        return StringCheckUtil.equal(managerStatus,TimingConstant.MANAGER_STATUS_STOP);
    }

    public boolean canStop(){
        return StringCheckUtil.equalExist(managerStatus,TimingConstant.MANAGER_STATUS_STARTED,TimingConstant.MANAGER_STATUS_STARTING);
    }


    public void shutDown(){
        TimingContext.deatroyPool();
        managerStatus = TimingConstant.MANAGER_STATUS_STOP;
    }

    public boolean taskExist(Long tid){
        return TimingContext.taskExist(tid);
    }

    /**
     * re put no incharging task to pool
     */
    public void reExecTask() {
        List<GenTask> notEndTasks = timingDbHelper.getTaskNotEndButNoManagerProcessing();

        if (CollectionUtil.isEmpty(notEndTasks)) {
            LOG.debug("no task to restart");
            return;
        }
        for (GenTask t : notEndTasks) {
            String type = t.getConfType();
            switch (type) {
                case TimingConstant.TP_CRON:
                    timingManager.execCronTask(t.getTid());
                    break;
                case TimingConstant.TP_DYN_FIXRATE_COND_PARAM:
                    timingManager.reExecDynamicTask(t.getTid());
                    break;
            }
        }
    }


    /**
     * init monitor thread
     */
    private boolean initMonitorThread() {
        return submitFixRate(TimingConstant.MONITOR_THREAD_ID,
                new MonitorTask(),
                true,//forever
                false,//param
                false,//cond
                false,//persist
                null,//param
                TimingConstant.DFT_MONITOR_DELAY,//delay
                TimingConstant.DFT_MONITOR_RATE,//interval
                -1,//max time
                0L
        );
    }

    /**
     * health task
     * 1.restart dead task thread
     * 2.update task live time
     */
    private boolean initHealthThread() {
        HealthTask ht = new HealthTask();
        ht.setTimingInnerManager(this);
        if(DevContext.isDev()){
            TimingConstant.DFT_LIVE_UPD_RATE = 60*1000L;
        }
        return submitFixRate(TimingConstant.HEALTH_THREAD_ID,
                ht,
                true,//forever
                false,//param
                false,//cond
                false,//persist
                null,//param
                TimingConstant.DFT_LIVE_UPD_DELAY,//delay
                TimingConstant.DFT_LIVE_UPD_RATE,//interval
                CommonConstant.PAGE_MAX,//max time
                0L
        );
    }


    /**
     * update task status
     *
     * @param tid
     * @param status
     */
    public void updateStatus(Long tid, String status) {
        timingDbHelper.updateTaskStatus(tid, status);
    }

    /**
     * save task execute history
     *
     * @param t
     */
    public void saveHistory(BaseTimingTask t) {
        timingDbHelper.saveHistory(t);
    }

    /**
     * is valid tid
     *
     * @param tid
     * @return
     */
    private boolean validTid(Long tid) {
        //check duplicate
        if (tid == null || TimingContext.taskExist(tid)) {
            return false;
        }
        return true;
    }

    /**
     * submit delay task to pool
     *
     * @return public boolean submitDelayTask(ITimingTask task, long delayMs) {
     * //        if (!validTask(task)) {
     * //            return false;
     * //        }
     * //        String tid = task.getTid();
     * //timingTaskHelper.
     * DelayTask innerTask = new DelayTask(tid, this, task);
     * submitDelay(innerTask, delayMs);
     * return true;
     * }
     */

    public boolean reSubmitCronTask(CronTask t) {
        submitDelay(t, t.getDelayTime(), false);
        return true;
    }


    public void updateManagerLiveTm() {
        timingDbHelper.updateManagerAlive(timingManager.getManagerId());
    }

    /**
     * batch update tasks alive time and manager
     */
    public void updateLiveTm() {
        String managerId = timingManager.getManagerId();
        List<Long> tasks = TimingContext.getTaskList();
        timingDbHelper.updateTaskLiveTm(tasks, managerId);
    }

    /**
     * submit cron task to pool
     *
     * @param cronExpression
     * @return
     * @throws ParseException
     */
    public RespDto<Long> submitCronTask(Long tid, String key, Object target, String cronExpression, long runedCnt) {
        if (!validTid(tid)) {
            return RespDto.buildFail();
        }
        Method exeMethod = getExeMethod(target, false);
        if (exeMethod == null) {
            LOG.debug("get exe method null");
            return RespDto.buildFail();
        }
        CronTask innerTask = null;
        try {
            innerTask = new CronTask(tid, this, target, exeMethod, cronExpression);
        } catch (ParseException e) {
            return RespDto.buildFail(RetCode.CRON_EXPESSION_INVALID);
        }

        innerTask.setKey(key);
        innerTask.setRunnedCounter(runedCnt);
        Long delayMs = innerTask.cron2delay();
        LOG.debug("cron task first delay {}", delayMs);
        submitDelay(innerTask, delayMs, true);
        return RespDto.buildOkLong(delayMs);
    }

    /**
     * add fix rate execute until conditon reach task
     *
     * @param delayMs
     * @param intervalMs
     * @return
     */
    public boolean submitFixRate(Long tid,
                                 Object target,
                                 boolean forever,
                                 boolean hasParam,
                                 boolean hasCond,
                                 boolean persist,
                                 String param,
                                 long delayMs,
                                 long intervalMs,
                                 long maxTime,
                                 long runedCnt) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("submit fix rate task,tid {},has param {},hasCond {},persist {},forever {},param {},delay {},interval {},maxtime {},runCnt {}", tid, hasParam, hasCond, persist, forever, param, delayMs, intervalMs, maxTime, runedCnt);
        }

        if (!validTid(tid)) {
            return false;
        }
        if (target == null) {
            return false;
        }

        Method exeMethod = getExeMethod(target, hasParam);
        if (exeMethod == null) {
            LOG.debug("get exe {} exeMethod null", hasParam);
        }

        Method condMethod = null;
        if (hasCond) {
            condMethod = ReflectUtil.getDeclaredMethod(target, TimingConstant.METHOD_COND,String.class);
            if (condMethod == null) {
                LOG.debug("get cond exeMethod null");
                return false;
            }
        }

        FixRateTask innerTask = null;
        if (forever) {
            innerTask = new FixRateTask(tid, this,
                    target, exeMethod);
        } else {
            if (hasCond) {
                innerTask = new FixRateCondTask(
                        tid, this, target, exeMethod,
                        condMethod, maxTime);
            } else {
                innerTask = new FixRateCntTask(
                        tid, this,
                        target, exeMethod, maxTime);
            }
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("new task class {}", innerTask.getClass());
        }
        if (hasParam) {
            innerTask.setHasParam(hasParam);
            innerTask.setParam(param);
        } else {
            innerTask.setHasParam(hasParam);
        }
        innerTask.setKey(String.valueOf(tid));
        innerTask.setPersist(persist);
        innerTask.setRunnedCounter(runedCnt);
        submitFixRate(innerTask, delayMs, intervalMs);
        return true;
    }

    /**
     * get execute method
     *
     * @param target
     * @param param
     * @return
     */
    private Method getExeMethod(Object target, boolean param) {
        if (param) {
            return ReflectUtil.getDeclaredMethod(target, TimingConstant.METHOD_EXECUTE, String.class);
        } else {
            return ReflectUtil.getDeclaredMethod(target, TimingConstant.METHOD_EXECUTE);
        }
    }

    /**
     * submit delay task
     *
     * @param innerTask
     * @param delayMs
     */
    private void submitDelay(BaseTimingTask innerTask, long delayMs, boolean isNew) {
        innerTask.setDelayTime(delayMs);
        ScheduledFuture future = TimingContext.executorService.schedule(innerTask, delayMs, TimeUnit.MILLISECONDS);
        innerTask.setFuture(future);
        TimingContext.addTask(innerTask.getTid(), innerTask);
        if (isNew) {
            TimingContext.incrementTaskCnt();
        }
    }

    /**
     * submit fix rate task
     *
     * @param innerTask
     * @param delayMs
     * @param interval
     */
    private boolean submitFixRate(FixRateTask innerTask, long delayMs, long interval) {
        innerTask.setDelayTime(delayMs);
        innerTask.setInterval(interval);

        if (LOG.isDebugEnabled()) {
            LOG.debug("scheduleAtFixedRate delay {},interval {}", delayMs, interval);
        }
        ScheduledFuture future = TimingContext.executorService.scheduleAtFixedRate(innerTask, delayMs, interval, TimeUnit.MILLISECONDS);

        innerTask.setFuture(future);
        TimingContext.addTask(innerTask.getTid(), innerTask);
        TimingContext.incrementTaskCnt();

        if (LOG.isDebugEnabled()) {
            LOG.debug("scheduleAtFixedRate succ,tid {},delay {},interval {}", innerTask.getTid(), delayMs, interval);
        }

        if (future == null) {
            return false;
        }
        return true;
    }

    /**
     * cancel task
     *
     * @param tid
     */
    public boolean cancelTask(Long tid) {
        return TimingContext.cancleTask(tid);
    }

    public DictService getDictService() {
        return dictService;
    }

    public void setDictService(DictService dictService) {
        this.dictService = dictService;
    }
}
