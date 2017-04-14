package com.jason798.redis.impl;

import com.jason798.character.StringCheckUtil;
import com.jason798.collection.CollectionUtil;
import com.jason798.config.ConfigUtil;
import com.jason798.redis.dto.RedisConfig;
import com.jason798.redis.dto.RedisConstant;
import com.jason798.redis.dto.RedisSentinelConfig;
import com.jason798.redis.dto.RedisPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * Redis config parser,and config holder
 * propertie file
 * <p>
 * redis.servers=A,B...
 * <p>
 * redis server A
 * redis.[A].type=single
 * redis.[A].ip=1.1.1.1
 * redis.[A].port=6370
 * redis.[A].auth=aaa
 * <p>
 * redis server B
 * redis.[B].ip
 * redis.[B].port
 * redis.[B].auth ....
 *
 *
 */
public class RedisConfigHolder {
    public static final Logger LOG = LoggerFactory.getLogger(RedisConfigHolder.class);
    private static Map<String, RedisConfig> map = new HashMap<>();
    /**
     * static {
     * Map<String, String> transferMap = new HashMap<>();
     * transferMap.put("ip", "REDIS_ADDR");
     * transferMap.put("auth", "REDIS_AUTH");
     * transferMap.put("maxActive", "REDIS_MAX_ACTIVE");
     * transferMap.put("maxIdle", "REDIS_MAX_IDLE");
     * transferMap.put("maxWait", "REDIS_MAX_WAIT");
     * transferMap.put("timeout", "REDIS_TIMEOUT");
     * transferMap.put("sentinel", "REDIS_SENTINEL");
     * }
     */

    public static void init(String configfile) {
        initConfigFromPropFile(configfile);
    }

    /**
     *
     * @param type TP_ADMIN pool count use default,otherwise use RedisUtil's setting
     * @throws IllegalArgumentException
     *
    public static void initFromRedisUtil(int type) throws IllegalArgumentException{
        RedisUtil ru = null;
        try {
            ru = ApplicationContextHepler.getBean("redisUtil", RedisUtil.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(ru==null){
            String msg = "get RedisUtil Object null";
            LOG.error(msg);
            throw new IllegalArgumentException(msg);
        }
        String sentinel = ru.getRedis_sentinel();
        RedisPoolConfig redisConfig = null;
        if (sentinel.equals("true")) {
            RedisSentinelConfig redisSentinelConfig = new RedisSentinelConfig();
            redisSentinelConfig.setType(RedisConstant.TP_SENTINAL);
            redisSentinelConfig.setClustername(ru.getRedis_clustername());
            //redisSentinelConfig.setDb(ru.get());
            redisSentinelConfig.setIp(ru.getRedis_addr());
            redisConfig = redisSentinelConfig;
        } else {
            RedisPoolConfig redisPoolConfig = new RedisPoolConfig();
            String redis_addr = ru.getRedis_addr();
            String address = redis_addr.split(":")[0];
            int port = Integer.parseInt(redis_addr.split(":")[1]);

            redisPoolConfig.setIp(address);
            redisPoolConfig.setPort(port);
            redisPoolConfig.setType(RedisConstant.TP_POOL);
            redisConfig = redisPoolConfig;
        }
        if (type != RedisConstant.TP_ADMIN) {//not for
            redisConfig.setMaxActive(Integer.parseInt(ru.getRedis_max_active()));
            redisConfig.setMaxIdle(Integer.parseInt(ru.getRedis_max_idle()));
            redisConfig.setMaxWait(Integer.parseInt(ru.getRedis_max_wait()));
        }

        if (redisConfig != null) {
            redisConfig.setAuth(ru.getRedis_auth());
            redisConfig.setTimeout(Integer.parseInt(ru.getRedis_timeout()));
        }else{
            String msg = "init redis from RedisUtil fail";
            LOG.error(msg);
            throw new IllegalArgumentException(msg);
        }
        map.put(RedisConstant.DFT_NAMESPACE,redisConfig);
    }*/

    /**
     * read properties,and parse
     *
     * @param configfile
     */
    public static void initConfigFromPropFile(String configfile) {
        String configfileWithExt = configfile + ".properties";
        if (!ConfigUtil.checkPropertiesExist(configfile)) {
            return;
        }
        String servers = ConfigUtil.get(RedisConstant.REDIS_PREFIX + ".servers", configfile);
        if (LOG.isDebugEnabled()) {
            LOG.debug("init redis servers {}", servers);
        }
        if (StringCheckUtil.isEmpty(servers)) {
            return;
        }
        String[] serverArr = servers.split(",");
        if (CollectionUtil.isEmpty(serverArr)) {
            return;
        }
        for (String server : serverArr) {
            String prefix = RedisConstant.REDIS_PREFIX + "." + server;
            try {
                String typeKey = prefix + "." + RedisConstant.KEY_TYPE;
                String type = ConfigUtil.get(typeKey, configfile);
                if (!RedisConstant.CLIENT_TYPES.contains(type)) {
                    LOG.error("redis type not support K:{},V:{}", typeKey, type);
                    continue;
                }
                Properties props = ConfigUtil.loadProperties(configfileWithExt, ConfigUtil.TP_CLASSPATH);
                RedisConfig redisConfig = null;
                switch (type) {
                    case RedisConstant.TP_SINGLE:
                        redisConfig = ConfigUtil.generateConfigModel(RedisConfig.class, prefix, props,null);
                        break;
                    case RedisConstant.TP_POOL:
                        redisConfig = ConfigUtil.generateConfigModel(RedisPoolConfig.class, prefix, props,null);
                        break;
                    case RedisConstant.TP_SENTINAL:
                        redisConfig = ConfigUtil.generateConfigModel(RedisSentinelConfig.class, prefix, props,null);
                        break;
                }
                if (redisConfig != null) {
                    map.put(server, redisConfig);
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("redis config server {} content {}", server, redisConfig);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * just for easy redis admin
     */
    public static void addConfig(String namespace, RedisConfig redisConfig) {
        if (StringCheckUtil.isEmpty(namespace)) {
            throw new IllegalArgumentException("namespace can't be null");
        }
        if (map.get(namespace) != null) {
            throw new IllegalArgumentException("namespace " + namespace + " already used");
        }
        map.put(namespace, redisConfig);
    }

    public static void deleteConfig(String namespace) {
        map.remove(namespace);
    }

    public static Map<String, RedisConfig> getAll() {
        return map;
    }

    public static RedisConfig getConfig() {
        return getConfig(null);
    }

    public static RedisConfig getConfig(String namespace) {
        RedisConfig redisConfig = map.get(namespace);
        return redisConfig;
    }
}
