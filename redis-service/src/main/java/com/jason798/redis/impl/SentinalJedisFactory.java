package com.jason798.redis.impl;

import com.jason798.character.StringCheckUtil;
import com.jason798.redis.api.RedisServiceErrorCode;
import com.jason798.redis.dto.RedisConfig;
import com.jason798.redis.dto.RedisSentinelConfig;
import com.jason798.redis.spi.JedisFactory;
import com.jason798.redis.api.RedisServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

public class SentinalJedisFactory implements JedisFactory<Jedis> {

    private final static Logger LOG = LoggerFactory
            .getLogger(SentinalJedisFactory.class);

    private JedisSentinelPool pool;

    /**
     * for init pool
     */
    private ReentrantLock lockPool = new ReentrantLock();

    /**
     * for get Jedis
     */
    private ReentrantLock lockJedis = new ReentrantLock();

    private RedisConfig config;

    public SentinalJedisFactory() {
    }

    public SentinalJedisFactory(RedisConfig redisConfig) {
        this.config = redisConfig;
    }


    @Override
    public Jedis getResource() {
        //get jedis
        lockJedis.lock();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
        } finally {
            lockJedis.unlock();
        }
        return jedis;
    }

    @Override
    public void returnResource(Jedis resource) {
        if (pool != null && resource != null) {
            pool.returnResourceObject(resource);
        }
    }

    @Override
    public RedisConfig getConfig() {
        return config;
    }

    @Override
    public void setConfig(RedisConfig config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "SentinalJedisFactory{" +
                "pool=" + pool +
                '}';
    }

    @Override
    public void init() {
        if (config == null && config.getClass() != RedisSentinelConfig.class) {
            LOG.error("redis init fail,redisconfig null or class type error");
            throw new RedisServiceException("RedisConfig null or class type error", RedisServiceErrorCode.REDIS_CONFIG_ERROR);
        }
        RedisSentinelConfig redisPoolConfig = (RedisSentinelConfig) config;
        //if pool null,init pool
        if (this.pool == null) {
            lockPool.lock();
            try {
                if (this.pool == null) {
                    initPool(redisPoolConfig);
                }
            } finally {
                lockPool.unlock();
            }
        }
        if (this.pool == null) {
            throw new RedisServiceException("Jedis not initialized.", RedisServiceErrorCode.REDIS_CLIENT_NOT_INITIALIZED);
        }
        LOG.info("sentinal redis pool initialed success");
    }

    private void initPool(RedisSentinelConfig redisPoolSentinelConfig) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(redisPoolSentinelConfig.getMaxActive());
        config.setMaxIdle(redisPoolSentinelConfig.getMaxIdle());
        config.setMaxWaitMillis(redisPoolSentinelConfig.getMaxWait());
        config.setTestOnBorrow(true);
        config.setTestWhileIdle(true);

        Set<String> sentinels = getRedisSentinels(redisPoolSentinelConfig.getIp());
        if (StringCheckUtil.isNotEmpty(redisPoolSentinelConfig.getAuth())) {
            //auth
            pool = new JedisSentinelPool(
                    redisPoolSentinelConfig.getClustername(), sentinels, config, redisPoolSentinelConfig.getAuth());
        } else {
            //no auth
            pool = new JedisSentinelPool(
                    redisPoolSentinelConfig.getClustername(), sentinels, config);

        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                if (pool != null) {
                    pool.destroy();
                }
            }
        });
    }

    private Set<String> getRedisSentinels(String redis_addr2) {
        Set<String> sentinelSet = new HashSet<>();
        for (String sentinel : redis_addr2.split("\\|")) {
            sentinelSet.add(sentinel);
        }
        return sentinelSet;
    }

    @Override
    public void destroy() {
        if (pool != null) {
            pool.destroy();
        }
    }
}
