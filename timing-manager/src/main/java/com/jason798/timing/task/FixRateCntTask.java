package com.jason798.timing.task;


import com.jason798.timing.TimingInnerManager;
import com.jason798.timing.domain.TaskEnum;

import java.lang.reflect.Method;

/**
 * fix rate execute N times task
 *
 * @author JasonLiu
 */
public class FixRateCntTask extends FixRateTask {
    protected Long maxTime = 1L;

    /**
     * constructor
     *
     * @param tid
     * @param helper
     * @param target
     * @param method
     */
    public FixRateCntTask(Long tid, TimingInnerManager helper, Object target, Method method) {
        super(tid, helper, target, method);
    }

    public FixRateCntTask(Long tid, TimingInnerManager helper, Object target, Method exe, Long maxTime) {
        super(tid,helper,target,exe);
        this.type = TaskEnum.FIXRATECNT;
        this.maxTime = maxTime;
    }

    @Override
    public void after() {
        super.after();
        if(runnedCounter == maxTime){
            this.end = true;
        }
    }

    /**
     * ########### getter & setter ###############
     */
    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }
}
