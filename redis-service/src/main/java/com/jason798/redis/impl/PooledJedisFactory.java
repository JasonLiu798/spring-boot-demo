package com.jason798.redis.impl;

import com.jason798.character.StringCheckUtil;
import com.jason798.redis.dto.RedisConfig;
import com.jason798.redis.api.RedisServiceErrorCode;
import com.jason798.redis.api.RedisServiceException;
import com.jason798.redis.dto.RedisPoolConfig;
import com.jason798.redis.spi.JedisFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReentrantLock;

/**
 * pool jedis factory
 */
public class PooledJedisFactory implements JedisFactory<Jedis> {

    private final static Logger LOG = LoggerFactory
            .getLogger(PooledJedisFactory.class);

    private JedisPool pool;

    private ReentrantLock lockPool = new ReentrantLock();

    private ReentrantLock lockJedis = new ReentrantLock();

    private RedisConfig config;

    public PooledJedisFactory() {
    }

    public PooledJedisFactory(RedisConfig redisConfig) {
        this.config = redisConfig;
    }

    @Override
    public Jedis getResource() {
        //init pool
        if (pool == null) {
            return null;
        }
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
        return "PooledJedisFactory{" +
                "pool=" + pool +
                '}';
    }

    @Override
    public void init() {
        if (config == null || config.getClass() != RedisPoolConfig.class) {
            LOG.error("");
        }
        if (this.pool == null) {
            lockPool.lock();
            try {
                if (this.pool == null) {
                    RedisPoolConfig redisPoolConfig = (RedisPoolConfig) config;
                    initPool(redisPoolConfig);
                }
            } finally {
                lockPool.unlock();
            }
        }

        if (this.pool == null) {
            throw new RedisServiceException("Jedis not initialized.", RedisServiceErrorCode.REDIS_CLIENT_NOT_INITIALIZED);
        }

    }

    private void initPool(RedisPoolConfig redisPoolConfig) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(redisPoolConfig.getMaxActive());
        config.setMaxIdle(redisPoolConfig.getMaxIdle());
        config.setMaxWaitMillis(redisPoolConfig.getMaxWait());
        config.setTestOnBorrow(true);
        config.setTestWhileIdle(true);
        if (StringCheckUtil.isNotEmpty(redisPoolConfig.getAuth())) {
            pool = new JedisPool(config,
                    redisPoolConfig.getIp(),
                    redisPoolConfig.getPort(),
                    redisPoolConfig.getTimeout(),
                    redisPoolConfig.getAuth());
        } else {
            pool = new JedisPool(config,
                    redisPoolConfig.getIp(),
                    redisPoolConfig.getPort(),
                    redisPoolConfig.getTimeout());
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

    public JedisPool getPool() {
        return pool;
    }

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    @Override
    public void destroy() {
        if (pool != null) {
            pool.destroy();
        }
    }
}
