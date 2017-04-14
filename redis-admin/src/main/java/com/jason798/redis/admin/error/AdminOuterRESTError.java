package com.jason798.redis.admin.error;


import com.jason798.dto.ResponseDataDto;

public class AdminOuterRESTError {

    public static ResponseDataDto buildRedisError(Exception e) {
        ResponseDataDto resp = RedisError.REDIS_ERROR.clone();
        resp.setData(e.getMessage());
        return resp;
    }


}
