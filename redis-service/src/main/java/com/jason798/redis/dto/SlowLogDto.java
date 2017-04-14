package com.jason798.redis.dto;

import java.io.Serializable;

public class SlowLogDto implements Serializable{
    private long id;
    private String executeTime;
    private long costTime;
    private String args;

    public SlowLogDto(){}

    @Override
    public String toString() {
        return "SlowLogDto{" +
                "id=" + id +
                ", executeTime='" + executeTime + '\'' +
                ", costTime=" + costTime +
                ", args='" + args + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
