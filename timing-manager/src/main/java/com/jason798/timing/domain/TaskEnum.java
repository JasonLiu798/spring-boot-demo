package com.jason798.timing.domain;

/**
 * task type
 *
 * @author JasonLiu
 */
public enum TaskEnum {
    /**
     * delay execute once
     */
    DELAY,
    /**
     * cron execute
     */
    CRON,
    /**
     * execute run at fix rate
     */
    FIXRATE,
    /**
     * execute run at fix rate,run N times
     */
    FIXRATECNT,
    /**
     * execute run at fix rate until condition reach
     */
    FIXRATECOND,
    FIXRATECONDPARAM
}
