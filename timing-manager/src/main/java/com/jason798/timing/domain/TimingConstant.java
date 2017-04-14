package com.jason798.timing.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * timing manager constant
 *
 * @author JasonLiu
 */
public class TimingConstant {

    public static final String MANAGER_STATUS_STOP = "P";
    public static final String MANAGER_STATUS_STARTING = "I";
    public static final String MANAGER_STATUS_STARTED = "S";

    /**
     * ##################### default value ########################
     */
    /**
     * default task max run time
     */
    public static final Long DFT_MAX_RUN_TIME =  10L;

    public static final long DFT_DELAY = 1000;

    /**
     * monitor
     */
    public static final Long MONITOR_THREAD_ID = -1L;
    public static final Long HEALTH_THREAD_ID = -2L;
    public static final int SLEEP_RAND_MAX_TM = 5000;
    public static final int SLEEP_DFT_TM = 3000;

    /**
     * thread pool dft size
     */
    public static final int DFT_POOL_SIZE = 10;
    public static final String POOL_SIZE_PK = "BaseSetting.performance.schedualpoolsize";

    /**
     * not alive interval,unit second
     * test 1min
     * prd 30min
     */
    public static Long NOT_ALIVE_INTERVAL = 60L;
    public static final Long NOT_ALIVE_INTERVAL_PLUS = NOT_ALIVE_INTERVAL+5L;


    /**
     * monitor interval (ms)
     */
    public static final long DFT_MONITOR_RATE = 1000;
    public static final long DFT_MONITOR_DELAY = 1000;


    /**
     * live update interval (ms)
     */
    public static long DFT_LIVE_UPD_RATE = NOT_ALIVE_INTERVAL*1000L;
    public static final long DFT_LIVE_UPD_DELAY = 5000;

    /**
     * reflect methods
     */
    public static final String METHOD_EXECUTE = "execute";
    public static final String METHOD_COND = "cond";

    /**
     * #################### time associate #####################
     */

    /**
     * mutex interval
     */
    public static final Long MUTEX_INTERVAL = 10L;

    public static final Long MIN_INTERVAL = 1000L;

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
    public static final Set<String> STATUS_SET = new HashSet<>();
    public static final Set<String> CAN_EXE_STATUS_SET = new HashSet<>();

    /**
     * starting
     */
    public static final String COL_MUTEX = "TMUTEX";
    public static final String COL_MUTEX_TM = "MUTEX_TM";

    public static final String COL_STATUS = "TSTATUS";
    public static final String COL_ALIVE_TM = "ALIVE_TM";


    /**
     * #################### task type ########################
     */
    /**
     * cron expression task
     */
    public static final String TP_CRON = "CRON";
    //String.valueOf(TaskEnum.CRON);

    /**
     * specified time and specified interval,execute until condition reach or reach the target num
     * in use
     * delay (interval) -> do -> check yes -> check reach max time no ->delay(interval) ...
     *                                     -> check reach max time yes -> stop
     *                        -> check no  -> stop
     */
    public static final String TP_DYN_FIXRATE_COND_PARAM =
            "FIXRATECONDPARAM";//String.valueOf(TaskEnum.FIXRATECONDPARAM);

    /**
     * now support all type set
     */
    public static final Set<String> TP_SET =new HashSet<>();
    public static final Set<String> TP_DYN_SET =new HashSet<>();

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
        TP_SET.add(TP_DYN_FIXRATE_COND_PARAM);

        TP_DYN_SET.add(TP_DYN_FIXRATE_COND_PARAM);

        STATUS_SET.add(STATUS_FREE);
        STATUS_SET.add(STATUS_INITIAL);
        STATUS_SET.add(STATUS_WAITING);
        STATUS_SET.add(STATUS_EXECUTING);
        STATUS_SET.add(STATUS_END);

        CAN_EXE_STATUS_SET.add(STATUS_FREE);
        CAN_EXE_STATUS_SET.add(STATUS_INITIAL);
        CAN_EXE_STATUS_SET.add(STATUS_WAITING);
        CAN_EXE_STATUS_SET.add(STATUS_EXECUTING);
    }

    /**
     * is valid task type
     * @param tp
     * @return
     */
    public static boolean validTp(String tp){
        return TP_SET.contains(tp);
    }
    public static boolean validDynTp(String tp){
        return TP_DYN_SET.contains(tp);
    }

    /**
     * is valid status
     * @param st
     * @return
     */
    public static boolean validStatus(String st){
        return STATUS_SET.contains(st);
    }
    public static boolean validCanExeStatus(String st){
        return CAN_EXE_STATUS_SET.contains(st);
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
     *
    public static final String TP_TM_INTERVAL = "TV";*/

    /**
     * specified time run once
     *
    public static final String TP_TM = "T";*/

    /**
     * fix rate
     *
    public static final String TP_INTERVAL= "V";*/
//    public static final String TP_DELAY_INTERVAL = "DV";
//    public static final String TP_DELAY_INTERVAL_CNT = "DVN";

}
