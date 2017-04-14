package com.jason798.redis.admin.controller;

import com.jason798.character.StringCheckUtil;
import com.jason798.dto.ResponseDataDto;
import com.jason798.redis.admin.error.AdminOuterRESTError;
import com.jason798.redis.admin.error.RedisError;
import com.jason798.redis.admin.modle.SetDto;
import com.jason798.redis.api.RedisService;
import com.jason798.redis.api.RedisServiceFactory;
import com.jason798.redis.dto.RedisConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Set;

@SuppressWarnings({"rawtypes", "unchecked"})
@RequestMapping(value = "/redis")
@Controller
public class SetController {

    @RequestMapping(value = "/smembers", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto smemebers(String namespace, String key) {
        SetDto dto = new SetDto();
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if(rs==null){
                return RedisError.REDIS_SERVICE_NULL;
            }

            if (!rs.exists(key)) {
                return RedisError.REDIS_KEY_NOT_EXIST;
            }
            if (!StringCheckUtil.equal(rs.type(key), RedisConstant.TP_SET)) {
                return RedisError.REDIS_KEY_TYPE_ERROR;
            }
            Set<String> res = rs.smembers(key);
            dto.setValues(res);
            dto.setTtl(rs.ttl(key));
            dto.setLen(rs.scard(key));
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
        return new ResponseDataDto(dto);
    }


}
