package com.jason798.timing.api;


import com.jason798.timing.domain.TaskDomain;

import java.util.List;

/**
 * timing manager
 *
 * @author JasonLiu
 */
public interface TimingManager {
    /**
     * init
     */
    void init();

    /**
     * task with specified stop condition,and max run time,with param
     *
     * @return
     */
    RetCode addDynamicTask(AddTaskParam param);

    /**
     * dynamic add cron task
     *
     * @return
     */
    RetCode addCronTask(AddTaskParam param);

    /**
     * @param tid
     * @param cronExpression
     * @return
     */
    RetCode updateCronTask(Long tid, String cronExpression);

    /**
     * exec cron task
     *
     * @param tid
     */
    RetCode execCronTask(Long tid);

    /**
     * re exec dynamic task
     *
     * @param tid
     */
    RetCode reExecDynamicTask(Long tid);

    /**
     * get unique id
     *
     * @return
     */
    String getManagerId();

    /**
     * #################### db operation ######################
     */
    List<TaskDomain> getTaskList(String valid, String showEnd);

    /**
     * #################### sys operations ####################
     */

    String getStatus();

    /**
     * manual stop pool
     */
    boolean manualStopAll();

    /**
     * manual start pool
     */
    boolean manualStart();

    /**
     * restart pool
     */
    boolean restart();


}
