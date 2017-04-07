package com.jason798.timing.task;


import com.jason798.timing.TimingCoreHelper;
import com.jason798.timing.api.ITimingTaskCond;
import com.jason798.timing.domain.TaskEnum;

/**
 * fix rate until reach the condition
 *
 * @author JasonLiu
 */
public class FixRateCondTask extends FixRateCntTask {

    public FixRateCondTask(Long tid, TimingCoreHelper helper, ITimingTaskCond service, Long maxTime) {
        super(tid,helper);
        this.type = TaskEnum.FIXRATECOND;
        this.service = service;
        this.maxTime = maxTime;
    }

    /**
     * buziness obj
     */
    private ITimingTaskCond service;

    @Override
    public void before() {
        super.before();
    }

    @Override
    public void execute() {
        service.execute();
    }

    /**
     * check condition reach
     */
    @Override
    public void after() {
        System.out.println("FixRateCondTask after bf");
        super.after();
        System.out.println("FixRateCondTask after af");
        if(!this.end) {
            if (service.cond()) {
                removeStatus();
                timingCoreHelper.cancelTask(tid);
                this.end = true;
            }
        }
        //LOG.de
    }

    public boolean getCond(){
        return service.cond();
    }

    /**
     * ################## getter & setter ################################
     */
    public ITimingTaskCond getService() {
        return service;
    }

    public void setService(ITimingTaskCond service) {
        this.service = service;
    }
}
