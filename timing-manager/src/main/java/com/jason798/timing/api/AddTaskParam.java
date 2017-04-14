package com.jason798.timing.api;


import com.jason798.common.DateUtil;
import com.jason798.timing.domain.TimingConstant;

/**
 * add task param
 */
public class AddTaskParam {

    private Long tid;
    private String tkey;
    /**
     * service
     */
    private String service;
    /**
     * options
     */
    // has param
    private boolean hasParam = false;
    // has cond
    private boolean hasCond = false;
    // has cnt
    private boolean hasCnt = false;
    // is cron
    private boolean cron = false;
    // run forever
    private boolean forever = false;
    //mutex
    private boolean mutex = false;

    /**
     * params
     */
    // delay
    private long delay;
    //interval
    private long interval;
    // max time
    private Long maxTime;
    //parameter
    private String param;

    //mutex tm
    private Long mutexTm;
    //status
    private String status;

    private Long aliveTm;

    private String cronExpression;

    private Long runedCnt= 0L;

    private boolean force = false;

    private RetCode failReason = RetCode.SUCCESS;

    public boolean isSucc(){
        if(failReason==RetCode.SUCCESS){
            return true;
        }
        return false;
    }
    public boolean isFail(){
        return !isSucc();
    }

    /**
     * build FixRateCondTaskParam
     * @param service
     * @param paramStr
     * @param maxTime
     * @param delay
     * @param interval
     * @return
     */
    public static AddTaskParam buildNewFixRateCntConditionParam(String service, String paramStr, Long maxTime, Long delay, Long interval) {
        AddTaskParam param = new AddTaskParam();
        param.setService(service);
        param.setMaxTime(maxTime);
        param.setDelay(delay);
        param.setInterval(interval);
        param.setHasCond(true);
        param.setHasParam(true);
        param.setParam(paramStr);
        param.setStatus(TimingConstant.STATUS_FREE);
        param.setAliveTm(DateUtil.getNowTS());
        param.setRunedCnt(0L);
        return param;
    }

    /**
     * build FixRateCntTaskParam
     * @param service
     * @param paramStr
     * @param maxTime
     * @param delay
     * @param interval
     * @return
     */
    public static AddTaskParam buildNewFixRateCntParam(String service, String paramStr, Long maxTime, Long delay, Long interval) {
        AddTaskParam param = new AddTaskParam();
        param.setService(service);
        param.setMaxTime(maxTime);
        param.setDelay(delay);
        param.setInterval(interval);
        param.setHasParam(true);
        param.setParam(paramStr);
        param.setStatus(TimingConstant.STATUS_FREE);
        param.setAliveTm(DateUtil.getNowTS());
        param.setRunedCnt(0L);
        return param;
    }

    /**
     * build CronTask
     * @param key
     * @param service
     * @param cronExpression
     * @return
     */
    public AddTaskParam buildNewCronParam(String key, String service, String cronExpression) {
        AddTaskParam param = new AddTaskParam();
        param.setService(service);
        param.setTkey(key);
        param.setCron(true);
        param.setCronExpression(cronExpression);
        param.setStatus(TimingConstant.STATUS_FREE);
        param.setAliveTm(DateUtil.getNowTS());
        param.setRunedCnt(0L);
        return param;
    }

    public AddTaskParam(){}

    public static AddTaskParam buildFailNull(){
        AddTaskParam p = new AddTaskParam();
        p.setFailReason(RetCode.DB_TASK_NULL);
        return  p;
    }

    public static AddTaskParam buildFailStopOrInvalid(){
        AddTaskParam p = new AddTaskParam();
        p.setFailReason(RetCode.TASK_STOP_OR_INVALID);
        return  p;
    }

    public RetCode getFailReason() {
        return failReason;
    }

    public void setFailReason(RetCode failReason) {
        this.failReason = failReason;
    }

    public Long getRunedCnt() {
        return runedCnt;
    }

    public void setRunedCnt(Long runedCnt) {
        this.runedCnt = runedCnt;
    }

    public boolean isCron() {
        return cron;
    }

    public void setCron(boolean cron) {
        this.cron = cron;
    }

    public Long getAliveTm() {
        return aliveTm;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public void setAliveTm(Long aliveTm) {
        this.aliveTm = aliveTm;
    }

    public boolean isMutex() {
        return mutex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getTkey() {
        return tkey;
    }

    public void setTkey(String tkey) {
        this.tkey = tkey;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }

    public boolean getMutex() {
        return mutex;
    }

    public void setMutex(boolean mutex) {
        this.mutex = mutex;
    }

    public Long getMutexTm() {
        return mutexTm;
    }

    public void setMutexTm(Long mutexTm) {
        this.mutexTm = mutexTm;
    }

    public boolean isForever() {
        return forever;
    }

    public boolean isHasParam() {
        return hasParam;
    }

    public void setHasParam(boolean hasParam) {
        this.hasParam = hasParam;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public void setForever(boolean forever) {
        this.forever = forever;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean isHasCond() {
        return hasCond;
    }

    public void setHasCond(boolean hasCond) {
        this.hasCond = hasCond;
    }

    public boolean isHasCnt() {
        return hasCnt;
    }

    public void setHasCnt(boolean hasCnt) {
        this.hasCnt = hasCnt;
    }
}
