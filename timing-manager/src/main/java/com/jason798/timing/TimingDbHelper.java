package com.jason798.timing;


import com.jason798.comm.ApplicationContextHepler;
import com.jason798.common.DateUtil;
import com.jason798.common.SystemUtil;
import com.jason798.log.LogClient;
import com.jason798.log.LogConstant;
import com.jason798.net.IPUtil;
import com.jason798.timing.api.TimingManager;
import com.jason798.timing.domain.gen.GenTaskHistory;
import com.jason798.timing.domain.gen.GenTaskManager;
import com.jason798.timing.mapper.gen.GenTaskHistoryMapper;
import com.jason798.timing.mapper.gen.GenTaskManagerMapper;
import com.jason798.timing.mapper.gen.GenTaskMapper;
import com.jason798.timing.task.BaseTimingTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * timing db helper
 * for get DataSourceTransactionManager,TransactionStatus
 */
@Component
public class TimingDbHelper {
    private static Logger LOG = LoggerFactory.getLogger(TimingDbHelper.class);
    @Resource
    private TimingManager timingManager;
    @Resource
    private GenTaskMapper genTaskMapper;
    @Resource
    private GenTaskHistoryMapper genTaskHistoryMapper;
    @Resource
    TimingCommonHelper timingCommonHelper;
    @Resource
    TimingLockHelper timingLockHelper;
    @Resource
    TimingDbHelper timingDbHelper;
    @Resource
    private GenTaskManagerMapper genTaskManagerMapper;
    @Resource
    private TaskMapper taskMapper;

    /**
     * ################# manager ##########################
     */
    /**
     * db registe
     */
    public String registeManager() {
        Long ipLong = IPUtil.getOneRandomIpLong();
        String pid = SystemUtil.getPid();

        String id = String.format("ip-%s,pid-%s", ipLong, pid);

        DataSourceTransactionManager tm = timingDbHelper.getTransManager();
        TransactionStatus status = timingDbHelper.getDefaultTrans(tm);
        if (status == null) {
            return null;
        }
        try {
            GenTaskManager m = new GenTaskManager();
            m.setName(id);
            m.setLastUpdateTm(DateUtil.getNowTS());
            genTaskManagerMapper.insertSelective(m);
            tm.commit(status);
        } catch (Exception e) {
            tm.rollback(status);
            throw e;
        }
        return id;
    }

    /**
     * manager alive update
     *
     * @param mid
     */
    public boolean updateManagerAlive(String mid) {
        boolean res = false;
        DataSourceTransactionManager tm = timingDbHelper.getTransManager();
        TransactionStatus status = timingDbHelper.getDefaultTrans(tm);
        if (status == null) {
            return res;
        }
        try {
            GenTaskManagerExample param = new GenTaskManagerExample();
            param.createCriteria().andNameEqualTo(mid);

            GenTaskManager manager = new GenTaskManager();
            Long now = DateUtil.getNowTS();
            manager.setLastUpdateTm(now);
            int cnt = genTaskManagerMapper.updateByExampleSelective(manager, param);
            if (cnt == 1) {
                res = true;
            } else {
                res = false;
            }
            tm.commit(status);
        } catch (Exception e) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "update manager alive db exception", e);
            tm.rollback(status);
            res = false;
        }
        return res;
    }

    /**
     * ################# check method #########################
     */
    /**
     * task exist
     *
     * @param key
     * @return
     */
    public boolean taskExistByKey(String key) {
        GenTaskExample param = new GenTaskExample();
        param.createCriteria().andValidEqualTo(CommonConstant.VALID).andTkeyEqualTo(key);
        if (genTaskMapper.countByExample(param) > 0) {
            return true;
        }
        return false;
    }


    /**
     * ###################### get method ##############################
     */
    /**
     * get dynamic task by key
     *
     * @return public GenTask getTaskByKey(String key) {
     * if (StringCheckUtil.isEmpty(key)) {
     * return null;
     * }
     * GenTaskExample param = new GenTaskExample();
     * param.createCriteria().andValidEqualTo(CommonConstant.VALID).andTkeyEqualTo(key);
     * List<GenTask> l = genTaskMapper.selectByExample(param);
     * if (!CollectionUtil.isEmpty(l)) {
     * return l.get(0);
     * }
     * return null;
     * }
     */

    public List<GenTask> getTasks(GenTaskExample param) {
        return genTaskMapper.selectByExample(param);
    }


    /**
     * get task by id
     *
     * @param tid
     * @return
     */
    public TaskDomain getTaskById(Long tid) {
        return taskMapper.getTask(tid);
    }

    /**
     * get task domain list
     *
     * @param valid
     * @param showEnd
     * @return
     */
    public List<TaskDomain> getTaskDomains(String valid, String showEnd) {
        return taskMapper.getTasks(valid, showEnd);
    }

    /**
     * get task not end,but no manager processing
     * 1.valid = Y
     * 2.status != END
     * 3.now - live tm > not live interval
     *
     * @return
     */
    public List<GenTask> getTaskNotEndButNoManagerProcessing() {
        GenTaskExample param = new GenTaskExample();
        //thread live tm less than now - interval
        long aliveTmFloor = DateUtil.getNowTS() - TimingConstant.NOT_ALIVE_INTERVAL_PLUS;
        param.createCriteria()
                .andTstatusNotEqualTo(TimingConstant.STATUS_END)
                .andValidEqualTo(CommonConstant.Y);
        //.andAliveTmLessThan(aliveTm);
        List<GenTask> l = getTasks(param);
        if (CollectionUtil.isEmpty(l)) {
            return l;
        }
        Iterator<GenTask> it = l.iterator();
        while (it.hasNext()) {
            GenTask g = it.next();
            if (g.getAliveTm() != null && g.getAliveTm() > aliveTmFloor) {
                it.remove();
            }
        }
        return l;
    }

    /**
     * ###################### db insert/update methods ########################
     */
    public Long addCronTask(String key, String service, String cronExpression) {
        DataSourceTransactionManager tm = timingDbHelper.getTransManager();
        TransactionStatus status = timingDbHelper.getDefaultTrans(tm);
        if (status == null) {
            return null;
        }
        GenTask r = new GenTask();
        try {
            r.setTservice(service);
            r.setTkey(key);
            r.setTstatus(TimingConstant.STATUS_FREE);
            r.setTmutex(CommonConstant.N);
            r.setValid(CommonConstant.Y);
            r.setConfCronExpression(cronExpression);
            r.setConfType(TimingConstant.TP_CRON);
            genTaskMapper.insertSelective(r);
            tm.commit(status);
            if (r != null) {
                return r.getTid();
            } else {
                return null;
            }
        } catch (Exception e) {
            tm.rollback(status);
            return null;
        }
    }

    /**
     * add fix rate cond task to db
     * PS:must use manual transaction
     *
     * @param delay
     * @param interval
     * @param maxTime
     * @return
     */
    public Long addTask(String type, String service, String param, Long delay, Long interval, Long maxTime) {
        if (!TimingConstant.validTp(type)) {
            LOG.error("add task,type {} unspport", type);
            return null;
        }
        DataSourceTransactionManager tm = timingDbHelper.getTransManager();
        TransactionStatus status = timingDbHelper.getDefaultTrans(tm);
        if (status == null) {
            return null;
        }

        GenTask r = new GenTask();
        try {
            r.setTservice(service);
            r.setTstatus(TimingConstant.STATUS_FREE);
            r.setTmutex(CommonConstant.N);
            r.setValid(CommonConstant.Y);
            r.setConfDelayTm(delay);
            r.setConfIntervalTm(interval);
            r.setParam(param);
            r.setConfExeTimes(maxTime);
            r.setConfType(type);
            genTaskMapper.insertSelective(r);

            GenTask nr = new GenTask();
            nr.setTid(r.getTid());
            nr.setTkey(String.valueOf(r.getTid()));
            genTaskMapper.updateByPrimaryKeySelective(nr);
            tm.commit(status);
        } catch (Exception e) {
            tm.rollback(status);
        }
        if (r != null) {
            return r.getTid();
        } else {
            return null;
        }
    }

    /**
     * set task invalid
     *
     * @param tid
     * @return
     */
    public boolean setInValid(Long tid) {
        GenTask t = new GenTask();
        t.setTid(tid);
        t.setValid(CommonConstant.N);
        return updateTaskByPk(t);
    }

    /**
     * update task status
     *
     * @param tid
     * @param status
     * @return
     */
    public boolean updateTaskStatus(Long tid, String status) {
        GenTask t = new GenTask();
        t.setTid(tid);
        t.setTstatus(status);
        t.setAliveTm(DateUtil.getNowTS());
        return updateTaskByPk(t);
    }

    /**
     * update task's alive tm and processor
     *
     * @param manager
     * @param taskid
     * @return
     */
    public boolean updateTaskLiveTm(String manager, Long taskid) {
        GenTask t = new GenTask();
        t.setTid(taskid);
        t.setProcessor(manager);
        t.setAliveTm(DateUtil.getNowTS());
        return updateTaskByPk(t);
    }

    /**
     * batch update task's live time and processor
     *
     * @return
     */
    public boolean updateTaskLiveTm(List<Long> taskIds, String manager) {
        DataSourceTransactionManager tm = timingDbHelper.getTransManager();
        TransactionStatus status = timingDbHelper.getDefaultTrans(tm);
        boolean res = false;
        if (status == null) {
            return res;
        }
        //construct param
        GenTaskExample param = new GenTaskExample();
        param.createCriteria().andTidIn(taskIds).andValidEqualTo(CommonConstant.Y);
        //construct object
        GenTask t = new GenTask();
        t.setAliveTm(DateUtil.getNowTS());
        t.setProcessor(manager);
        try {
            genTaskMapper.updateByExampleSelective(t, param);
            res = true;
            tm.commit(status);
        } catch (Exception e) {
            tm.rollback(status);
        }
        return res;
    }

    /**
     * update task
     *
     * @param t
     * @return
     */
    public boolean updateTaskByPk(GenTask t) {
        DataSourceTransactionManager tm = timingDbHelper.getTransManager();
        TransactionStatus status = timingDbHelper.getDefaultTrans(tm);
        boolean res = false;
        if (status == null) {
            return res;
        }
        try {
            genTaskMapper.updateByPrimaryKeySelective(t);
            res = true;
            tm.commit(status);
        } catch (Exception e) {
            tm.rollback(status);
        }
        return res;
    }


    /**
     * ################## ts_task_history ##########################
     */
    /**
     * save execute history to db
     *
     * @param t
     */
    public boolean saveHistory(BaseTimingTask t) {
        DataSourceTransactionManager tm = timingDbHelper.getTransManager();
        TransactionStatus status = timingDbHelper.getDefaultTrans(tm);
        boolean res = false;
        if (status == null) {
            return res;
        }
        try {
            GenTaskHistory h = new GenTaskHistory();
            h.setEndTm(t.getLastStopTime());
            h.setStartTm(t.getLastStartTime());
            h.setExeCnt(t.getRunnedCounter());
            h.setExeStatus(String.valueOf(t.isLastExeSucc()));
            h.setProcessor(timingManager.getManagerId());
            h.setTid(t.getTid());
            genTaskHistoryMapper.insertSelective(h);
            tm.commit(status);
            res = true;
        } catch (Exception e) {
            tm.rollback(status);
            res = false;
        }
        return res;
    }

    /**
     * ##################### db helper #########################
     */
    /**
     * get transaction manager
     *
     * @return
     */
    public DataSourceTransactionManager getTransManager() {
        DataSourceTransactionManager transactionManager = null;
        try {
            transactionManager = ApplicationContextHepler.getBean(
                    "transactionManager", DataSourceTransactionManager.class);
            return transactionManager;
        } catch (Exception e1) {
            LogClient.writeError(LogConstant.MODULE_TIMING, "get transaction manager error", e1);
        }
        return null;
    }

    /**
     * get serial transaction status
     *
     * @return
     */
    public TransactionStatus getDefaultTrans(DataSourceTransactionManager tm) {
        return getTransactionStatus(tm, TransactionDefinition.ISOLATION_DEFAULT);
    }

    /**
     * get serial transaction status
     *
     * @return
     */
    public TransactionStatus getSerialTrans(DataSourceTransactionManager tm) {
        return getTransactionStatus(tm, TransactionDefinition.ISOLATION_SERIALIZABLE);
    }


    /**
     * get transaction status
     *
     * @param tm
     * @param level
     * @return
     */
    public TransactionStatus getTransactionStatus(DataSourceTransactionManager tm, int level) {
        if (tm == null) {
            return null;
        }
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        def.setIsolationLevel(level);
        return tm.getTransaction(def);
    }

}
