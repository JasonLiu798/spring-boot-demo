package com.jason798.timing.task;

import com.jason798.timing.TimingInnerManager;
import com.jason798.timing.api.ITimingTaskParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * when task execute timeout,stop the task
 */
public class TimeOutTask implements ITimingTaskParam {
    private static Logger LOG = LoggerFactory.getLogger(TimeOutTask.class);

    /**
     * need manual set
     */
    private TimingInnerManager timingInnerManager;


    @Override
    public void execute(String param) {
        Long tid = Long.parseLong(param);
        if(tid==null){

        }else{
        }
    }
}
