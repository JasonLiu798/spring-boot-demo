package com.jason798.redis.dto;

import com.jason798.character.StringCheckUtil;
import com.jason798.redis.dto.info.RedisInfoDto;
import com.jason798.redis.impl.PooledJedisFactory;
import com.jason798.redis.impl.SentinalJedisFactory;
import com.jason798.redis.impl.SingleJedisFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RedisConstant {

    /**
     * constants
     */
    public static final String TP_NONE = "none";
    public static final String TP_STR = "string";
    public static final String TP_LIST = "list";
    public static final String TP_SET = "set";
    public static final String TP_ZSET = "zset";
    public static final String TP_HASH = "hash";

    public static final int TP_ADMIN = 1;

    public static final String REDIS_IMPL_PKG = "com.jasonLiu798.redis.impl.";

    public static final String CLZ_SINGLE_JEDIS = SingleJedisFactory.class.getName();
    public static final String CLZ_POOL_JEDIS = PooledJedisFactory.class.getName();
    public static final String CLZ_SENTINAL_JEDIS = SentinalJedisFactory.class.getName();
    public static final String CLZ_REDIS_CONFIG = RedisConfig.class.getName();

    public static final String PKG_REDIS_INFO = RedisInfoDto.class.getPackage().getName();

    public static final Set<String> REDIS_DATA_TYPES = new HashSet<>();
    public static final String REDIS_PREFIX = "redis";
    public static final String DFT_REDIS_FILE = "redis";
    public static final String KEY_TYPE = "type";
    public static final String TP_SINGLE = "S";
    public static final String TP_POOL = "P";
    public static final String TP_SENTINAL = "L";
    public static final Set<String> CLIENT_TYPES = new HashSet<>();
    public static final Map<String,String> CLIENT_TYPE_M2V_MAP = new HashMap<>();
    public static final Map<String,String> CLIENT_TYPE_V2M_MAP = new HashMap<>();

    public static final String TP_SINGLE_V = "单连接模式";
    public static final String TP_POOL_V = "池模式";
    public static final String TP_SENTINAL_V = "哨兵模式";


    static {
        CLIENT_TYPE_M2V_MAP.put(TP_SINGLE,TP_SINGLE_V);
        CLIENT_TYPE_M2V_MAP.put(TP_POOL,TP_POOL_V);
        CLIENT_TYPE_M2V_MAP.put(TP_SENTINAL,TP_SENTINAL_V);

        CLIENT_TYPE_V2M_MAP.put(TP_SINGLE_V,TP_SINGLE);
        CLIENT_TYPE_V2M_MAP.put(TP_POOL_V,TP_POOL);
        CLIENT_TYPE_V2M_MAP.put(TP_SENTINAL_V,TP_SENTINAL);

        CLIENT_TYPES.add(RedisConstant.TP_SINGLE);
        CLIENT_TYPES.add(RedisConstant.TP_POOL);
        CLIENT_TYPES.add(RedisConstant.TP_SENTINAL);

        REDIS_DATA_TYPES.add(TP_STR);
        REDIS_DATA_TYPES.add(TP_HASH);
        REDIS_DATA_TYPES.add(TP_LIST);
        REDIS_DATA_TYPES.add(TP_SET);
        REDIS_DATA_TYPES.add(TP_ZSET);
    }

    public static String formatServType(String type){
        if(StringCheckUtil.isEmpty(type)){
            return "";
        }
        return CLIENT_TYPE_M2V_MAP.get(type);
    }

    public static boolean isValidType(String type) {
        if (REDIS_DATA_TYPES.contains(type) && !StringCheckUtil.equal(type, TP_NONE)) {
            return true;
        }
        return false;
    }


    public static final String DFT_NAMESPACE = "default";
}
