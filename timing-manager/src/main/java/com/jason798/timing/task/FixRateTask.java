package com.jason798.timing.task;


import com.jason798.timing.TimingInnerManager;
import com.jason798.timing.domain.TaskEnum;

import java.lang.reflect.Method;

/**
 * fix rate execute task
 *
 * @author JasonLiu
 */
public class FixRateTask extends BaseTimingTask {
    /**
     * constructor
     *
     * @param tid
     * @param helper
     * @param target
     * @param method
     */
    public FixRateTask(Long tid, TimingInnerManager helper, Object target, Method method) {
        super(tid, helper, target, method);
        this.type = TaskEnum.FIXRATE;
    }

}
