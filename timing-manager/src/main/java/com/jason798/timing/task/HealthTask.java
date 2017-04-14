package com.jason798.timing.task;

import com.jason798.common.SystemUtil;
import com.jason798.timing.TimingInnerManager;
import com.jason798.timing.api.ITimingTask;
import com.jason798.timing.domain.TimingConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * update alive time
 */
public class HealthTask implements ITimingTask {
    private static Logger LOG = LoggerFactory.getLogger(HealthTask.class);

    /**
     * need manual set
     */
    private TimingInnerManager timingInnerManager;

    @Override
    public void execute() {
        //sleep random time,0~5s,avoid other thread

        //SystemUtil.sleepRandom(TimingConstant.SLEEP_RAND_MAX_TM, TimingConstant.SLEEP_DFT_TM);
        if(LOG.isDebugEnabled()) {
            LOG.debug(HealthTask.class+" start to run");
        }
        //find dead not execute task and re execute
        //todo:remove task execute over cnount
        timingInnerManager.reExecTask();
        //update live tm
        timingInnerManager.updateLiveTm();
        //update manager live tm
        timingInnerManager.updateManagerLiveTm();
    }


    /**
     * ############### getter & setter ##################
     */
    public TimingInnerManager getTimingInnerManager() {
        return timingInnerManager;
    }

    public void setTimingInnerManager(TimingInnerManager timingInnerManager) {
        this.timingInnerManager = timingInnerManager;
    }
}
