package com.jason798.redis.dto;

/**
 * for pool
 */
public class RedisPoolConfig extends RedisConfig {

    protected int maxActive = 10;
    protected int maxIdle = 1;
    protected int maxWait = 100;


    public RedisPoolConfig() {
    }

    @Override
    public String toString() {
        return super.toString()+"RedisPoolConfig{" +
                ", maxActive=" + maxActive +
                ", maxIdle=" + maxIdle +
                ", maxWait=" + maxWait +
                ", timeout=" + timeout +
                '}';
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }


}
