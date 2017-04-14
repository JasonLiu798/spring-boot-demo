package com.jason798.redis.api;


import com.jason798.error.ErrorCode;

public class RedisServiceErrorCode {
    public static ErrorCode REDIS_CLIENT_NOT_INITIALIZED = new ErrorCode("S01001", "Redis Client尚未初始化", "请查看错误信息");

    public static ErrorCode REDIS_EXECUTE_FAIL_ERROR = new ErrorCode("S01002", "Redis服务执行错误", "请查看错误信息");

    public static ErrorCode REDIS_CONFIG_ERROR = new ErrorCode("S01003", "Redis服务执行错误", "请查看错误信息");
}
