package com.jason798.redis.admin.controller;

import com.jason798.character.StringCheckUtil;
import com.jason798.collection.CollectionUtil;
import com.jason798.dto.ResponseDataDto;
import com.jason798.redis.admin.error.AdminOuterRESTError;
import com.jason798.redis.admin.error.RedisError;
import com.jason798.redis.api.RedisServiceFactory;
import com.jason798.redis.dto.RedisConfig;
import com.jason798.redis.dto.RedisConstant;
import org.hibernate.validator.internal.util.CollectionHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


@SuppressWarnings({"rawtypes", "unchecked"})
@RequestMapping(value = "/redis/server")
@Controller
public class ServerController {

    @RequestMapping(value = "/servers", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto servers() {
        try {
            Map<String, RedisConfig> map = RedisServiceFactory.getServers();
            //format type
            Map<String, RedisConfig> nmap = CollectionUtil.copyMap(map);//clone redisConfig,
            for (Map.Entry<String, RedisConfig> entry : nmap.entrySet()) {
                RedisConfig rc = entry.getValue();
                rc.setType(RedisConstant.formatServType(rc.getType()));
            }
            return new ResponseDataDto(nmap);
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
    }

    /**
     * 暂时只支持single模式用于测试环境
     *
     * @param namespace namespace
     * @param ip        ip
     * @param port      port
     * @param auth      auth
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto add(String namespace, String ip, Integer port, String auth) {
        if (StringCheckUtil.isExistEmpty(namespace, ip, String.valueOf(port))) {
            return null;//AdminOuterRESTError.PARAMETER_FORMAT_ERROR;
        }
        try {
            RedisConfig redisConfig = new RedisConfig();
            redisConfig.setType(RedisConstant.TP_SINGLE);
            redisConfig.setIp(ip);
            redisConfig.setPort(port);
            if (StringCheckUtil.isNotEmpty(auth)) {
                redisConfig.setAuth(auth);
            }
            RedisServiceFactory.add(namespace, redisConfig);
            return new ResponseDataDto();
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto delete(String namespace) {
        try {
            if (namespace.equals(RedisConstant.DFT_NAMESPACE)) {
                return RedisError.REDIS_SERVICE_DFT_CANNOT_DEL;
            }
            RedisServiceFactory.delete(namespace);
            return new ResponseDataDto();
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
    }

}
