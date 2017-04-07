package com.jason798.timing.task;


import com.jason798.timing.TimingCoreHelper;
import com.jason798.timing.api.ITimingTask;
import com.jason798.timing.domain.TaskEnum;

/**
 * fix rate execute task
 *
 * @author JasonLiu
 */
public class FixRateTask extends BaseTask {
    /**
     * business obj
     */
    protected ITimingTask service;

    public FixRateTask(Long tid, TimingCoreHelper helper){
        super(tid,helper);
        this.type = TaskEnum.FIXRATE;
    }

    public FixRateTask(Long tid, TimingCoreHelper helper, ITimingTask service) {
        super(tid,helper);
        this.type = TaskEnum.FIXRATE;
        this.service = service;
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
        super.after();
    }

    /**
     * ########### getter & setter ###############
     */
    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public ITimingTask getService() {
        return service;
    }

    public void setService(ITimingTask service) {
        this.service = service;
    }
    public Long getRunnedCounter() {
        return runnedCounter;
    }
    public void setRunnedCounter(Long runnedCounter) {
        this.runnedCounter = runnedCounter;
    }
}
