package com.jason798.redis.impl;

import com.jason798.character.StringCheckUtil;
import com.jason798.redis.dto.RedisConfig;
import com.jason798.redis.spi.JedisFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class SingleJedisFactory implements JedisFactory<Jedis> {
    private final static Logger LOG = LoggerFactory
            .getLogger(SingleJedisFactory.class);

    private RedisConfig config;

    ThreadLocal<Jedis> jedisMap = new ThreadLocal<>();

    public SingleJedisFactory() {
    }

    public SingleJedisFactory(RedisConfig redisConfig) {
        this.config = redisConfig;
    }

    @Override
    public Jedis getResource() {
        if (config == null) {
            throw new IllegalStateException("redis config not set");
        }
        Jedis jedis = jedisMap.get();
        if (jedis == null) {
            LOG.debug("jedis null");
            jedis = new Jedis(config.getIp(), config.getPort());
            if(StringCheckUtil.isNotEmpty(config.getAuth())){
                jedis.auth(config.getAuth());
            }
            jedisMap.set(jedis);
        }
        return jedis;
    }

    @Override
    public void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    @Override
    public String toString() {
        return "SingleJedisFactory{" +
                "config=" + config +
                '}';
    }

    public RedisConfig getConfig() {
        return config;
    }

    public void setConfig(RedisConfig config) {
        this.config = config;
    }

    @Override
    public void init() {
    }

    @Override
    public void destroy() {
        Jedis jedis = jedisMap.get();
        if (jedis != null) {
            jedis.close();
        }
    }
}
