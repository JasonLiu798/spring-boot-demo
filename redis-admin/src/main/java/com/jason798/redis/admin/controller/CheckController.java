package com.jason798.redis.admin.controller;


import com.jason798.character.StringCheckUtil;
import com.jason798.dto.ResponseDataDto;
import com.jason798.redis.admin.error.AdminOuterRESTError;
import com.jason798.redis.admin.error.RedisError;
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
public class CheckController {

    @RequestMapping(value = "/exist", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto exist(String namespace, String key) {
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if(rs==null){
                return RedisError.REDIS_SERVICE_NULL;
            }
            if (rs.exists(key)) {
                return RedisError.REDIS_KEY_EXIST;
            }
            return RedisError.REDIS_KEY_NOT_EXIST;
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
    }

    @RequestMapping(value = "/type", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto type(String namespace,String key) {
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if (StringCheckUtil.isEmpty(key) || !rs.exists(key)) {
                return RedisError.REDIS_KEY_NOT_EXIST;
            }
            if(rs==null){
                return RedisError.REDIS_SERVICE_NULL;
            }
            String type = rs.type(key);
            return new ResponseDataDto(type);
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
    }

    @RequestMapping(value = "/types", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto types() {
        return new ResponseDataDto(RedisConstant.REDIS_DATA_TYPES);
    }


}
