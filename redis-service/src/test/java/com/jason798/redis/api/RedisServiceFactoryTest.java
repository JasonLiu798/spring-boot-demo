package com.jason798.redis.api;

import com.jason798.common.SystemUtil;
import com.jason798.redis.dto.RedisConfig;
import com.jason798.redis.dto.RedisConstant;
import com.jason798.redis.dto.RedisSentinelConfig;
import com.jason798.redis.dto.info.RedisInfoDto;
import com.jason798.redis.impl.SingleJedisFactory;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class RedisServiceFactoryTest {


    @Test
    public void testGetSingle() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"ac-redis.xml"});
        //single
        RedisService redisService = RedisServiceFactory.getRedisService("a");

        long t = System.currentTimeMillis();
        for(int i=0;i<100;i++) {
            String s = redisService.get("aa");
            System.out.println(s);
        }
        long d = System.currentTimeMillis()- t;
        System.out.println("cost "+d);
    }

    @Test
    public void testGetPool() throws Exception {
//        SystemUtil.addClasspath();
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"ac-redis.xml"});
        //pool
        RedisService redisServicep = RedisServiceFactory.getRedisService("p");

        long t = System.currentTimeMillis();
        for(int i=0;i<100;i++) {
            String s = redisServicep.get("aa");
            System.out.println(s);
        }
        long d = System.currentTimeMillis()- t;
        System.out.println("cost "+d);

    }

    @Test
    public void testGetSentinal() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"ac-redis.xml"});
        //sentinal
        RedisService redisServices = RedisServiceFactory.getRedisService("s");
        long t = System.currentTimeMillis();
        for(int i=0;i<100;i++) {
            String s = redisServices.get("aa");
            System.out.println(s);
        }
        long d = System.currentTimeMillis()- t;
        System.out.println("cost "+d);
    }

    @Test
    public void testGetDft() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"ac-redis.xml"});

        RedisService redisServiceDft = RedisServiceFactory.getRedisService(RedisConstant.DFT_NAMESPACE);
        long t = System.currentTimeMillis();
        for(int i=0;i<100;i++) {
            String s = redisServiceDft.get("aa");
            System.out.println(s);
        }
        long d = System.currentTimeMillis()- t;
        System.out.println("cost "+d);

    }


    @Test
    public void addRedis() {
        /**
         redis.a.type=S
         redis.a.ip=10.202.125.15
         redis.a.port=6379
         redis.a.auth=redis
         */

        String skey = "c";
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setIp("10.202.125.15");
        redisConfig.setPort(6379);
        redisConfig.setType(RedisConstant.TP_SINGLE);
        redisConfig.setAuth("redis");
        RedisServiceFactory.add(skey, redisConfig);

        String pkey = "s";
        RedisSentinelConfig sredisConfig = new RedisSentinelConfig();
        sredisConfig.setIp("10.202.37.147:8001|10.202.37.147:8002|10.202.37.150:8001");
        sredisConfig.setType(RedisConstant.TP_SENTINAL);
        sredisConfig.setClustername("ESG_EIMS_CORE_CNSZ22_REDIS");
        sredisConfig.setAuth("admin.123");
        RedisServiceFactory.add(pkey, sredisConfig);

        String key = "aa";
        long t = System.currentTimeMillis();
        int excep = 0;
        for (int i = 0; i < 1000; i++) {
            try {
                RedisService redisService = RedisServiceFactory.getRedisService(skey);
                RedisService redisService2 = RedisServiceFactory.getRedisService(pkey);
                String val = redisService.get(key);
                System.out.println(val);
                val = redisService2.get(key);
                System.out.println(val);
            }catch(Exception e){
                excep++;
                System.out.println(e);
            }
        }
        long d = System.currentTimeMillis() - t;
        System.out.println("cost "+d+",error "+excep);
    }

    @Test
    public void testDeleteNamespace(){
        String skey = "c";
        RedisConfig redisConfig = new RedisConfig();
        redisConfig.setIp("5");
        redisConfig.setPort(6379);
        redisConfig.setType(RedisConstant.TP_SINGLE);
        redisConfig.setAuth("redis");
        RedisServiceFactory.add(skey, redisConfig);

        String pkey = "s";
        RedisSentinelConfig sredisConfig = new RedisSentinelConfig();
        sredisConfig.setIp("7:8001|2:8002|3:8001");
        sredisConfig.setType(RedisConstant.TP_SENTINAL);
        sredisConfig.setClustername("aaa");
        sredisConfig.setAuth("pass");
        RedisServiceFactory.add(pkey, sredisConfig);


        String key = "aa";
        long t = System.currentTimeMillis();
        int excep = 0;
        for (int i = 0; i < 2; i++) {
            try {
                RedisService redisService = RedisServiceFactory.getRedisService(skey);
                RedisService redisService2 = RedisServiceFactory.getRedisService(pkey);
                String val = redisService.get(key);
                System.out.println(val);
                val = redisService2.get(key);
                System.out.println(val);
            }catch(Exception e){
                excep++;
                System.out.println(e);
            }
        }
        long d = System.currentTimeMillis() - t;
        System.out.println("cost "+d+",error "+excep);

        Map map = RedisServiceFactory.getServers();
        System.out.println("before:"+map);
        RedisServiceFactory.delete(skey);
        map = RedisServiceFactory.getServers();
        System.out.println("after:"+map);
        try {
            RedisService rs = RedisServiceFactory.getRedisService(skey);
            System.out.println(rs);
        }catch (Exception e){
            System.out.println("deleted redis service ");
        }

    }

    @Test
    public void testGetRedisServiceNamespace() throws Exception {
        System.out.println(SingleJedisFactory.class.getName());


    }


    @Test
    public void testInfo() throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"ac-redis.xml"});

        RedisService redisServiceDft = RedisServiceFactory.getRedisService(RedisConstant.DFT_NAMESPACE);
        RedisInfoDto info = redisServiceDft.info();
        System.out.println(info);
    }

    @Test
    public void testGet() throws Exception {

    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
