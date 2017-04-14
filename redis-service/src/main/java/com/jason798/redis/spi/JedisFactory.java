package com.jason798.redis.spi;

import com.jason798.redis.dto.RedisConfig;
import com.jason798.spi.LifeCycle;

/**
 * build jedis factory
 */
public interface JedisFactory<T> extends LifeCycle {

    T getResource();

    void returnResource(T resource);

    RedisConfig getConfig();

    void setConfig(RedisConfig config);

}
