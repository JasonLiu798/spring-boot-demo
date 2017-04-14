package com.jason798.redis.admin.controller;

import com.jason798.character.StringCheckUtil;
import com.jason798.dto.ResponseDataDto;
import com.jason798.redis.admin.error.AdminOuterRESTError;
import com.jason798.redis.admin.error.RedisError;
import com.jason798.redis.admin.modle.ListDto;

import com.jason798.redis.api.RedisService;
import com.jason798.redis.api.RedisServiceFactory;
import com.jason798.redis.dto.RedisConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
@RequestMapping(value = "/redis")
@Controller
public class ListController {

    //Logger LOG = LoggerFactory.getLogger(RedisController.class);

    /**
     *
     * @param namespace
     * @param type L/R
     * @param key
     * @param value
     * @param ttl
     * @return
     */
    @RequestMapping(value = "/ladd", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto ladd(String namespace, String type, String key, String value, Integer ttl) {
        List<String> list = null;
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if(rs==null){
                return RedisError.REDIS_SERVICE_NULL;
            }
            if(type.equals("L")){
                rs.lpush(key,value);
            }else{
                rs.rpush(key,value);
            }
            if(rs.llen(key)!=null && rs.llen(key)==1){
                rs.expire(key,ttl);
            }
            return new ResponseDataDto();
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
    }

    @RequestMapping(value = "/lrange", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto lrange(String namespace,String key, Integer start, Integer end) {
        List<String> list = null;
        Long ttl = null;
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if(rs==null){
                return RedisError.REDIS_SERVICE_NULL;
            }

            if (!rs.exists(key)) {
                return RedisError.REDIS_KEY_NOT_EXIST;
            }
            if (!StringCheckUtil.equal(rs.type(key), RedisConstant.TP_LIST)) {
                return RedisError.REDIS_KEY_TYPE_ERROR;
            }
            if (start != null && end != null) {
                list = rs.lrange(key, start, end);
            } else if (start != null) {
                list = rs.lrange(key, start);
            } else if (end != null) {
                list = rs.lrangeLimit(key, end);
            } else {
                // parameter error
                list = rs.lrangeLimit(key, 10);
                //return AdminOuterRESTError.PARAMETER_FORMAT_ERROR;
            }
            ttl = rs.ttl(key);
            ListDto dto = new ListDto();
            dto.setKey(key);
            dto.setTtl(ttl);
            dto.setValues(list);
            dto.setType(RedisConstant.TP_LIST);
            dto.setLen(rs.llen(key));
            return new ResponseDataDto(dto);
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
    }



}
