package com.jason798.timing.task;


import com.jason798.timing.TimingCoreHelper;
import com.jason798.timing.api.ITimingTask;
import com.jason798.timing.domain.TaskEnum;

/**
 * delay execute task
 *
 * @author JasonLiu
 */
public class DelayTask extends BaseTask {

    protected ITimingTask service;

    protected boolean runned = false;

    public DelayTask(Long tid, TimingCoreHelper helper) {
        super(tid,helper);
    }
    public DelayTask(Long tid, TimingCoreHelper helper, ITimingTask service) {
        super(tid,helper);
        this.type = TaskEnum.DELAY;
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
    public void after(){
        runned = true;
        runnedCounter++;
        super.after();
        removeStatus();
    }


    /**
     * ############## getter & setter #################
     */
    public void setService(ITimingTask service) {
        this.service = service;
    }
    public ITimingTask getService() {
        return service;
    }

    public boolean isRunned() {
        return runned;
    }

    public void setRunned(boolean runned) {
        this.runned = runned;
    }
}
