package com.jason798.timing.task;


import com.jason798.log.LogClient;
import com.jason798.timing.api.ITimingTask;
import com.jason798.timing.domain.TaskEnum;

/**
 * fix rate execute task
 *
 * @author JasonLiu
 */
public class FixRateTask extends BaseTask {
    /**
     * fix rate interval
     */
    protected long interval;

    public FixRateTask(String tid){
        super(tid);
        this.type = TaskEnum.FIXRATE;
    }

    public FixRateTask(String tid, ITimingTask service) {
        super(tid);
        this.type = TaskEnum.FIXRATE;
        this.service = service;
    }

    /**
     * buz obj
     */
    private ITimingTask service;

    @Override
    public void before() {
        super.before();
    }

    @Override
    public void run() {
        before();
        try {
            service.execute();
        } catch (Exception e) {
            LogClient.writeError(FixRateTask.class,"timing task run exception", e);
        }
        after();
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
}
