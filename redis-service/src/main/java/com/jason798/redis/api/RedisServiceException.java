package com.jason798.redis.api;


import com.jason798.error.CommonException;
import com.jason798.error.ErrorCode;

/**
 * redis exception for caller
 */
public class RedisServiceException extends CommonException {
    private static final long serialVersionUID = 1L;

    public RedisServiceException() {
    }

    public RedisServiceException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RedisServiceException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }

    public RedisServiceException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public RedisServiceException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }
}
