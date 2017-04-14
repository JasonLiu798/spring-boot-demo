package com.jason798.redis.admin.controller;

import com.jason798.character.StringCheckUtil;
import com.jason798.dto.ResponseDataDto;
import com.jason798.redis.admin.error.AdminOuterRESTError;
import com.jason798.redis.admin.error.RedisError;
import com.jason798.redis.admin.modle.KeyValueDto;
import com.jason798.redis.api.RedisService;
import com.jason798.redis.api.RedisServiceFactory;
import com.jason798.redis.dto.RedisConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@SuppressWarnings({"rawtypes", "unchecked"})
@RequestMapping(value = "/redis")
@Controller
public class KeyController {


    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto get(String namespace, String key) {
        String value = null;
        Long ttl = null;
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if (rs == null) {
                return RedisError.REDIS_SERVICE_NULL;
            }

            if (!rs.exists(key)) {
                return RedisError.REDIS_KEY_NOT_EXIST;
            }
            if (!StringCheckUtil.equal(rs.type(key), RedisConstant.TP_STR)) {
                return RedisError.REDIS_KEY_TYPE_ERROR;
            }
            value = rs.get(key);
            ttl = rs.ttl(key);
            KeyValueDto dto = new KeyValueDto();
            dto.setKey(key);
            dto.setValue(value);
            dto.setTtl(ttl);
            dto.setType(RedisConstant.TP_STR);
            return new ResponseDataDto(dto);
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
    }


    @RequestMapping(value = "/remove", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto remove(String namespace, String key) {
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if (rs == null) {
                return RedisError.REDIS_SERVICE_NULL;
            }
            if (!rs.exists(key)) {
                return RedisError.REDIS_KEY_NOT_EXIST;
            }
            rs.remove(key);
            return new ResponseDataDto();
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
    }


    @RequestMapping(value = "/set", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto set(String namespace, String type, String key, String value, String field, Integer ttl) {
        if (!RedisConstant.isValidType(type)) {
            return RedisError.REDIS_UNKNOWN_TYPE;
        }
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if (rs == null) {
                return RedisError.REDIS_SERVICE_NULL;
            }

            //string key can't exist
            boolean exist = rs.exists(key);
            if (type.equals(RedisConstant.TP_STR) && exist) {
                return RedisError.REDIS_KEY_EXIST;
            }

            switch (type) {
                case RedisConstant.TP_STR:
                    rs.set(key, value, ttl);
                    break;
                case RedisConstant.TP_LIST:
                    rs.lpush(key, value);
                    break;
                case RedisConstant.TP_HASH:
                    rs.hset(key, field, value);
                    Long hsize = rs.hlen(key);
                    if (hsize != null && hsize == 1) {
                        rs.expire(key, ttl);
                    }
                    break;
                case RedisConstant.TP_SET:
                    rs.sadd(key, value);
                    Long ssize = rs.scard(key);
                    if (ssize != null && ssize == 1) {
                        rs.expire(key, ttl);
                    }
                    break;
            }
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
        return new ResponseDataDto();
    }

    @RequestMapping(value = "/expire", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto expire(String namespace, String key, Integer ttl) {
        Long res = null;
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if (rs == null) {
                return RedisError.REDIS_SERVICE_NULL;
            }
            if (!rs.exists(key)) {
                return RedisError.REDIS_KEY_NOT_EXIST;
            }
            res = rs.expire(key, ttl);
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
        return new ResponseDataDto(res);
    }


}
