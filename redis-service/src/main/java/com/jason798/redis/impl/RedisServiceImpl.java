package com.jason798.redis.impl;

import com.jason798.character.StringCheckUtil;
import com.jason798.collection.CollectionUtil;
import com.jason798.common.DateUtil;
import com.jason798.redis.api.RedisService;
import com.jason798.redis.api.RedisServiceErrorCode;
import com.jason798.redis.api.RedisServiceException;
import com.jason798.redis.dto.SlowLogDto;
import com.jason798.redis.dto.info.RedisInfoDto;
import com.jason798.redis.spi.JedisFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.util.Slowlog;

import java.util.*;

/**
 * implement redis service
 */
public class RedisServiceImpl implements RedisService {
    private final static Logger LOG = LoggerFactory
            .getLogger(RedisServiceImpl.class);

    private JedisFactory<Jedis> redisFactory;

    public RedisServiceImpl() {
    }

    public RedisServiceImpl(JedisFactory<Jedis> redisFactory) {
        this.redisFactory = redisFactory;
    }


    public JedisFactory<Jedis> getRedisFactory() {
        return redisFactory;
    }

    public void setRedisFactory(JedisFactory<Jedis> redisFactory) {
        this.redisFactory = redisFactory;
    }

    /**
     * execute redis command template
     *
     * @param <T>
     */
    abstract class Executor<T> {

        Jedis jedis;

        public Executor() {
            jedis = redisFactory.getResource();
        }

        abstract T action();

        public T execute(String operation, String... strings) {
            T result = null;
            boolean broken = false;
            try {
                long startTime = System.currentTimeMillis();

                result = action();//execute

                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                String content = "operation=" + operation + ", duration=" + duration + ", parameters=" + Arrays.asList(strings).toString();
                LOG.debug(content);
            } catch (Throwable e) {
                broken = handleException(e);
                throw new RedisServiceException("Redis execute exception, " + e.getMessage(), RedisServiceErrorCode.REDIS_EXECUTE_FAIL_ERROR);
            } finally {
                if (jedis != null) {
                    redisFactory.returnResource(jedis);
                }
            }
            return result;
        }

        private boolean handleException(Throwable e) {
            if (e instanceof JedisDataException) {
                if ((e.getMessage() == null) || (e.getMessage().indexOf("READONLY") == -1)) {
                    // dataException, isBroken=false
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public void set(final byte[] key, final byte[] value, final int seconds) {
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return (seconds > 0) ? jedis.setex(key, seconds, value) : jedis.set(key, value);
            }
        };
        executor.execute("redis.set(byte,byte) and key.length=" + key.length + " and value.length=" + value.length, "");
    }

    @Override
    public void set(final String key, final String value, final int seconds) {
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return (seconds > 0) ? jedis.setex(key, seconds, value) : jedis.set(key, value);
            }
        };
        executor.execute("redis.set", key, value, String.valueOf(seconds));
    }

    public Long setnx(final String key, final String value) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.setnx(key, value);
            }
        };
        return executor.execute("redis.setnx", key, value);
    }

    @Override
    public Long expire(final String key, final int seconds) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.expire(key, seconds);
            }
        };
        return executor.execute("redis.expire", key, String.valueOf(seconds));
    }

    @Override
    public byte[] get(final byte[] key) {
        Executor<byte[]> executor = new Executor<byte[]>() {
            @Override
            byte[] action() {
                return jedis.get(key);
            }
        };
        return executor.execute("redis.get(byte) and key.length=" + key.length, "");
    }

    @Override
    public String get(final String key) {
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.get(key);
            }
        };
        return executor.execute("redis.get", key);
    }

    @Override
    public Long append(String key, String value) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.append(key, value);
            }
        };
        return executor.execute("redis.append", key, value);
    }

    @Override
    public void remove(final byte[] key) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.del(key);
            }
        };
        executor.execute("redis.remove(byte) and key.length=" + key.length, "");
    }

    @Override
    public void remove(final String key) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.del(key);
            }
        };
        executor.execute("redis.del", key);
    }

    @Override
    public boolean exists(final String key) {
        if(StringCheckUtil.isEmpty(key)){
            return false;
        }
        Executor<Boolean> executor = new Executor<Boolean>() {
            @Override
            Boolean action() {
                return jedis.exists(key);
            }
        };
        return executor.execute("redis.exists", key);
    }

    @Override
    public List<String> mget(final String... keys) {
        Executor<List<String>> executor = new Executor<List<String>>() {
            @Override
            List<String> action() {
                return jedis.mget(keys);
            }
        };
        return executor.execute("redis.mget", Arrays.asList(keys).toString());
    }

    @Override
    public String mset(final String... keysvalues) {
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.mset(keysvalues);
            }
        };
        return executor.execute("redis.mset", Arrays.asList(keysvalues).toString());
    }

    @Override
    public Long rpush(final String key, final String... strings) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.rpush(key, strings);
            }
        };
        return executor.execute("redis.rpush", key, Arrays.asList(strings).toString());
    }

    @Override
    public Long lpush(final byte[] key, final byte[]... strings) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.lpush(key, strings);
            }
        };
        return executor.execute("redis.lpush(byte)", "");
    }

    @Override
    public Long lpush(final String key, final String... strings) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.lpush(key, strings);
            }
        };
        return executor.execute("redis.lpush", key, Arrays.asList(strings).toString());
    }

    @Override
    public String rpop(final String key) {
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.rpop(key);
            }
        };
        return executor.execute("redis.rpop", key);
    }

    @Override
    public String lpop(final String key) {
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.lpop(key);
            }
        };
        return executor.execute("redis.lpop", key);
    }

    @Override
    public List<String> lrange(final String key, final long start, final long end) {
        Executor<List<String>> executor = new Executor<List<String>>() {
            @Override
            List<String> action() {
                return jedis.lrange(key, start, end);
            }
        };
        return executor.execute("redis.lrange", key, String.valueOf(start), String.valueOf(end));
    }

    @Override
    public List<String> lrange(String key, long start) {
        Executor<List<String>> executor = new Executor<List<String>>() {
            @Override
            List<String> action() {
                Long len = jedis.llen(key);
                if (len != null) {
                    return jedis.lrange(key, start, len);
                } else {
                    return null;
                }
            }
        };
        return executor.execute("redis.lranges", key, String.valueOf(start));
    }

    @Override
    public List<String> lrangeLimit(String key, int end) {
        return lrange(key, 0, end);
    }

    @Override
    public String ltrim(final String key, final long start, final long end) {
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.ltrim(key, start, end);
            }
        };
        return executor.execute("redis.ltrim", key, String.valueOf(start), String.valueOf(end));
    }

    @Override
    public Long lrem(final String key, final long count, final String value) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.lrem(key, count, value);
            }
        };
        return executor.execute("redis.lrem", key, String.valueOf(count), value);
    }

    @Override
    public Set<String> hkeys(final String key) {
        Executor<Set<String>> executor = new Executor<Set<String>>() {
            @Override
            Set<String> action() {
                return jedis.hkeys(key);
            }
        };
        return executor.execute("redis.hkeys", key);
    }

    @Override
    public Long hset(final String key, final String field, final String value) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.hset(key, field, value);
            }
        };
        return executor.execute("redis.hset", key, field, value);
    }

    @Override
    public Long hdel(String key, String field) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.hdel(key, field);
            }
        };
        return executor.execute("redis.hdel", key, field);
    }

    @Override
    public Long hlen(String key) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.hlen(key);
            }
        };
        return executor.execute("redis.hlen", key);
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Executor<Map<String, String>> executor = new Executor<Map<String, String>>() {
            @Override
            Map<String, String> action() {
                return jedis.hgetAll(key);
            }
        };
        return executor.execute("redis.hgetall", key);
    }

    @Override
    public String hget(final String key, final String field) {
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.hget(key, field);
            }
        };
        return executor.execute("redis.hget", key, field);
    }

    @Override
    public Long zadd(final String key, final double score, final String member) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.zadd(key, score, member);
            }
        };
        return executor.execute("redis.zadd", key, String.valueOf(score), member);
    }

    @Override
    public Long zcard(final String key) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.zcard(key);
            }
        };
        return executor.execute("redis.zcard", key);
    }

    @Override
    public Long zrem(final String key, final String... members) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.zrem(key, members);
            }
        };
        return executor.execute("redis.zrem", key, Arrays.asList(members).toString());
    }

    @Override
    public Double zscore(final String key, final String member) {
        Executor<Double> executor = new Executor<Double>() {
            @Override
            Double action() {
                return jedis.zscore(key, member);
            }
        };
        return executor.execute("redis.zscore", key, member);
    }

    @Override
    public Set<String> zrevrange(final String key, final long start, final long end) {
        Executor<Set<String>> executor = new Executor<Set<String>>() {
            @Override
            Set<String> action() {
                return jedis.zrevrange(key, start, end);
            }
        };
        return executor.execute("redis.zrevrange", key, String.valueOf(start), String.valueOf(end));
    }

    @Override
    public Long zunionstore(final String key, final String... sets) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.zunionstore(key, sets);
            }
        };
        return executor.execute("redis.zunionstore", key, Arrays.asList(sets).toString());
    }

    @Override
    public Long sadd(String key, String... value) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.sadd(key, value);
            }
        };
        return executor.execute("redis.sadd", key, Arrays.asList(value).toString());
    }

    @Override
    public Long scard(String key) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.scard(key);
            }
        };
        return executor.execute("redis.scard", key);
    }

    @Override
    public Set<String> smembers(String key) {
        Executor<Set<String>> executor = new Executor<Set<String>>() {
            @Override
            Set<String> action() {
                return jedis.smembers(key);
            }
        };
        return executor.execute("redis.smembers", key);
    }

    @Override
    public Object eval(String script) {
        Executor<Object> executor = new Executor<Object>() {
            @Override
            Object action() {
                return jedis.eval(script);
            }
        };
        return executor.execute("redis.eval", script);
    }

    @Override
    public RedisInfoDto info() {
        Executor<RedisInfoDto> executor = new Executor<RedisInfoDto>() {
            @Override
            RedisInfoDto action() {
                String raw = jedis.info();
                RedisInfoDto redisInfoDto = formatInfo(raw);
                if (redisInfoDto == null) {
                    redisInfoDto = new RedisInfoDto();
                    redisInfoDto.setRaw(raw);
                }
                return redisInfoDto;
            }
        };
        return executor.execute("redis.info");
    }

    private RedisInfoDto formatInfo(String info) {
        try {
            return RedisInfoDto.build(RedisInfoDto.split(info));
        } catch (Exception e) {
            LOG.error("format info error {}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<SlowLogDto> slowLog(int n) {
        Executor<List<SlowLogDto>> executor = new Executor<List<SlowLogDto>>() {
            @Override
            List<SlowLogDto> action() {
                List<SlowLogDto> res = new LinkedList<>();
                if (n <= 0) {
                    return res;
                }
                List<Slowlog> logs = jedis.slowlogGet(n);
                for (Slowlog sl : logs) {
                    SlowLogDto dto = new SlowLogDto();
                    dto.setId(sl.getId());
                    String tsFmt = DateUtil.formatDefault(DateUtil.ts2Date(sl.getTimeStamp()));
                    dto.setCostTime(sl.getExecutionTime());
                    dto.setExecuteTime(tsFmt);
                    if (CollectionUtil.isEmpty(sl.getArgs())) {
                        continue;
                    }
                    StringBuilder sb = new StringBuilder();
                    int i = 0;
                    int lastIdx = sl.getArgs().size() - 1;
                    for (String arg : sl.getArgs()) {
                        sb.append(arg);
                        if (i != lastIdx) {
                            sb.append(",");
                        }
                        i++;
                    }
                    dto.setArgs(sb.toString());
                    res.add(dto);
                }
                return res;
            }
        };
        return executor.execute("redis.slowlog", String.valueOf(n));
    }

    @Override
    public List<SlowLogDto> slowLog() {
        return slowLog(DFT_SLOWLOG_CNT);
    }

    @Override
    public String acquireLockWithTimeout(String lockName, int acquireTimeout, int lockTimeout) {
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                String identifier = UUID.randomUUID().toString();
                String lockNameFormat = String.format("lock:%s", lockName);
                long end = System.currentTimeMillis() + acquireTimeout;
                do {
                    if (jedis.setnx(lockNameFormat, identifier) == 1) {
                        jedis.expire(lockNameFormat, lockTimeout);
                        return identifier;
                    } else if (jedis.ttl(lockNameFormat) == -1) {
                        jedis.expire(lockNameFormat, lockTimeout);
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (System.currentTimeMillis() < end);
                return null;
            }
        };
        return executor.execute("redis.acquireLockWithTimeout", lockName, String.valueOf(acquireTimeout), String.valueOf(lockTimeout));
    }

    @Override
    public Boolean releaseLock(String lockName, String identifier) {
        Executor<Boolean> executor = new Executor<Boolean>() {
            @Override
            Boolean action() {
                String lockNameFormat = String.format("lock:%s", lockName);
                while (true) {
                    jedis.watch(lockNameFormat);
                    if (identifier.equals(jedis.get(lockNameFormat))) {
                        Transaction tx = jedis.multi();
                        tx.del(lockNameFormat);
                        List<Object> list = tx.exec();
                        jedis.unwatch();
                        if (list == null) {
                            continue;
                        }
                        return true;
                    } else {
                        jedis.unwatch();
                        break;
                    }
                }
                return false;
            }
        };
        return executor.execute("redis.releaseLock", lockName, identifier);
    }


    @Override
    public Long decrement(final String key, final long delta) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.decrBy(key, delta);
            }
        };
        return executor.execute("redis.decr", key, String.valueOf(delta));
    }

    @Override
    public Long increment(final String key, final long delta) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.incrBy(key, delta);
            }
        };
        return executor.execute("redis.incr", key, String.valueOf(delta));
    }

    @Override
    public Long llen(final String key) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.llen(key);
            }
        };
        return executor.execute("redis.llen", key);
    }

    @Override
    public Long ttl(final String key) {
        Executor<Long> executor = new Executor<Long>() {
            @Override
            Long action() {
                return jedis.ttl(key);
            }
        };
        return executor.execute("redis.ttl", key);
    }

    @Override
    public String type(String key) {
        Executor<String> executor = new Executor<String>() {
            @Override
            String action() {
                return jedis.type(key);
            }
        };
        return executor.execute("redis.type", key);
    }

    /**
     * !!! 仅供测试，线上慎用
     */
    public Set<String> keys(String keyPattern) {
        Executor<Set<String>> executor = new Executor<Set<String>>() {
            @Override
            Set<String> action() {
                return jedis.keys(keyPattern);
            }
        };
        return executor.execute("redis.keys", keyPattern);
    }


}