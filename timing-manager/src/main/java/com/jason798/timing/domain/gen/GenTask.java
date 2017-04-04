package com.jason798.timing.domain.gen;

import java.io.Serializable;

public class GenTask implements Serializable {
    private Long tid;

    private String tkey;

    private String name;

    private String confType;

    private Long confStartTm;

    private Long confExeTimes;

    private String confIntervalUnit;

    private Long confInterval;

    private String statusProcessor;

    private Long statusLastExeTm;

    private Long statusRunedTime;

    private String processing;

    private String executing;

    private String serviceName;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getConfType() {
        return confType;
    }

    public void setConfType(String confType) {
        this.confType = confType == null ? null : confType.trim();
    }

    public Long getConfStartTm() {
        return confStartTm;
    }

    public void setConfStartTm(Long confStartTm) {
        this.confStartTm = confStartTm;
    }

    public Long getConfExeTimes() {
        return confExeTimes;
    }

    public void setConfExeTimes(Long confExeTimes) {
        this.confExeTimes = confExeTimes;
    }

    public String getConfIntervalUnit() {
        return confIntervalUnit;
    }

    public void setConfIntervalUnit(String confIntervalUnit) {
        this.confIntervalUnit = confIntervalUnit == null ? null : confIntervalUnit.trim();
    }

    public Long getConfInterval() {
        return confInterval;
    }

    public void setConfInterval(Long confInterval) {
        this.confInterval = confInterval;
    }

    public String getStatusProcessor() {
        return statusProcessor;
    }

    public void setStatusProcessor(String statusProcessor) {
        this.statusProcessor = statusProcessor == null ? null : statusProcessor.trim();
    }

    public Long getStatusLastExeTm() {
        return statusLastExeTm;
    }

    public void setStatusLastExeTm(Long statusLastExeTm) {
        this.statusLastExeTm = statusLastExeTm;
    }

    public Long getStatusRunedTime() {
        return statusRunedTime;
    }

    public void setStatusRunedTime(Long statusRunedTime) {
        this.statusRunedTime = statusRunedTime;
    }

    public String getProcessing() {
        return processing;
    }

    public void setProcessing(String processing) {
        this.processing = processing == null ? null : processing.trim();
    }

    public String getExecuting() {
        return executing;
    }

    public void setExecuting(String executing) {
        this.executing = executing == null ? null : executing.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }
}