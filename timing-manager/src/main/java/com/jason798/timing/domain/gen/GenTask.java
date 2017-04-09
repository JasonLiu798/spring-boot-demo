package com.jason798.timing.domain.gen;

import java.io.Serializable;

public class GenTask implements Serializable {
    private Long tid;

    private String tkey;

    private String serviceName;

    private String confType;

    private String confCronExpression;

    private Long confDelayTm;

    private Long confIntervalTm;

    private Long confExeTimes;

    private String processor;

    private String statusEnd;

    private String mutex;

    private Long mutexTm;

    private String status;

    private Long aliveTm;

    private String valid;

    private static final long serialVersionUID = 1L;

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
        this.tkey = tkey == null ? null : tkey.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getConfType() {
        return confType;
    }

    public void setConfType(String confType) {
        this.confType = confType == null ? null : confType.trim();
    }

    public String getConfCronExpression() {
        return confCronExpression;
    }

    public void setConfCronExpression(String confCronExpression) {
        this.confCronExpression = confCronExpression == null ? null : confCronExpression.trim();
    }

    public Long getConfDelayTm() {
        return confDelayTm;
    }

    public void setConfDelayTm(Long confDelayTm) {
        this.confDelayTm = confDelayTm;
    }

    public Long getConfIntervalTm() {
        return confIntervalTm;
    }

    public void setConfIntervalTm(Long confIntervalTm) {
        this.confIntervalTm = confIntervalTm;
    }

    public Long getConfExeTimes() {
        return confExeTimes;
    }

    public void setConfExeTimes(Long confExeTimes) {
        this.confExeTimes = confExeTimes;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor == null ? null : processor.trim();
    }

    public String getStatusEnd() {
        return statusEnd;
    }

    public void setStatusEnd(String statusEnd) {
        this.statusEnd = statusEnd == null ? null : statusEnd.trim();
    }

    public String getMutex() {
        return mutex;
    }

    public void setMutex(String mutex) {
        this.mutex = mutex == null ? null : mutex.trim();
    }

    public Long getMutexTm() {
        return mutexTm;
    }

    public void setMutexTm(Long mutexTm) {
        this.mutexTm = mutexTm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getAliveTm() {
        return aliveTm;
    }

    public void setAliveTm(Long aliveTm) {
        this.aliveTm = aliveTm;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }
}