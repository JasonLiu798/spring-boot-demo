package com.jason798.timing.task;


import com.jason798.timing.TimingCoreHelper;
import com.jason798.timing.api.ITimingTask;
import com.jason798.timing.domain.TaskEnum;

/**
 * fix rate execute N times task
 *
 * @author JasonLiu
 */
public class FixRateCntTask extends FixRateTask {
    protected Long maxTime = 1L;

    public FixRateCntTask(Long tid, TimingCoreHelper helper){
        super(tid,helper);
    }
    public FixRateCntTask(Long tid, TimingCoreHelper helper, ITimingTask service, Long maxTime) {
        super(tid,helper);
        this.type = TaskEnum.FIXRATECNT;
        this.service = service;
        this.maxTime = maxTime;
    }

    @Override
    public void before() {
        super.before();
    }

    @Override
    public void execute() {
        service.execute();
    }

    @Override
    public void after() {
        System.out.println("FixRateCntTask end bf");
        super.after();
        System.out.println("FixRateCntTask end af "+runnedCounter+","+maxTime);
        if(runnedCounter == maxTime){
            timingCoreHelper.cancelTask(tid);
            this.end = true;
            return;
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
