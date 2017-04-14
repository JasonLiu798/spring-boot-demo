package com.jason798.redis.api;

import com.jason798.redis.dto.SlowLogDto;
import com.jason798.redis.dto.info.RedisInfoDto;
import com.jason798.redis.spi.JedisFactory;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public interface RedisService {

    JedisFactory<Jedis> getRedisFactory();

    /**
     * never expire parameter for interface set,expire
     */
    int NEVER_EXPIRE = -1;
    int DFT_SLOWLOG_CNT = 5;

    /**
     * ################ key api ######################
     */
    /**
     * @param key
     * @param value
     * @param seconds ttl
     */
    void set(byte[] key, byte[] value, int seconds);

    /**
     * set value never expire
     * set(K,V,NEVER_EXPIRE);
     *
     * @param key
     * @param value
     * @param seconds ttl
     */
    void set(String key, String value, int seconds);

    Long setnx(String key, String value);

    byte[] get(byte[] key);

    String get(String key);

    Long append(String key, String value);

    void remove(byte[] key);

    void remove(String key);

    Long expire(String key, int seconds);

    boolean exists(String key);

    List<String> mget(String... keys);

    String mset(String... keysvalues);

    Long increment(String key, long delta);

    Long decrement(String key, long delta);

    Long ttl(String key);

    /**
     * "none","string", "list", "set"
     *
     * @param key
     * @return
     */
    String type(String key);

    /**
     * ############## list api #################
     */
    Long rpush(String key, String... strings);

    Long lpush(String key, String... strings);

    Long lpush(byte[] key, byte[]... strings);

    String rpop(String key);

    String lpop(String key);

    Long llen(String key);

    List<String> lrange(String key, long start, long end);

    /**
     * list 长度过长，慎用
     *
     * @param key
     * @param start
     * @return
     */
    List<String> lrange(String key, long start);//lrange key start llen(key)

    List<String> lrangeLimit(String key, int end);//lrange 0 end

    String ltrim(String key, long start, long end);

    Long lrem(String key, long count, String value);

    /**
     * ############### hash api ######################
     */
    Set<String> hkeys(String key);

    String hget(String key, String field);

    Long hset(String key, String field, String value);

    Long hdel(String key, String field);

    Long hlen(String key);

    /**
     * hash table key较多时，慎用
     *
     * @param key
     * @return
     */
    Map<String, String> hgetAll(String key);

    /**
     * ############## zset api #######################
     * zsdd Olog(n)
     */
    Long zadd(String key, double score, String member);

    Set<String> zrevrange(String key, long start, long end);

    Long zcard(String key);

    Long zrem(String key, String... members);

    Double zscore(String key, String member);

    Long zunionstore(String key, String... sets);

    /**
     * ############## set api ##########################
     */
    Long sadd(String key, String... value);

    Long scard(String key);

    Set<String> smembers(String key);

    Object eval(String script);


    /**
     * ############### status api ########################
     */
    RedisInfoDto info();

    List<SlowLogDto> slowLog(int n);

    List<SlowLogDto> slowLog();


    /**
     * lock api
     */
    String acquireLockWithTimeout(String lockName, int acquireTimeout, int lockTimeout);

    Boolean releaseLock(String lockName, String identifier);


    /**
     * !!! 仅供测试，线上慎用，key较多，会产生严重性能问题
     *
     * @param keyPattern
     * @return
     */
    Set<String> keys(String keyPattern);


}
