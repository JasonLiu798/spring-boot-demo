package com.jason798.redis.admin.modle;


import com.jason798.redis.dto.RedisConstant;

import java.io.Serializable;
import java.util.Map;

public class ZsetDto extends BaseDto implements Serializable {
    /**
     * [K:field,[K:value,V:score]]
     */
    private Map<String, Map<String, Long>> values;
    private Long len;

    public ZsetDto() {
        this.type = RedisConstant.TP_ZSET;
    }

    @Override
    public String toString() {
        return "ZsetDto{" +
                "values=" + values +
                ", len=" + len +
                '}';
    }

    public Map<String, Map<String, Long>> getValues() {
        return values;
    }

    public void setValues(Map<String, Map<String, Long>> values) {
        this.values = values;
    }

    public Long getLen() {
        return len;
    }

    public void setLen(Long len) {
        this.len = len;
    }
}
