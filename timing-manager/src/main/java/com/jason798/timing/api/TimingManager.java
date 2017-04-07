package com.jason798.timing.api;

/**
 * timing manager
 *
 * @author JasonLiu
 */
public interface TimingManager {

    /**
     * task with specified stop condition,and max run time
     * @param task
     * @param delay
     * @param interval
     */
    boolean addFixRateCondTask(ITimingTaskCond task, Long delay, Long interval, Long runMaxTime);
    boolean addFixRateCondTask(ITimingTaskCond task, Long delay, Long interval);
    boolean addFixRateCondTask(ITimingTaskCond task, Long interval);

    /**
     * add cron task
     * @return
     */
    boolean addCronTask(String cronExpression, ITimingTask task);

    /**
     * exec cron task
     * @param tid
     */
    void execCronTask(Long tid);

    String getManagerId();
}
