package com.jason798.timing.domain.gen;

import java.io.Serializable;

public class GenTaskHistory implements Serializable {
    private Long htid;

    private Long tid;

    private String manager;

    private String thread;

    private Long startTm;

    private Long endTm;

    private String exeStatus;

    private String memo;

    private static final long serialVersionUID = 1L;

    public Long getHtid() {
        return htid;
    }

    public void setHtid(Long htid) {
        this.htid = htid;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread == null ? null : thread.trim();
    }

    public Long getStartTm() {
        return startTm;
    }

    public void setStartTm(Long startTm) {
        this.startTm = startTm;
    }

    public Long getEndTm() {
        return endTm;
    }

    public void setEndTm(Long endTm) {
        this.endTm = endTm;
    }

    public String getExeStatus() {
        return exeStatus;
    }

    public void setExeStatus(String exeStatus) {
        this.exeStatus = exeStatus == null ? null : exeStatus.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}