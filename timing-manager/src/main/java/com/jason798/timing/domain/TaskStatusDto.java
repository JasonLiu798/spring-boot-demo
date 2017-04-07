package com.jason798.timing.domain;

import java.io.Serializable;

/**
 * task status dto
 *
 * @author JasonLiu
 */
public class TaskStatusDto implements Serializable {
    private Long tid;
    private String tkey;

    private String type;

    private String lastStartTm;
    private String lastStopTm;

    private long startStopInterval;
    private Boolean runned;

    /**
     * runned counter,for fix rate and cron
     */
    private Long runnedCounter;

    private Long maxRunCount;

    /**
     * is running
     */
    private boolean running;

    /**
     * delay
     */
    private long delay;
    /**
     * interval
     */
    private long interval;
    /**
     * cond status
     */
    private Boolean cond;


    public TaskStatusDto(){
    }

    public Long getMaxRunCount() {
        return maxRunCount;
    }

    public void setMaxRunCount(Long maxRunCount) {
        this.maxRunCount = maxRunCount;
    }

    public Boolean getCond() {
        return cond;
    }

    public void setCond(Boolean cond) {
        this.cond = cond;
    }

    public Boolean getRunned() {
        return runned;
    }

    public Long getRunnedCounter() {
        return runnedCounter;
    }

    public void setRunnedCounter(Long runnedCounter) {
        this.runnedCounter = runnedCounter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLastStartTm() {
        return lastStartTm;
    }

    public void setLastStartTm(String lastStartTm) {
        this.lastStartTm = lastStartTm;
    }

    public String getLastStopTm() {
        return lastStopTm;
    }

    public void setLastStopTm(String lastStopTm) {
        this.lastStopTm = lastStopTm;
    }

    public long getStartStopInterval() {
        return startStopInterval;
    }

    public void setStartStopInterval(long startStopInterval) {
        this.startStopInterval = startStopInterval;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTkey() {
        return tkey;
    }

    public void setTkey(String tkey) {
        this.tkey = tkey;
    }

    public Boolean isRunned() {
        return runned;
    }

    public void setRunned(Boolean runned) {
        this.runned = runned;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }
}
