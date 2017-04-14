package com.jason798.redis.admin.error;


import com.jason798.dto.ResponseDataDto;

/**
 * redis error
 */
public class RedisError {

    /**
     * redis admin
     */
    public static final ResponseDataDto REDIS_KEY_NOT_EXIST = new ResponseDataDto(RedisErrorCode.REDIS_KEY_NOT_EXIST, RedisErrorCode.REDIS_KEY_NOT_EXIST_MSG);
    public static final ResponseDataDto REDIS_KEY_EXIST = new ResponseDataDto(RedisErrorCode.REDIS_KEY_EXIST, RedisErrorCode.REDIS_KEY_EXIST_MSG);
    public static final ResponseDataDto REDIS_KEY_TYPE_ERROR = new ResponseDataDto(RedisErrorCode.REDIS_KEY_TYPE_ERROR, RedisErrorCode.REDIS_KEY_TYPE_ERROR_MSG);
    public static final ResponseDataDto REDIS_UNKNOWN_TYPE = new ResponseDataDto(RedisErrorCode.REDIS_UNKNOWN_TYPE, RedisErrorCode.REDIS_UNKNOWN_TYPE_MSG);
    public static final ResponseDataDto REDIS_ERROR = new ResponseDataDto(RedisErrorCode.REDIS_ERROR, RedisErrorCode.REDIS_ERROR_MSG);
    public static final ResponseDataDto REDIS_SERVICE_NULL = new ResponseDataDto(RedisErrorCode.REDIS_SERVICE_NULL, RedisErrorCode.REDIS_SERVICE_NULL_MSG);
    public static final ResponseDataDto REDIS_SERVICE_DFT_CANNOT_DEL = new ResponseDataDto(RedisErrorCode.REDIS_SERVICE_DFT_CANNOT_DEL, RedisErrorCode.REDIS_SERVICE_DFT_CANNOT_DEL_MSG);
}
