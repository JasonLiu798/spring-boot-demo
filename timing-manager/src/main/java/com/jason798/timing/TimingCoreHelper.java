package com.jason798.timing;

import com.jason798.timing.api.TimingException;
import com.jason798.timing.domain.TimingConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * core schedule pool
 */
@Component
public class TimingCoreHelper {
    private static Logger LOG = LoggerFactory.getLogger(TimingCoreHelper.class);

    @Resource
    DictService dictService;
    @Resource
    TimingTaskHelper timingTaskHelper;

    /**
     * init method,call after spring init
     */
    public void init() {
        int poolSize = TimingConstant.DFT_POOL_SIZE;
        try {
            poolSize = dictService.getIntValueByPathKey(TimingConstant.POOL_SIZE_PK, TimingConstant.DFT_POOL_SIZE);
        } catch (Exception e) {
            LOG.error("get pool size error,use {}", poolSize);
        }
        if (!TimingContext.buildTaskPool(poolSize)) {
            throw new TimingException("init pool fail");
        }
        initMonitor();
    }

    /**
     * init monitor thread
     */
    private void initMonitor() {
        FixRateTask task = MonitorTask.build(this);
        if (!submitFixRate(task, task.getDelayTime(), task.getInterval())) {
            LogClient.writeError(TimingCoreHelper.class.getSimpleName(), "monitor thread submit fail");
        }
    }


    public void saveHistory(BaseTask t) {
        timingTaskHelper.saveHistory(t);
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

    /*
    public boolean submitDelayTask(ITimingTask task, long delayMs) {
        return submitDelayTask(task,delayMs,true);
    }*/

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


    /**
     * submit cron task to pool
     *
     * @param task
     * @param cronExpression
     * @return
     * @throws ParseException
     */
    public boolean submitCronTask(Long tid, String key, ITimingTask task, String cronExpression) throws ParseException {
        if (!validTid(tid)) {
            return false;
        }
        CronTask innerTask = new CronTask(tid, this, task, cronExpression);
        innerTask.setKey(key);
        Long delayMs = innerTask.cron2delay();
        LOG.debug("cron task first delay {}", delayMs);
        submitDelay(innerTask, delayMs, true);
        return true;
    }


    /**
     * add fix rate task
     *
     * @param task
     * @param delayMs
     * @param intervalMs
     * @return
     *
    public boolean submitFixRate(ITimingTask task, long delayMs, long intervalMs) {
    if (!validTask(task)) {
    return false;
    }
    String tid = task.getTid();
    FixRateTask innerTask = new FixRateTask(tid, this, task);
    submitFixRate(innerTask, delayMs, intervalMs);
    return true;
    }*/

    /**
     * add fix rate execute until conditon reach task
     *
     * @param task
     * @param delayMs
     * @param intervalMs
     * @return
     */
    public boolean submitFixRateCond(Long tid, ITimingTaskCond task, long delayMs, long intervalMs, long maxTime) {
        if (!validTid(tid)) {
            return false;
        }
        if (task == null) {
            return false;
        }

        FixRateCondTask innerTask = new FixRateCondTask(tid, this, task, maxTime);
        innerTask.setKey(String.valueOf(tid));
        submitFixRate(innerTask, delayMs, intervalMs);
        return true;
    }


    /**
     * submit delay task
     *
     * @param innerTask
     * @param delayMs
     */
    private void submitDelay(BaseTask innerTask, long delayMs, boolean isNew) {
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
