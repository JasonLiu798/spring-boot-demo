package com.jason798.timing.task;


import com.jason798.log.LogClient;
import com.jason798.timing.TimingContext;
import com.jason798.timing.TimingCoreHelper;
import com.jason798.timing.domain.TaskEnum;

import java.util.concurrent.ScheduledFuture;

/**
 * task base class
 *
 * @author JasonLiu
 */
public abstract class BaseTask implements Runnable {
    /**
     * ################# basis ##################
     */
    /**
     * task db pk
     */
    protected Long tid;
    /**
     * task key
     */
    protected String key;
    /**
     * task type
     */
    protected TaskEnum type;
    /**
     * for cancel
     */
    protected ScheduledFuture future;
    /**
     * timing core helper
     */
    protected TimingCoreHelper timingCoreHelper;

    /**
     * ################ timing #####################
     */
    /**
     * first delay time
     */
    protected long delayTime = 0;
    /**
     * interval time
     */
    protected long interval = 0;

    /**
     * ############### status #####################
     */
    /**
     * is running
     */
    protected volatile boolean running = false;
    /**
     * last start time
     */
    protected long lastStartTime = 0;
    /**
     * last stop time
     */
    protected long lastStopTime = 0;
    /**
     * submit to pool time
     */
    protected long submitTm = 0;
    /**
     * runed counter
     */
    protected Long runnedCounter=0L;
    /**
     * last run success
     */
    protected boolean lastExeSucc = true;
    /**
     * is persist to db
     * inner thread not persist
     */
    protected boolean persist = true;
    /**
     * is totally end
     */
    protected boolean end = false;

    /**
     * constructor
     *
     * @param tid
     */
    public BaseTask(Long tid, TimingCoreHelper helper) {
        this.tid = tid;
        this.timingCoreHelper = helper;
    }

    /**
     * run before
     * for sys
     */
    public void before() {
        running = true;
        lastStartTime = System.currentTimeMillis();
        lastExeSucc = true;
    }

    abstract public void execute();

    /**
     * for runnable
     */
    @Override
    public void run(){
        before();
        try {
            execute();
        } catch (Exception e) {
            LogClient.writeError(BaseTask.class, "task execute error", e);
            lastExeSucc = false;
        }
        after();
    }

    /**
     * run after
     */
    public void after() {
        lastStopTime = System.currentTimeMillis();
        running = false;
        runnedCounter++;
        if(persist) {
            timingCoreHelper.saveHistory(this);//persist history
        }
        System.out.println("basetask after "+tid);
    }

    /**
     * remove status
     */
    public BaseTask removeStatus() {
        return TimingContext.removeTask(tid);
    }


    /**
     * ################### getter & setter ################################
     */
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public TaskEnum getType() {
        return type;
    }

    public void setType(TaskEnum type) {
        this.type = type;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public long getLastStartTime() {
        return lastStartTime;
    }

    public void setLastStartTime(long lastStartTime) {
        this.lastStartTime = lastStartTime;
    }

    public long getLastStopTime() {
        return lastStopTime;
    }

    public void setLastStopTime(long lastStopTime) {
        this.lastStopTime = lastStopTime;
    }

    public ScheduledFuture getFuture() {
        return future;
    }

    public boolean isLastExeSucc() {
        return lastExeSucc;
    }

    public void setLastExeSucc(boolean lastExeSucc) {
        this.lastExeSucc = lastExeSucc;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public long getSubmitTm() {
        return submitTm;
    }

    public void setSubmitTm(long submitTm) {
        this.submitTm = submitTm;
    }

    public TimingCoreHelper getTimingCoreHelper() {
        return timingCoreHelper;
    }

    public void setTimingCoreHelper(TimingCoreHelper timingCoreHelper) {
        this.timingCoreHelper = timingCoreHelper;
    }

    public Long getRunnedCounter() {
        return runnedCounter;
    }

    public void setRunnedCounter(Long runnedCounter) {
        this.runnedCounter = runnedCounter;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public boolean isPersist() {
        return persist;
    }

    public void setPersist(boolean persist) {
        this.persist = persist;
    }

    public void setFuture(ScheduledFuture future) {
        this.future = future;
    }
}
