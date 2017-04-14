package com.jason798.redis.dto.info;


import java.io.Serializable;

public class PersistenceDto implements Serializable{
    private String loading;
    private String rdb_changes_since_last_save;
    private String rdb_bgsave_in_progress;
    private String rdb_last_save_time;
    private String rdb_last_bgsave_status;
    private String rdb_last_bgsave_time_sec;
    private String rdb_current_bgsave_time_sec;
    private String aof_enabled;
    private String aof_rewrite_in_progress;
    private String aof_rewrite_scheduled;
    private String aof_last_rewrite_time_sec;
    private String aof_current_rewrite_time_sec;
    private String aof_last_bgrewrite_status;
    private String aof_last_write_status;

    @Override
    public String toString() {
        return "PersistenceDto{" +
                "loading='" + loading + '\'' +
                ", rdb_changes_since_last_save='" + rdb_changes_since_last_save + '\'' +
                ", rdb_bgsave_in_progress='" + rdb_bgsave_in_progress + '\'' +
                ", rdb_last_save_time='" + rdb_last_save_time + '\'' +
                ", rdb_last_bgsave_status='" + rdb_last_bgsave_status + '\'' +
                ", rdb_last_bgsave_time_sec='" + rdb_last_bgsave_time_sec + '\'' +
                ", rdb_current_bgsave_time_sec='" + rdb_current_bgsave_time_sec + '\'' +
                ", aof_enabled='" + aof_enabled + '\'' +
                ", aof_rewrite_in_progress='" + aof_rewrite_in_progress + '\'' +
                ", aof_rewrite_scheduled='" + aof_rewrite_scheduled + '\'' +
                ", aof_last_rewrite_time_sec='" + aof_last_rewrite_time_sec + '\'' +
                ", aof_current_rewrite_time_sec='" + aof_current_rewrite_time_sec + '\'' +
                ", aof_last_bgrewrite_status='" + aof_last_bgrewrite_status + '\'' +
                ", aof_last_write_status='" + aof_last_write_status + '\'' +
                '}';
    }

    public PersistenceDto() {
    }

    public String getLoading() {
        return loading;
    }

    public void setLoading(String loading) {
        this.loading = loading;
    }

    public String getRdb_changes_since_last_save() {
        return rdb_changes_since_last_save;
    }

    public void setRdb_changes_since_last_save(String rdb_changes_since_last_save) {
        this.rdb_changes_since_last_save = rdb_changes_since_last_save;
    }

    public String getRdb_bgsave_in_progress() {
        return rdb_bgsave_in_progress;
    }

    public void setRdb_bgsave_in_progress(String rdb_bgsave_in_progress) {
        this.rdb_bgsave_in_progress = rdb_bgsave_in_progress;
    }

    public String getRdb_last_save_time() {
        return rdb_last_save_time;
    }

    public void setRdb_last_save_time(String rdb_last_save_time) {
        this.rdb_last_save_time = rdb_last_save_time;
    }

    public String getRdb_last_bgsave_status() {
        return rdb_last_bgsave_status;
    }

    public void setRdb_last_bgsave_status(String rdb_last_bgsave_status) {
        this.rdb_last_bgsave_status = rdb_last_bgsave_status;
    }

    public String getRdb_last_bgsave_time_sec() {
        return rdb_last_bgsave_time_sec;
    }

    public void setRdb_last_bgsave_time_sec(String rdb_last_bgsave_time_sec) {
        this.rdb_last_bgsave_time_sec = rdb_last_bgsave_time_sec;
    }

    public String getRdb_current_bgsave_time_sec() {
        return rdb_current_bgsave_time_sec;
    }

    public void setRdb_current_bgsave_time_sec(String rdb_current_bgsave_time_sec) {
        this.rdb_current_bgsave_time_sec = rdb_current_bgsave_time_sec;
    }

    public String getAof_enabled() {
        return aof_enabled;
    }

    public void setAof_enabled(String aof_enabled) {
        this.aof_enabled = aof_enabled;
    }

    public String getAof_rewrite_in_progress() {
        return aof_rewrite_in_progress;
    }

    public void setAof_rewrite_in_progress(String aof_rewrite_in_progress) {
        this.aof_rewrite_in_progress = aof_rewrite_in_progress;
    }

    public String getAof_rewrite_scheduled() {
        return aof_rewrite_scheduled;
    }

    public void setAof_rewrite_scheduled(String aof_rewrite_scheduled) {
        this.aof_rewrite_scheduled = aof_rewrite_scheduled;
    }

    public String getAof_last_rewrite_time_sec() {
        return aof_last_rewrite_time_sec;
    }

    public void setAof_last_rewrite_time_sec(String aof_last_rewrite_time_sec) {
        this.aof_last_rewrite_time_sec = aof_last_rewrite_time_sec;
    }

    public String getAof_current_rewrite_time_sec() {
        return aof_current_rewrite_time_sec;
    }

    public void setAof_current_rewrite_time_sec(String aof_current_rewrite_time_sec) {
        this.aof_current_rewrite_time_sec = aof_current_rewrite_time_sec;
    }

    public String getAof_last_bgrewrite_status() {
        return aof_last_bgrewrite_status;
    }

    public void setAof_last_bgrewrite_status(String aof_last_bgrewrite_status) {
        this.aof_last_bgrewrite_status = aof_last_bgrewrite_status;
    }

    public String getAof_last_write_status() {
        return aof_last_write_status;
    }

    public void setAof_last_write_status(String aof_last_write_status) {
        this.aof_last_write_status = aof_last_write_status;
    }
}
