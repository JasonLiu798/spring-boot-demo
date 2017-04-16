package com.jason798.redis.api;

import com.jason798.common.ReflectUtil;
import com.jason798.redis.dto.RedisConfig;
import com.jason798.redis.dto.RedisConstant;
import com.jason798.redis.impl.RedisConfigHolder;
import com.jason798.redis.impl.RedisServiceImpl;
import com.jason798.redis.spi.JedisFactory;
import com.jason798.spi.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * # usage
 * ## simple way
 * write redis.properties,format @RedisConfigHolder
 * RedisService rs = RedisServiceFactory.getRedisService();
 * or
 * RedisService rs = RedisServiceFactory.getRedisService("serv1");
 * default operations:
 * init redis from redis.properties(if exist)
 *
 * ## manual way
 * ### specify config file name
 * RedisServiceFactory.init("test");//use test.properties init
 * ### not add RedisUtil use namespace "default"
 * RedisServiceFactory.addDefaultRedisUtil();//also optional
 * then
 * RedisService rs = RedisServiceFactory.getRedisService();
 * or
 * RedisService rs = RedisServiceFactory.getRedisService("serv1");
 *
 * the functions above,need manual call,and add this before program running,
 * For example,when use spring,implement ApplicationListener<ContextRefreshedEvent>.
 * <context:component-scan base-package="com.sf.inv.redis.api" />
 *
 */
public class RedisServiceFactory {

    private static final Logger LOG = LoggerFactory.getLogger(RedisServiceFactory.class);

    /**
     * K:namespace
     * V:RedisService instantce
     */
    private static Map<String, RedisService> instanceMap = new ConcurrentHashMap<>();

    private static Status status = Status.INIT;

    /**
     * for manual init
     */
    public static void init() {
        init(RedisConstant.DFT_REDIS_FILE);
        //addDefaultRedisUtil();
    }

    /**
     * must call before use
     *
     * @param configfile filename,not include extend name
     */
    public static void init(String configfile) {
        RedisConfigHolder.init(configfile);
        status = Status.RUNNING;
    }

    public static RedisService getRedisService() {
        return getRedisService(null);
    }

    public static Set<String> getKeys() {
        if (instanceMap == null) {
            return null;
        }
        return instanceMap.keySet();
    }

    public static Map<String, RedisConfig> getServers() {
        return RedisConfigHolder.getAll();
    }

    /**
     * add config to get
     *
     * @param namespace
     * @param redisConfig
     */
    public static void add(String namespace, RedisConfig redisConfig) {
        RedisConfigHolder.addConfig(namespace, redisConfig);
    }

    public static void delete(String namespace) {
        //delete config
        RedisConfigHolder.deleteConfig(namespace);
        RedisService redisService = instanceMap.get(namespace);
        JedisFactory factory = redisService.getRedisFactory();
        factory.destroy();//destroy jedis factory
        //delete redisService
        instanceMap.remove(namespace);
    }

    public static JedisFactory getJedisFactory(String namespace) {
        RedisService redisService = instanceMap.get(namespace);
        return redisService.getRedisFactory();
    }


    public static RedisService getRedisService(String namespace) {
        return get(namespace);
    }

    private static RedisService get(String namespace) {
        if (null == namespace) {
            namespace = RedisConstant.DFT_NAMESPACE;
        }

        RedisService instance = instanceMap.get(namespace);
        if (instance == null) {
            synchronized (RedisServiceFactory.class) {
                if (status == Status.INIT) {
                    init();//init config from redis.properties
                }
                instance = instanceMap.get(namespace);
                if (instance == null) {
                    //init RedisService
                    try {
                        String serviceImplClassName = null;
                        RedisConfig redisConfig = RedisConfigHolder.getConfig(namespace);
                        if (redisConfig == null) {
                            throw new IllegalStateException("get redis config null");
                        }
                        String type = redisConfig.getType();
                        switch (type) {
                            case RedisConstant.TP_SINGLE:
                                serviceImplClassName = RedisConstant.CLZ_SINGLE_JEDIS;
                                break;
                            case RedisConstant.TP_POOL:
                                serviceImplClassName = RedisConstant.CLZ_POOL_JEDIS;
                                break;
                            case RedisConstant.TP_SENTINAL:
                                serviceImplClassName = RedisConstant.CLZ_SENTINAL_JEDIS;
                                break;
                            default:
                                throw new IllegalStateException("redis config type " + type + " not support");
                        }
                        Class<?> clzJedisFactory = Class.forName(serviceImplClassName);
                        if (!JedisFactory.class.isAssignableFrom(clzJedisFactory)) {
                            throw new IllegalStateException("set implementation class " + clzJedisFactory
                                    + " is not subclass of " + RedisService.class.getName());
                        }
                        //Class<?> paramClz = Class.forName(RedisConstant.CLZ_REDIS_CONFIG);
//                        Constructor constructor = clzJedisFactory.getConstructor(paramClz);
//                        Object jedisFactoryObj = constructor.newInstance(redisConfig);

                        Object jedisFactoryObj = clzJedisFactory.newInstance();
                        JedisFactory jedisFactory = (JedisFactory) jedisFactoryObj;
                        //set config
                        ReflectUtil.setter(jedisFactory,"config",RedisConfig.class,redisConfig);
                        jedisFactory.init();
                        RedisServiceImpl redisServiceImpl = new RedisServiceImpl(jedisFactory);
                        instanceMap.put(namespace, redisServiceImpl);
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("init redis service {},use factory {}", redisServiceImpl, jedisFactory);
                        }
                        instance = redisServiceImpl;
                    } catch (Exception e) {
                        String msg = "Fail to create RedisService instance, cause: " + e.getMessage();
                        LOG.error(msg, e);
                        throw new IllegalStateException(msg, e);
                    }
                }
            }
        }
        return instance;
    }

}
