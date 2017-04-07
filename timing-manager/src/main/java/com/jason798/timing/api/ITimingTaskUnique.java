package com.jason798.timing.api;

/**
 * simple task for delay,fix rate
 *
 * @author JasonLiu
 */
public interface ITimingTaskUnique extends ITimingTask {
    /**
     * unique key
     *
     * @return
     */
    String getKey();
}
