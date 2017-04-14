package test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class RedisTest {
/*
    @Test
    public void test1() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(100);
            config.setMaxIdle(5);
            config.setMaxWaitMillis(15000);
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
            config.setTestOnBorrow(true);
            JedisPool jedisPool = new JedisPool(config, "0.0.0.0", 8080,
                    5000, "");

            Jedis jedis = jedisPool.getResource();
            System.err.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        Set<String> sentinels = new HashSet<String>();

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(15000);
        // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
        config.setTestOnBorrow(true);
        JedisSentinelPool redisSentinelJedisPool = new JedisSentinelPool(clusterName, sentinels, config, password);

        Jedis jedis = null;
        try {
            jedis = redisSentinelJedisPool.getResource();
            jedis.set("key", "valuezsl");
            System.out.println(jedis.get("key"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            redisSentinelJedisPool.returnBrokenResource(jedis);
        }

        redisSentinelJedisPool.close();
    }
*/


}
