package com.jason798.timing;

import com.jason798.collection.CollectionUtil;
import com.jason798.json.JSONFastJsonUtil;
import com.jason798.log.LogClient;
import com.jason798.log.LogConstant;
import com.jason798.timing.domain.TimingManagerStatusDto;
import com.jason798.timing.task.BaseTimingTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

public class TimingContext {
    private static Logger LOG = LoggerFactory.getLogger(TimingContext.class);

    public static void clearAll() {
        LogClient.writeWarn(LogConstant.MODULE_TIMING, "before clear timing context,CNT:" + getRunningTaskCnt() + ",taskMap:" + JSONFastJsonUtil.objectToJson(taskMap));
        taskCount = new AtomicInteger();
        executorService = null;
        taskMap = new ConcurrentHashMap<>();
    }
    /**
     * ################### for monitor ######################
     */
    /**
     * running task count
     */
    private static AtomicInteger taskCount = new AtomicInteger();

    /**
     * incr /desc running task count
     */
    public static void incrementTaskCnt() {
        taskCount.incrementAndGet();
    }

    public static void decrementTaskCnt() {
        taskCount.decrementAndGet();
    }

    public static int getRunningTaskCnt() {
        return taskCount.get();
    }


    /**
     * #################### core struct ######################
     */
    /**
     * core schedule pool
     */
    static ScheduledExecutorService executorService;

    /**
     * build schedule pool
     *
     * @param poolsize
     */
    public static synchronized boolean buildTaskPool(int poolsize) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("init task pool,size {}", poolsize);
        }
        executorService = Executors.newScheduledThreadPool(poolsize);
        if (executorService == null) {
            return false;
        }
        return true;
    }

    /**
     * destroy pool
     *
     * @return
     */
    public static synchronized boolean deatroyPool() {
        List<Runnable> rlist = executorService.shutdownNow();
        LogClient.writeError(LogConstant.MODULE_TIMING, "manual shutdown task", JSONFastJsonUtil.objectToJson(rlist));
        return true;
    }

    /**
     * cancel task
     *
     * @param tid task id
     * @return
     */
    public static synchronized boolean cancleTask(Long tid) {
        ScheduledFuture future = getFuture(tid);
        if (future == null) {
            return false;
        }
        boolean cancelRes = future.cancel(true);
        if (cancelRes) {
            TimingContext.removeTask(tid);
        } else {
            LogClient.writeError(LogConstant.MODULE_TIMING, "call task future cancel fail,tid " + tid );
        }
        return cancelRes;
    }


    /**
     * for get task
     */
    private static Map<Long, BaseTimingTask> taskMap = new ConcurrentHashMap<>();

    public static boolean taskExist(Long tid) {
        return taskMap.containsKey(tid);
    }

    public static void addTask(Long tid, BaseTimingTask task) {
        taskMap.put(tid, task);
    }

    public static BaseTimingTask getTask(Long tid) {
        return taskMap.get(tid);
    }

    /**
     * remove task from map
     *
     * @param tid
     * @return
     */
    public static BaseTimingTask removeTask(Long tid) {
        BaseTimingTask t = null;
        if (tid != null) {
            t = taskMap.remove(tid);
        } else {
            LOG.error("remove task fail tid null");
        }
        decrementTaskCnt();
        return t;
    }

    /**
     * get futrue for cancel
     *
     * @param tid
     * @return
     */
    public static ScheduledFuture getFuture(Long tid) {
        BaseTimingTask t = getTask(tid);
        if (t != null) {
            return t.getFuture();
        }
        return null;
    }

    public static Set<Long> getTasks() {
        return taskMap.keySet();
    }

    public static List<Long> getTaskList() {
        Set<Long> tasks = taskMap.keySet();
        if (CollectionUtil.isEmpty(tasks)) {
            return null;
        }
        List<Long> res = new ArrayList<>(tasks.size());
        for (Long t : tasks) {
            res.add(t);
        }
        return res;
    }


    /**
     * monitor message for web
     *
     * @return
     */
    public static TimingManagerStatusDto getTaskStatusDto() {
        TimingManagerStatusDto timingManagerStatusDto = new TimingManagerStatusDto();
        timingManagerStatusDto.setTaskCount(taskCount.intValue());

        if (CollectionUtil.isEmpty(taskMap)) {
            return timingManagerStatusDto;
        }
        for (Map.Entry<Long, BaseTimingTask> entry : taskMap.entrySet()) {
            timingManagerStatusDto.addTaskStatus(entry.getValue());
        }
        return timingManagerStatusDto;
    }

    /**
     * monitor message for console
     *
     * @return
     */
    public static String getStatusFmt(boolean json) {
        TimingManagerStatusDto timingManagerStatusDto = getTaskStatusDto();
        if (json) {
            return timingManagerStatusDto.fmtJson();
        } else {
            return timingManagerStatusDto.fmt();
        }
    }

}
