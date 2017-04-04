package com.jason798.timing.domain.gen;

import java.io.Serializable;

public class GenTaskManager implements Serializable {
    private Long mid;

    private String name;

    private Long lastUpdateTm;

    private static final long serialVersionUID = 1L;

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getLastUpdateTm() {
        return lastUpdateTm;
    }

    public void setLastUpdateTm(Long lastUpdateTm) {
        this.lastUpdateTm = lastUpdateTm;
    }
}