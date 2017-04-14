package com.jason798.redis.impl;

import com.jason798.redis.api.RedisService;
import com.jason798.redis.api.RedisServiceFactory;
import com.jason798.redis.dto.info.RedisInfoDto;
import org.junit.*;
import org.junit.rules.ExpectedException;
import redis.clients.jedis.Jedis;


public class SingleJedisFactoryTest {


    @Test
    public void testGetResource() throws Exception {

        RedisServiceFactory.init("redis");
        RedisService redisService = RedisServiceFactory.getRedisService("a");
//        RedisService redisServicep = RedisServiceFactory.getRedisService("p");
//        RedisService redisServices = RedisServiceFactory.getRedisService("s");

        for(int i=0;i<100;i++) {
        }

        /*
        s = redisServicep.get("EIMS-INV-REQ-QTY");
        System.out.println(s);

        s = redisServices.get("EIMS-INV-REQ-QTY");
        System.out.println(s);*/
    }
    
    @Test
    public void testReturnResource() throws Exception {

        Jedis jedis = new Jedis("", 6379);
        jedis.auth("redis");
        long t = System.currentTimeMillis();
        for(int i=0;i<100;i++){
//            System.out.println(s);
        }
        jedis.close();
        long d = System.currentTimeMillis() - t;
        System.out.println(d);
    }
    
    @Test
    public void testToString() throws Exception {
        System.out.println(RedisInfoDto.class.getPackage().getName());


    }
    
    @Test
    public void testGetConfig() throws Exception { 
        
    }
    
    @Test
    public void testSetConfig() throws Exception { 
        
    }
    
    @Test
    public void testInit() throws Exception { 
        
    }
    
    @Test
    public void testDestroy() throws Exception { 
        
    }
    
        
    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    }
    
    @BeforeClass
    public static void beforeClass() throws Exception{
        
    }
    
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
