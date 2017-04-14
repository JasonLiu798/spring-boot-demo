package com.jason798.redis.admin.error;

/**
 * ##inv-redis-admin
 * 范围：13000~13999 (total 1000)
 */
public class RedisErrorCode {

    /**
     * redis admin
     */
    public static final int REDIS_KEY_NOT_EXIST = 13000;
    public static final String REDIS_KEY_NOT_EXIST_MSG = "redis key不存在";
    public static final int REDIS_KEY_TYPE_ERROR = 13001;
    public static final String REDIS_KEY_TYPE_ERROR_MSG = "redis key 类型错误";
    public static final int REDIS_ERROR = 13002;
    public static final String REDIS_ERROR_MSG = "redis 执行错误";
    public static final int REDIS_KEY_EXIST = 13003;
    public static final String REDIS_KEY_EXIST_MSG = "redis key已经存在";
    public static final int REDIS_UNKNOWN_TYPE = 13004;
    public static final String REDIS_UNKNOWN_TYPE_MSG = "未知的redis类型";
    public static final int REDIS_SERVICE_NULL = 13005;
    public static final String REDIS_SERVICE_NULL_MSG = "redis服务为空";
    public static final int REDIS_SERVICE_DFT_CANNOT_DEL = 13006;
    public static final String REDIS_SERVICE_DFT_CANNOT_DEL_MSG = "默认redis实例不能删除";

}
