package com.jason798.timing;

import com.jason798.timing.api.TimingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * timing mansger
 * todo:timing clear invalid,end task
 * todo:clear time-out task
 * todo:test restart task
 */
@Component
public class TimingManagerImpl implements TimingManager {
    private static Logger LOG = LoggerFactory.getLogger(TimingManagerImpl.class);
    @Resource
    private TimingCommonHelper timingCommonHelper;
    @Resource
    private TimingInnerManager timingInnerManager;
    @Resource
    private TimingLockHelper lockHelper;
    @Resource
    private TimingDbHelper timingDbHelper;

    /**
     * unique id
     */
    private String managerId;

    public TimingManagerImpl() {
    }

    /**
     * init core pool
     */
    public void init() {
        String managerId = timingDbHelper.registeManager();
        if (StringCheckUtil.isEmpty(managerId)) {
            LogClient.writeError(
                    LogConstant.MODULE_TIMING,
                    "registe timing manager fail" + managerId);
            return;
        }
        this.managerId = managerId;
        timingInnerManager.init();
    }

    /**
     * add dynamic fix rate cond task with param
     *
     * @return
     */
    @Override
    public RetCode addDynamicTask(AddTaskParam param) {
        RetCode chkRet = timingCommonHelper.checkTaskCanProcess(param);
        if (chkRet != RetCode.SUCCESS) {
            return chkRet;
        }

        Object target = getTarget(param.getService());
        if (target == null) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "get target null");
            return RetCode.GET_BEAN_NULL;
        }

        //db insert
        //String type,String service,String param,Long delay, Long interval, Long maxTime
        Long tid = timingDbHelper.addTask(String.valueOf(TaskEnum.FIXRATECONDPARAM), param.getService(), param.getParam(), param.getDelay(), param.getInterval(), param.getMaxTime());
        if (tid == null) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "insert task to db fail");
            return RetCode.ADD_DYN_TASK_DB_FAIL;
        }

        //submit to pool
        boolean subRes = timingInnerManager.submitFixRate(tid,
                target,
                param.isForever(),//forever
                param.isHasParam(),//param
                param.isHasCond(),//cond
                true,//persist
                param.getParam(),//exe param
                param.getDelay(), param.getInterval(),
                param.getMaxTime(),
                param.getRunedCnt()
        );

        if (subRes) {
            LOG.debug("submit dynamic task succ,tid {},service {}", tid, param.getService());
            return RetCode.SUCCESS;
        } else {
            timingDbHelper.setInValid(tid);
            LogClient.writeError(TimingManager.class.getSimpleName(), "submit dynamic task fail,tid " + tid + ",service " + param.getService());
            return RetCode.RE_ADD_DYN_TASK_TO_POOL_FAIL;
        }
    }

    /**
     * add a cron task
     *
     * @return
     */
    @Override
    public RetCode addCronTask(AddTaskParam param) {
        RetCode checkRet = timingCommonHelper.checkTaskCanProcess(param);
        if (checkRet != RetCode.SUCCESS) {
            return checkRet;
        }

        Object target = getTarget(param.getService());
        if (target == null) {
            return RetCode.GET_BEAN_FAIL;
        }

        //db insert
        //String type,String service,String param,Long delay, Long interval, Long maxTime
        Long tid = timingDbHelper.addCronTask(param.getTkey(), param.getService(), param.getCronExpression());
        if (tid == null) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "insert cron task to db fail");
            return RetCode.ADD_CRON_DB_FAIL;
        }

        //submit to pool
        //String key, Object target, String cronExpression
        RespDto<Long> subRes = timingInnerManager.submitCronTask(
                tid,
                param.getTkey(),
                target,
                param.getCronExpression(),
                0L
        );
        if (subRes.isSucc()) {
            LOG.debug("submit cron task succ,tid {},service {}", tid, param.getService());
            return RetCode.SUCCESS;
        } else {
            timingDbHelper.setInValid(tid);
            LogClient.writeError(LogConstant.MODULE_TIMING, "submit dynamic task fail,tid " + tid + ",service " + param.getService());
            return RetCode.ADD_CRON_POOL_FAIL;
        }
    }

    /**
     * update cron task
     *
     * @param tid
     * @param cronExpression
     * @return
     */
    @Override
    public RetCode updateCronTask(Long tid, String cronExpression) {
        if (timingInnerManager.taskExist(tid)) {//check exist in this manager
            if (!timingCommonHelper.validCronExpression(cronExpression)) {//check valid expression
                return RetCode.CRON_EXPESSION_INVALID;
            }
            //cancel task
            boolean cancelRes = timingInnerManager.cancelTask(tid);
            if (cancelRes) {
                //reexecute
                GenTask t = new GenTask();
                t.setTid(tid);
                t.setConfCronExpression(cronExpression);
                timingDbHelper.updateTaskByPk(t);
                return execCronTask(tid);
            } else {
                return RetCode.CANCEL_TASK_FAIL;
            }
        }
        return RetCode.TASK_NOT_IN_THE_POOL;
    }


    /**
     * exec cron task
     *
     * @param tid
     */
    public RetCode execCronTask(Long tid) {
        TaskDomain rawTask = timingDbHelper.getTaskById(tid);
        //pre check can process
        RetCode chkRet = timingCommonHelper.checkTaskCanProcess(rawTask);
        if (chkRet != RetCode.SUCCESS) {
            LogClient.writeInfo(LogConstant.MODULE_TIMING, "cron task cannot start " + JSONFastJsonUtil.objectToJson(rawTask));
            return chkRet;
        }

        //get execute service
        Object target = getTarget(rawTask.getTservice());
        if (target == null) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "rexec task fail,target null,service " + rawTask.getTservice());
            return RetCode.GET_BEAN_NULL;
        }

        Long runCnt = rawTask.getRunCnt();
        if (runCnt == null) {
            runCnt = 0L;
        }

        //submit to pool
        boolean lock = lockHelper.lock(tid);
        if (lock) {
            try {
                RespDto<Long> submitRes = timingInnerManager.submitCronTask(
                        tid, rawTask.getTkey(),
                        target, rawTask.getConfCronExpression(),
                        runCnt
                );
                if (submitRes.isSucc()) {
                    timingDbHelper.updateTaskStatus(tid, TimingConstant.STATUS_WAITING);
                    return RetCode.SUCCESS;
                } else {
                    LogClient.writeError(LogConstant.MODULE_TIMING, "submit cron task fail,tid " + tid);
                    return RetCode.ADD_CRON_TO_POOL_FAIL;
                }
            } catch (Exception e) {
                LogClient.writeError(LogConstant.MODULE_TIMING, "cron task init fail", e);
                return RetCode.ADD_CRON_TO_POOL_EXCEPTION;
            } finally {
                lockHelper.unlock(tid);
            }
        } else {
            return RetCode.GET_LOCK_FAIL;
        }
    }

    /**
     * get target service bean from spring
     *
     * @param service
     * @return
     */
    private Object getTarget(String service) {
        Object target = null;
        try {
            target = AOSApplicationContextHepler.getBeanByName(service);
        } catch (Exception e) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "get target service exception", e);
        }
        return target;
    }

    /**
     * re exe dynamic task
     *
     * @param tid
     */
    @Override
    public RetCode reExecDynamicTask(Long tid) {
        //get raw task from db
        TaskDomain rawTask = timingDbHelper.getTaskById(tid);

        //pre check,before lock
        RetCode checkRet = timingCommonHelper.checkTaskCanProcess(rawTask);
        if (checkRet != RetCode.SUCCESS) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "rexec task fail,check ret " + checkRet + ",task " + JSONFastJsonUtil.objectToJson(rawTask));
            return checkRet;
        }

        //get target
        Object target = getTarget(rawTask.getTservice());
        if (target == null) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "rexec task fail,target null,service " + rawTask.getTservice());
            return RetCode.GET_BEAN_NULL;
        }

        long runCount = 0L;
        if (rawTask.getRunCnt() != null) {
            runCount = rawTask.getRunCnt();
        }

        //lock
        boolean lockRes = lockHelper.lock(tid);
        if (lockRes) {
            try {
                //submit to pool
                boolean subRes = timingInnerManager.submitFixRate(tid,
                        target,
                        false,//forever
                        true,//param
                        true,//cond
                        true,//persist
                        rawTask.getParam(),//exe param
                        rawTask.getConfDelayTm(), rawTask.getConfIntervalTm(), rawTask.getConfExeTimes(),
                        runCount
                );

                if (subRes) {
                    LOG.debug("re exe dynamic task succ,tid {},service {}", tid, rawTask.getTservice());
                    //todo:if delay = 0,dead lock
                    timingDbHelper.updateTaskLiveTm(getManagerId(), tid);
                    return RetCode.SUCCESS;
                } else {
                    LogClient.writeError(LogConstant.MODULE_TIMING, "re exe dynamic task fail,tid " + tid + ",service " + rawTask.getTservice());
                    return RetCode.RE_ADD_DYN_TASK_TO_POOL_FAIL;
                }
            } catch (Exception e) {
                LogClient.writeError(LogConstant.MODULE_TIMING, "submit fix rate task exception", e);
                return RetCode.RE_ADD_DYN_TASK_TO_POOL_EXCEPTION;
            } finally {
                lockHelper.unlock(tid);
            }
        } else {
            return RetCode.GET_LOCK_FAIL;
        }
    }

    /**
     * manual stop all task
     * @return
     */
    @Override
    public boolean manualStopAll() {
        if (timingInnerManager.canStop()) {
            timingInnerManager.shutDown();
            return true;
        }
        return false;
    }

    /**
     * manual start pool
     * @return
     */
    @Override
    public boolean manualStart() {
        if (timingInnerManager.canStart()) {
            TimingContext.clearAll();
            timingInnerManager.init();
        }
        return false;
    }

    /**
     * manual restart pool
     * @return
     */
    @Override
    public boolean restart() {
        if (timingInnerManager.canStop()) {
            TimingContext.deatroyPool();
            TimingContext.clearAll();
            timingInnerManager.init();
        }
        return false;
    }

    /**
     * ###################### db #############################
     */

    /**
     * get task list from db
     *
     * @param valid
     * @param showEnd
     * @return
     */
    @Override
    public List<TaskDomain> getTaskList(String valid, String showEnd) {
        valid = StringEscapeUtils.escapeHtml4(valid);
        showEnd = StringEscapeUtils.escapeHtml4(showEnd);
        return timingDbHelper.getTaskDomains(valid, showEnd);
    }

    /**
     * get status
     * @return
     */
    @Override
    public String getStatus() {
        return TimingContext.getStatusFmt(true);
    }

    /**
     * ##################### getter & setter #####################
     */
    public TimingCommonHelper getTimingCommonHelper() {
        return timingCommonHelper;
    }

    public void setTimingCommonHelper(TimingCommonHelper timingCommonHelper) {
        this.timingCommonHelper = timingCommonHelper;
    }

    public String getManagerId() {
        return managerId;
    }


    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public TimingLockHelper getLockHelper() {
        return lockHelper;
    }

    public void setLockHelper(TimingLockHelper lockHelper) {
        this.lockHelper = lockHelper;
    }

    public TimingDbHelper getTimingDbHelper() {
        return timingDbHelper;
    }

    public void setTimingDbHelper(TimingDbHelper timingDbHelper) {
        this.timingDbHelper = timingDbHelper;
    }
}


