package com.jason798.timing;

import com.jason798.character.StringCheckUtil;
import com.jason798.collection.CollectionUtil;
import com.jason798.comm.ApplicationContextHepler;
import com.jason798.common.CommonConstant;
import com.jason798.common.DateUtil;
import com.jason798.log.LogClient;
import com.jason798.log.LogConstant;
import com.jason798.timing.api.TimingManager;
import com.jason798.timing.domain.TaskEnum;
import com.jason798.timing.domain.TimingConstant;
import com.jason798.timing.domain.gen.GenTask;
import com.jason798.timing.domain.gen.GenTaskExample;
import com.jason798.timing.domain.gen.GenTaskHistory;
import com.jason798.timing.mapper.gen.GenTaskHistoryMapper;
import com.jason798.timing.mapper.gen.GenTaskMapper;
import com.jason798.timing.task.BaseTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * db task associate functions
 */
@Component
public class TimingTaskHelper {
	private static Logger LOG = LoggerFactory.getLogger(TimingTaskHelper.class);
	@Resource
	private TimingManager timingManager;
	@Resource
	private GenTaskMapper genTaskMapper;
	@Resource
	private GenTaskHistoryMapper genTaskHistoryMapper;
	@Resource
	TimingCommonHelper timingHelper;
	
	
	/**
	 * add fix rate cond task to db
	 *
	 * @param delay
	 * @param interval
	 * @param maxTime
	 * @return
	 */
	@Transactional
	public Long addFixRateCondTask(Long delay, Long interval, Long maxTime) {
		GenTask r = new GenTask();
		r.setStatus(TimingConstant.STATUS_FREE);
		r.setMutex(CommonConstant.N);
		r.setValid(CommonConstant.Y);
		r.setConfDelayTm(delay);
		r.setConfIntervalTm(interval);
		r.setConfExeTimes(maxTime);
		r.setConfType(String.valueOf(TaskEnum.FIXRATECOND));
		genTaskMapper.insertSelective(r);
		
		GenTask nr = new GenTask();
		nr.setTid(r.getTid());
		nr.setTkey(String.valueOf(r.getTid()));
		genTaskMapper.updateByPrimaryKeySelective(nr);
		return r.getTid();
	}
	
	/**
	 * save execute history to db
	 *
	 * @param t
	 */
	public void saveHistory(BaseTask t) {
		GenTaskHistory h = new GenTaskHistory();
		h.setEndTm(t.getLastStopTime());
		h.setStartTm(t.getLastStartTime());
		h.setExeCnt(t.getRunnedCounter());
		h.setExeStatus(String.valueOf(t.isLastExeSucc()));
		h.setProcessor(timingManager.getManagerId());
		h.setType(String.valueOf(t.getType()));
		h.setTid(t.getTid());
		genTaskHistoryMapper.insertSelective(h);

	}
	
	/**
	 * task exist
	 *
	 * @param key
	 * @return
	 *
	public boolean taskExist(String key) {
	GenDynTask param = new GenTaskExample();
	param.createCriteria().andValidEqualTo(CommonConstant.VALID).andTkeyEqualTo(key);
	if (genTaskMapper.countByExample(param) > 0) {
	return true;
	}
	return false;
	}*/
	
	/**
	 * get dynamic task by key
	 *
	 * @param key
	 * @return
	 */
	public GenTask getTaskByKey(String key) {
		if (StringCheckUtil.isEmpty(key)) {
			return null;
		}
		GenTaskExample param = new GenTaskExample();
		param.createCriteria().andValidEqualTo(CommonConstant.Y).andTkeyEqualTo(key);
		List<GenTask> l = genTaskMapper.selectByExample(param);
		if (!CollectionUtil.isEmpty(l)) {
			return l.get(0);
		}
		return null;
	}
	
	
	public GenTask getTaskById(Long tid) {
		
		return genTaskMapper.selectByPrimaryKey(tid);
	}
	
	
	/**
	 * start task
	 */
	public void startCronTask(Long id) {


        /*
		switch (tp) {
            case TimingConstant.TP_CRON:
                //helper.submitCronTask(t,cron);
                break;
            case TimingConstant.TP_TM_INTERVAL:
                //计算 下次执行时间
                Long now = DateUtil.getNowTS();

                /*
                if (exeTm < now) {
                    //todo: 下次执行时间小于当前时间
                    //判断是否


                } else {

                    Long nextStartTm = exeTm - now;

                    //判断是否已经有进程执行

                    Long delaySec = DateUtil.getIntervalSec(now, unit, intervalTm);//计算下次执行时间
                    switch (unit) {
                        //case
                    }

                }

                //rawTask.get);
        }*/
		
		
	}
	
	/**
	 * data:in memory
	 * check task can process,before call startTaskPre
	 * Free,Iinitial,Waiting,Executing,End
	 * <p>
	 * Free status,can direct start
	 * <p>
	 * Initialing status,check alive and mutexTm
	 * Waiting status,check alive and mutexTm
	 * Executing status,check last start Tm and mutexTm
	 * <p>
	 * End status,@false no process
	 *
	 * @return
	 */
	public boolean checkTaskCanProcess(GenTask task) {
		if (!TimingConstant.validTp(task.getConfType())) {
			return false;
		}
		
		String bean = task.getServiceName();
		try {
			Object obj = ApplicationContextHepler.getBeanByName(bean);
			if (obj == null) {
				LogClient.writeError(TimingTaskHelper.class.getSimpleName(), "task service clz " + bean + " not exist");
				return false;
			}
		} catch (Exception e) {
			LogClient.writeError(TimingTaskHelper.class, "task service clz " + bean + " not exist", e);
			return false;
		}
		
		String status = task.getStatus();
		//free
		if (StringCheckUtil.equal(status, TimingConstant.STATUS_FREE)) {
			return true;
		}
		
		// is in mutex
		if (hasLock(task.getMutex(), task.getMutexTm())) {
			return false;
		}
		
		//no lock,check is some manager incharging
		Long now = DateUtil.getNowTS();
		Long aliveTm = task.getAliveTm();
		if (StringCheckUtil.equalExist(status,
				TimingConstant.STATUS_INITIAL,
				TimingConstant.STATUS_WAITING,
				TimingConstant.STATUS_EXECUTING
		)) {
			Long interval = now - aliveTm;
			if (interval > TimingConstant.NOT_ALIVE_INTERVAL) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * is having lock
	 *
	 * @param mutex
	 * @param mutexTm
	 * @return
	 */
	private boolean hasLock(String mutex, Long mutexTm) {
		Long now = DateUtil.getNowTS();
		if (StringCheckUtil.equal(mutex,
				CommonConstant.Y)) {
			Long mutexInterval = now - mutexTm;
			if (mutexInterval < TimingConstant.MUTEX_INTERVAL) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * is lock
	 * @return
	 */
	private boolean isLock(String mutex,Long mutexTm) {
		Long now = DateUtil.getNowTS();
		Long mutexInterval = now - mutexTm;
		if ((StringCheckUtil.equal(mutex, CommonConstant.Y) && mutexInterval < TimingConstant.MUTEX_INTERVAL)) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * update task status
	 * @param tid
	 * @param status
	 * @return
	 */
	public boolean updateTaskStatus(Long tid,String status){
		GenTask t = new GenTask();
		t.setTid(tid);
		t.setStatus(status);
		t.setAliveTm(DateUtil.getNowTS());
		return  updateTaskByPk(t);
	}
	
	/**
	 * update task
	 * @param t
	 * @return
	 */
	public boolean updateTaskByPk(GenTask t){
		boolean res = false;
		DataSourceTransactionManager tm = getTransManager();
		if (tm == null) {
			return res;
		}
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		TransactionStatus status = tm.getTransaction(def);
		try {
			//5.具体的数据库操作（多个）
			genTaskMapper.updateByPrimaryKeySelective(t);
			res = true;
			tm.commit(status);
		} catch (Exception e) {
			tm.rollback(status);
		}
		return res;
	}
	
	/**
	 * get transaction manager
	 * @return
	 */
	private DataSourceTransactionManager getTransManager() {
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
	 * start task,set processing Y
	 * pre: task exist
	 * todo: add timer, stop dead lock
	 */
	public boolean lock(Long tid) {
		boolean res = false;
		long curtid = Thread.currentThread().getId();
		
		//start trans
		DataSourceTransactionManager transactionManager = getTransManager();
		if (transactionManager == null) {
			return res;
		}
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		def.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
		TransactionStatus transactionStatus = transactionManager.getTransaction(def);
		
		DataSource ds = transactionManager.getDataSource();
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "select MUTEX,MUTEX_TM from ts_task where TID=? for update";
		String updateSql = "update ts_task set MUTEX = 'Y',MUTEX_TM=? where TID = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, tid);
			ResultSet rs = pstmt.executeQuery();
			String mutex = null;
			Long mutexTm = 0L;
			while (rs.next()) {
				mutex = rs.getString(TimingConstant.COL_MUTEX);
				mutexTm = rs.getLong(TimingConstant.COL_MUTEX_TM);
			}
			rs.close();
			
			if (mutexTm == null) {
				mutexTm = 0L;
			}
			
			Long now = DateUtil.getNowTS();
			Long mutexInterval = now - mutexTm;
			if (LOG.isDebugEnabled()) {
				LOG.debug("get tid {},mutex {},mutex tm {},interval {}", tid, mutex, mutexTm, mutexInterval);
			}
			
			if ((StringCheckUtil.equal(mutex, CommonConstant.Y) && mutexInterval < TimingConstant.MUTEX_INTERVAL)) {
				//other thread get lock,do nothing
				if(LOG.isDebugEnabled()) {
					LOG.debug("get lock fail,tid " + curtid);
				}
				return res;
			} else {
				//no body processing
				//LOG.debug("exec update sql " + updateSql);
				
				pstmt = conn.prepareStatement(updateSql);
				pstmt.setLong(1, now);
				pstmt.setLong(2, tid);
				boolean updateRes = pstmt.execute();
				if(LOG.isDebugEnabled()) {
					LOG.debug("get lock succ,tid " + curtid + ",res " + updateRes);
				}
			}
			return true;
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			LogClient.writeError(TimingManagerImpl.class, "execute start processing trans exception ", e);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					LogClient.writeError(LogConstant.MODULE_TIMING,"close pstms error ", e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LogClient.writeError(LogConstant.MODULE_TIMING,"close conn error ", e);
				}
			}
			transactionManager.commit(transactionStatus);
		}
		return res;
	}
	
	/**
	 * unlock
	 *
	 * @param tid
	 * @return
	 */
	public boolean unlock(Long tid) {
		boolean res = false;
		long curtid = Thread.currentThread().getId();
		
		DataSourceTransactionManager transactionManager = getTransManager();
		
		if (transactionManager == null) {
			return false;
		}
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		def.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		TransactionStatus transactionStatus = transactionManager.getTransaction(def);
		
		DataSource ds = transactionManager.getDataSource();
		Connection conn = null;
		PreparedStatement ustmt = null;
		Long now = DateUtil.getNowTS();
		String updateSql = "update ts_task set MUTEX = 'N',MUTEX_TM=? where TID = ?";
		try {
			conn = ds.getConnection();
			ustmt = conn.prepareStatement(updateSql);
			
			LOG.debug("execsql " + updateSql);
			ustmt = conn.prepareStatement(updateSql);
			ustmt.setLong(1, now);
			ustmt.setLong(2, tid);
			boolean updateRes = ustmt.execute();
			LOG.debug("thread processing " + curtid + ",res " + updateRes);
			return true;
		} catch (Exception e) {
			transactionManager.rollback(transactionStatus);
			LogClient.writeError(TimingManagerImpl.class, "execute start processing trans exception ", e);
		} finally {
			if (ustmt != null) {
				try {
					ustmt.close();
				} catch (SQLException e) {
					LogClient.writeError(LogConstant.MODULE_TIMING,"close pstms error ", e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LogClient.writeError(LogConstant.MODULE_TIMING,"close conn error ", e);
				}
			}
			transactionManager.commit(transactionStatus);
		}
		return res;
	}
	
}
