package com.jason798.timing.api;

/**
 * return code
 * @author JasonLiu
 */
public enum RetCode {
    /**
     * execute succ
     */
    SUCCESS,

    HAS_LOCK,

    UNKNOWN,

    ADD_CRON_POOL_FAIL,
    ADD_CRON_POOL_EXCEPTION,

    STILL_ALIVE,



    RE_ADD_DYN_TASK_TO_POOL_FAIL,
    RE_ADD_DYN_TASK_TO_POOL_EXCEPTION,

    ADD_CRON_TO_POOL_FAIL,
    ADD_CRON_TO_POOL_EXCEPTION,

    /**
     * db
     */
    ADD_DYN_TASK_DB_FAIL,
    ADD_CRON_DB_FAIL,



    /**
     * lock
     */
    GET_LOCK_FAIL,



    /**
     * status associate
     */
    STATUS_CANNOT_START,

    DB_TASK_NULL,
    TASK_PARAM_NULL,

    /**
     * bean associate
     */
    BEAN_NOT_EXIST,
    GET_BEAN_FAIL,
    GET_BEAN_NULL,

    //run cnt reach max
MAX_CNT_NULL,
    RUN_CNT_REACH_MAX_CNT,

    HAS_PARAM_NOT_IMPLEMENT_INF,
    NO_PARAM_NOT_IMPLEMENT_INF,
    HAS_COND_NOT_IMPLEMENT_INF,

    //got cond,must has param, ICond cond(String xxx)
    HAS_COND_BUT_NOT_HAS_PARAM,

    /**
     * init schedule pool fail
     */
    INIT_POOL_FAIL,
    CRON_EXPESSION_INVALID,
    UPD_CRON_RESTART_FAIL,
    TASK_NOT_IN_THE_POOL,
    CANCEL_TASK_FAIL,

   TASK_STOP_OR_INVALID

}
