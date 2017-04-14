package com.jason798.timing;

import com.jason798.character.StringCheckUtil;
import com.jason798.comm.ApplicationContextHepler;
import com.jason798.common.CommonConstant;
import com.jason798.common.DateUtil;
import com.jason798.common.ReflectUtil;
import com.jason798.log.LogClient;
import com.jason798.log.LogConstant;
import com.jason798.number.RandomUtil;
import com.jason798.timing.api.*;
import com.jason798.timing.domain.CronExpression;
import com.jason798.timing.domain.TaskDomain;
import com.jason798.timing.domain.TimingConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * common functions
 */
@Component
public class TimingCommonHelper {
    private static Logger LOG = LoggerFactory.getLogger(TimingCommonHelper.class);
    @Resource
    private TimingLockHelper timingLockHelper;

    public boolean validCronExpression(String cronExpression) {
        try {
            new CronExpression(cronExpression);
        } catch (ParseException e1) {
            return false;
        }
        return true;
    }

    public boolean isStopOrInvalid(TaskDomain t) {
        String valid = t.getValid();
        String status = t.getTstatus();
        if (StringCheckUtil.isEmpty(valid)
                || StringCheckUtil.equal(valid, CommonConstant.N)
                || StringCheckUtil.isEmpty(status)
                || StringCheckUtil.equal(status, TimingConstant.STATUS_END)
                ) {
            return true;
        }
        return false;
    }

    /**
     * @param t
     * @return
     */
    public AddTaskParam db2param(TaskDomain t) {
        if (t == null) {
            return AddTaskParam.buildFailNull();
        }
        if (isStopOrInvalid(t)) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "check task,task stop or invalid");
            return AddTaskParam.buildFailStopOrInvalid();
        }
        AddTaskParam p = new AddTaskParam();
        TimingConstant.validTp(t.getConfType());
        switch (t.getConfType()) {
            case TimingConstant.TP_CRON:
                p.setForever(true);
                p.setCron(true);
                p.setCronExpression(t.getConfCronExpression());
                break;
            case TimingConstant.TP_DYN_FIXRATE_COND_PARAM:
                p.setHasParam(true);
                p.setParam(t.getParam());
                p.setHasCnt(true);
                p.setMaxTime(t.getConfExeTimes());
                p.setHasCond(true);
                p.setInterval(t.getConfIntervalTm());
                p.setDelay(t.getConfDelayTm());
                p.setRunedCnt(t.getRunCnt());
                break;
            default:
                LogClient.writeError(LogConstant.MODULE_TIMING, "task type unsupport,actual conf type:" + t.getConfType());
                return null;
        }
        p.setStatus(t.getTstatus());
        p.setService(t.getTservice());
        p.setAliveTm(t.getAliveTm());
        return p;
    }

    /**
     * check task can process
     *
     * @param t
     * @return
     */
    public RetCode checkTaskCanProcess(TaskDomain t) {
        if (t == null) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "gen task null");
            return RetCode.DB_TASK_NULL;
        }
        AddTaskParam p = db2param(t);
        if (p.isFail()) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "trans form db task to param null");
            return p.getFailReason();
        }
        return checkTaskCanProcess(p);
    }

    /**
     * check task can process
     *
     * @param param
     * @return
     */
    public RetCode checkTaskCanProcess(AddTaskParam param) {
        if (param == null) {
            return RetCode.TASK_PARAM_NULL;
        }

        //check service exist
        String beanName = param.getService();
        Object target = null;
        try {
            target = ApplicationContextHepler.getBeanByName(beanName);
            if (target == null) {
                LogClient.writeError(LogConstant.MODULE_TIMING, "task service clz " + beanName + " not exist");
                return RetCode.BEAN_NOT_EXIST;
            }
        } catch (Exception e) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "task service clz " + beanName + " not exist", e);
            return RetCode.GET_BEAN_FAIL;
        }

        if (param.isCron()) {
            //check cron
            if(!validCronExpression(param.getCronExpression())){
                return RetCode.CRON_EXPESSION_INVALID;
            }
        } else {
            //check delay
            if (param.getDelay() <= 0) {
                param.setDelay(TimingConstant.DFT_MONITOR_DELAY);
            }

            //check interval
            //if(!param.isForce()) {
            if (param.getInterval() < TimingConstant.MIN_INTERVAL) {
                param.setInterval(TimingConstant.MIN_INTERVAL);
            }

            //if cond,check implement ICond
            if (param.isHasCond()) {
                if (!param.isHasParam()) {
                    //if use cond,must has param
                    return RetCode.HAS_COND_BUT_NOT_HAS_PARAM;
                }
                if (!ReflectUtil.chkAImplementB(target, ICond.class)) {
                    return RetCode.HAS_COND_NOT_IMPLEMENT_INF;
                }
            }

            //check run count reach max cnt or not
            if (param.isHasCnt()) {
                Long actualRunCnt = param.getRunedCnt();
                if (param.getMaxTime() == null) {
                    return RetCode.MAX_CNT_NULL;
                }
                if (actualRunCnt != null && actualRunCnt >= param.getMaxTime()) {
                    return RetCode.RUN_CNT_REACH_MAX_CNT;
                }
            }
        }

        //check implement interface
        if (param.isHasParam()) {
            //check target implement execute(String )
            if (!ReflectUtil.chkAImplementB(target, ITimingTaskParam.class)) {
                return RetCode.HAS_PARAM_NOT_IMPLEMENT_INF;
            }
        } else {
            //check target implement execute()
            if (!ReflectUtil.chkAImplementB(target, ITimingTask.class)) {
                return RetCode.NO_PARAM_NOT_IMPLEMENT_INF;
            }
        }

        //check is in mutex
        if (timingLockHelper.hasLock(param.getMutexTm())) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "check task ,task locking");
            return RetCode.HAS_LOCK;
        }

        //no lock,check status valid,check alive time,is some manager incharging
        if (TimingConstant.validCanExeStatus(param.getStatus())) {
            Long now = DateUtil.getNowTS();
            Long aliveTm = param.getAliveTm();
            if (aliveTm == null) {
                return RetCode.SUCCESS;
            }
            Long interval = now - aliveTm;
            if (LOG.isDebugEnabled()) {
                LOG.debug("check task interval {},compare to {}", interval, TimingConstant.NOT_ALIVE_INTERVAL_PLUS);
            }
            if (interval > TimingConstant.NOT_ALIVE_INTERVAL_PLUS) {
                return RetCode.SUCCESS;
            }else{
                return RetCode.STILL_ALIVE;
            }
        } else {
            return RetCode.STATUS_CANNOT_START;
        }
    }

    /**
     * generate random tie
     *
     * @return
     */
    public String genRandomTid() {
        return RandomUtil.generateMD5();
    }

}
