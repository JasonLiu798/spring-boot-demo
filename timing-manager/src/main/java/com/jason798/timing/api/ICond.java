package com.jason798.timing.api;

/**
 * condition task
 *
 * @author JasonLiu
 */
public interface ICond {
    /**
     * return true,task stop
     */
    boolean cond(String param);
}
