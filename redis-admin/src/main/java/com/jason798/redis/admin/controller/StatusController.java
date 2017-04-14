package com.jason798.redis.admin.controller;


import com.jason798.dto.ResponseDataDto;
import com.jason798.redis.admin.error.AdminOuterRESTError;
import com.jason798.redis.admin.error.RedisError;
import com.jason798.redis.api.RedisService;
import com.jason798.redis.api.RedisServiceFactory;
import com.jason798.redis.dto.SlowLogDto;
import com.jason798.redis.dto.info.RedisInfoDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.Set;

@SuppressWarnings({"rawtypes", "unchecked"})
@RequestMapping(value = "/redis")
@Controller
public class StatusController {

    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto info(String namespace) {
        RedisInfoDto info = null;
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if(rs==null){
                return RedisError.REDIS_SERVICE_NULL;
            }
            info = rs.info();
            return new ResponseDataDto(info);
        }catch (Exception e){
            return AdminOuterRESTError.buildRedisError(e);
        }

    }

    @RequestMapping(value = "/slowlog", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto slowlog(String namespace,Integer n) {
        List<SlowLogDto> list = null;
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if(rs==null){
                return RedisError.REDIS_SERVICE_NULL;
            }
            if (n == null) {
                list = rs.slowLog();
            } else {
                list = rs.slowLog(n);
            }
            return new ResponseDataDto(list);
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
    }

    @RequestMapping(value = "/keys", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto keys(String namespace,String pattern) {
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if (rs == null) {
                return RedisError.REDIS_SERVICE_NULL;
            }
            Set<String> stringSet = rs.keys(pattern);
            return new ResponseDataDto(stringSet);
        }catch (Exception e){
            return AdminOuterRESTError.buildRedisError(e);
        }
    }


}
