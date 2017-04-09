package com.jason798.timing;

import com.jason798.timing.api.TimingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * timing mansger
 */
@Component
public class TimingManagerImpl implements TimingManager {
    private static Logger LOG = LoggerFactory.getLogger(TimingManagerImpl.class);
    @Resource
    private TimingCommonHelper timingCommonHelper;
    @Resource
    private TimingTaskHelper timingTaskHelper;
    @Resource
    private TimingCoreHelper timingCoreHelper;

    /**
     * unique id
     */
    private String managerId;
    private Long id;

    public TimingManagerImpl() {
    }

    /**
     * init
     */
    public void init() {
        String managerId = timingCommonHelper.registe();
        this.managerId = managerId;
        timingCoreHelper.init();
    }

    /**
     * submit fix rate task, execute until condition reach,or execute maxtime
     *
     * @param task
     * @param delay
     * @param interval
     * @param runMaxTime
     * @return
     */
    @Override
    public boolean addFixRateCondTask(ITimingTaskCond task, Long delay, Long interval, Long runMaxTime) {
        //db insert
        Long tid = timingTaskHelper.addFixRateCondTask(delay, interval, runMaxTime);
        //submit to pool
        if (!timingCoreHelper.submitFixRateCond(tid, task, delay, interval, runMaxTime)) {
            LogClient.writeError(TimingManager.class.getSimpleName(), "submit fix rate cond task fail");
            return false;
        }
        return true;
    }

    @Override
    public boolean addFixRateCondTask(ITimingTaskCond task, Long delay, Long interval) {
        return addFixRateCondTask(task, delay, interval, TimingConstant.DFT_MAX_RUN_TIME);
    }

    @Override
    public boolean addFixRateCondTask(ITimingTaskCond task, Long interval) {
        return addFixRateCondTask(task, 0L, interval);
    }


    @Override
    public boolean addCronTask(String cronExpression, ITimingTask task) {

        return false;
    }

    /**
     * when server restart
     * exec not finish task
     *
     * @param tid
     */
    public void execFixRateCondTask(Long tid) {
        GenTask rawTask = timingTaskHelper.getTaskById(tid);
    }

    /**
     * exec cron task
     *
     * @param tid
     */
    public void execCronTask(Long tid) {
        GenTask rawTask = timingTaskHelper.getTaskById(tid);
        //check exist
        if (rawTask == null) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "cron task " + id + " notexist");
        }

        //check type
        if (!StringCheckUtil.equal(rawTask.getConfType(), String.valueOf(TaskEnum.CRON))) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "task " + id + " not cron");
            return;
        }

        //pre check can process
        if (!timingTaskHelper.checkTaskCanProcess(rawTask)) {
            LogClient.writeInfo(TimingTaskHelper.class.getSimpleName(), "task cannot start " + JSONFastJsonUtil.objectToJson(rawTask));
        }


        //get execute service
        ITimingTask task = null;
        try {
            task = (ITimingTask) AOSApplicationContextHepler.getBeanByName(rawTask.getServiceName());
        } catch (Exception e) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "get cron service fail", e);
            return;
        }
        if (task == null) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "get service fail,get null");
            return;
        }

        if(LOG.isDebugEnabled()) {
            LOG.debug("can execute cron task {}", tid );
        }
        //submit to pool
        boolean lock = timingTaskHelper.lock(tid);
        if (lock) {
            try {
                RespDto<Long> submitRes = timingCoreHelper.submitCronTask(tid, rawTask.getTkey(), task, rawTask.getConfCronExpression());
                if(submitRes.isSucc()){
					timingTaskHelper.updateTaskStatus(tid, TimingConstant.STATUS_WAITING);
				}else{
                	LogClient.writeError(LogConstant.MODULE_TIMING,"submit cron task fail,tid "+tid);
				}
            } catch (ParseException e) {
                LogClient.writeError(LogConstant.MODULE_TIMING, "cron task init fail", e);
                return;
            } finally {
                timingTaskHelper.unlock(tid);
            }
        } else {
            return;
        }
    }
    
    
    

    /**
     * 测试用 静态方法 方便调用
     *
     public static ScheduledFuture fixRate4test(FixRateTask task, long dealy, long interval) {
     TimingManager timingManager = null;
     try {
     timingManager = AOSApplicationContextHepler.getBean("timingManager", TimingManager.class);
     return timingManager.fixRate(task, dealy, interval);
     } catch (Exception e) {
     LogClient.writeError(TimingManager.class, "schedule fix rate static error ", e);
     return null;
     }
     }
     */

    /**
     * 测试用 静态方法 方便调用
     *
     public static void delay4test(DelayTask runnable, long ms) {
     TimingManager timingManager = null;
     try {
     timingManager = AOSApplicationContextHepler.getBean("timingManager", TimingManager.class);
     } catch (Exception e) {
     e.printStackTrace();
     }
     timingManager.delay(runnable, ms);
     }
     */


    /**
     * 测试用 生成 实例
     *
     * @return
     *
    public static TimingManager build4test() {
    TimingManager tm = new TimingManager();
    tm.init();
    return tm;
    }*/


    /**
     * ##################### getter & setter #####################
     */
    public TimingCommonHelper getTimingCommonHelper() {
        return timingCommonHelper;
    }

    public void setTimingCommonHelper(TimingCommonHelper timingCommonHelper) {
        this.timingCommonHelper = timingCommonHelper;
    }

    public TimingTaskHelper getTimingTaskHelper() {
        return timingTaskHelper;
    }

    public void setTimingTaskHelper(TimingTaskHelper timingTaskHelper) {
        this.timingTaskHelper = timingTaskHelper;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimingCoreHelper getTimingCoreHelper() {
        return timingCoreHelper;
    }

    public void setTimingCoreHelper(TimingCoreHelper timingCoreHelper) {
        this.timingCoreHelper = timingCoreHelper;
    }
}
