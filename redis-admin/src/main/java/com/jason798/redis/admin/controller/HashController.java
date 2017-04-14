package com.jason798.redis.admin.controller;

import com.jason798.character.StringCheckUtil;
import com.jason798.dto.ResponseDataDto;
import com.jason798.redis.admin.error.RedisError;
import com.jason798.redis.admin.modle.HashDto;

import com.jason798.redis.api.RedisService;
import com.jason798.redis.api.RedisServiceFactory;
import com.jason798.redis.dto.RedisConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
@RequestMapping(value = "/redis")
@Controller
public class HashController {

    /**
     * @param key
     * @param field
     * @param type  =0 get field,1 get all
     * @return
     */
    @RequestMapping(value = "/hget", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto hget(String namespace, String key, String field, int type) {
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if(rs==null){
                return RedisError.REDIS_SERVICE_NULL;
            }

            if (!rs.exists(key)) {
                return RedisError.REDIS_KEY_NOT_EXIST;
            }
            if (!StringCheckUtil.equal(rs.type(key), RedisConstant.TP_HASH)) {
                return RedisError.REDIS_KEY_TYPE_ERROR;
            }
            HashDto dto = new HashDto();
            if (type == 0) {
                String value = rs.hget(key, field);
                Map<String, String> valueMap = new HashMap<>();
                valueMap.put(field, value);
                dto.setValues(valueMap);
                long len = rs.hlen(key);
                dto.setLen(len);
            } else {
                Map<String, String> valueMap = rs.hgetAll(key);
                dto.setValues(valueMap);
                dto.setLen(new Long(valueMap.size()));
            }
            dto.setTtl(rs.ttl(key));
            return new ResponseDataDto(dto);
        } catch (Exception e) {
            return RedisError.REDIS_ERROR;
        }
    }


}
