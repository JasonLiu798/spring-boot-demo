package com.jason798.redis.dto;

/**
 * for sentinel config
 */
public class RedisSentinelConfig extends RedisPoolConfig {

    private String clustername;

    public RedisSentinelConfig() {
    }

    @Override
    public String toString() {
        return super.toString() + "RedisSentinelConfig{clustername='" + clustername + '\'' +
                '}';
    }

    public String getClustername() {
        return clustername;
    }

    public void setClustername(String clustername) {
        this.clustername = clustername;
    }
}
