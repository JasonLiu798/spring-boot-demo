package com.jason798.timing.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * timing manager constant
 *
 * @author JasonLiu
 */
public class TimingConstant {

    /**
     * ##################### default value ########################
     */
    /**
     * default task max run time
     */
    public static final Long DFT_MAX_RUN_TIME =  10L;

    /**
     * monitor
     */
    public static final Long MONITOR_THREAD_ID = -1L;

    /**
     * thread pool dft size
     */
    public static final int DFT_POOL_SIZE = 10;
    public static final String POOL_SIZE_PK = "BaseSetting.performance.schedualpoolsize";

    /**
     * monitor interval (ms)
     */
    public static final long DFT_MONITOR_RATE = 1000;
    public static final long DFT_MONITOR_DELAY = 1000;

    /**
     * #################### time associate #####################
     */
    /**
     * not alive interval,unit second
     */
    public static final Long NOT_ALIVE_INTERVAL = 10*60L+5L;

    /**
     * mutex interval
     */
    public static final Long MUTEX_INTERVAL = 10L;

    /**
     * task status
     * Free,Iinitial,Waiting,Executing,enD
     * F->I->W->E->D/W
     */
    public static final String STATUS_FREE = "F";//free,no processor
    public static final String STATUS_INITIAL = "I";//starting
    public static final String STATUS_WAITING = "W";//waiting
    public static final String STATUS_EXECUTING = "E";//executing
    public static final String STATUS_END = "D";//end

    /**
     * starting
     */
    public static final String COL_MUTEX = "MUTEX";
    public static final String COL_MUTEX_TM = "MUTEX_TM";

    /**
     * cron expression task
     */
    public static final String TP_CRON = "CR";

    /**
     * specified time and specified interval,execute until condition reach or reach the target num
     * in use
     * delay (interval) -> do -> check yes -> check reach max time no ->delay(interval) ...
     *                                     -> check reach max time yes -> stop
     *                        -> check no  -> stop
     */
    public static final String TP_COND_INTERVAL_MAX_TIME = "CVN";


    /**
     *
     */
    public static final Set<String> TP_SET =new HashSet<>();




    /**
     * task execute max time,1 hour
     * if over 1 hour,task will be terminate
     */
    public static final Long MAX_EXE_TM = 60*60*2L;

    /**
     * init task type
     */
    static {
        TP_SET.add(TP_CRON);
        TP_SET.add(TP_COND_INTERVAL_MAX_TIME);

    }

    /**
     * is valid task type
     * @param tp
     * @return
     */
    public static boolean validTp(String tp){
        return TP_SET.contains(tp);
    }


    /**
     * specified time and specified interval
     * in use
     *
     * 1.unit is year,month
     * use scheduleWithFixedDelay,calc next exe time ,reput to pool
     *
     * 2.unit is day,week,minute,second
     * use scheduleAtFixedRate,
     * delay (tgtTm - now)-> do (check 次数->stop) -> delay(tgtTm - now) -> do ....
     */
    public static final String TP_TM_INTERVAL = "TV";

    /**
     * specified time run once
     */
    public static final String TP_TM = "T";

    /**
     * fix rate
     */
    public static final String TP_INTERVAL= "V";
    /**
     */
    public static final String TP_DELAY_INTERVAL = "DV";
    /**
     */
    public static final String TP_DELAY_INTERVAL_CNT = "DVN";




}
