package com.jason798.redis.admin.controller;


import com.jason798.dto.ResponseDataDto;
import com.jason798.redis.admin.error.AdminOuterRESTError;
import com.jason798.redis.admin.error.RedisError;
import com.jason798.redis.api.RedisService;
import com.jason798.redis.api.RedisServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@SuppressWarnings({"rawtypes", "unchecked"})
@RequestMapping(value = "/redis")
@Controller
public class RedisController {

    //Logger LOG = LoggerFactory.getLogger(RedisController.class);

    @RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("redis/index");
        return modelAndView;
    }

    @RequestMapping(value = "/eval", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ResponseDataDto eval(String namespace, String script) {
        Object res = null;
        try {
            RedisService rs = RedisServiceFactory.getRedisService(namespace);
            if(rs==null){
                return RedisError.REDIS_SERVICE_NULL;
            }
            res = rs.eval(script);
        } catch (Exception e) {
            return AdminOuterRESTError.buildRedisError(e);
        }
        return new ResponseDataDto(res);
    }

}
