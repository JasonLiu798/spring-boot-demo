package com.jason798.redis.admin.modle;

import com.jason798.redis.dto.RedisConstant;

import java.io.Serializable;
import java.util.Map;

public class HashDto extends BaseDto implements Serializable{
    private Map<String,String> values;
    private Long len;

    public HashDto() {
        this.type = RedisConstant.TP_HASH;
    }

    @Override
    public String toString() {
        return super.toString()+"HashDto{" +
                "values=" + values +
                ", len=" + len +
                '}';
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public Long getLen() {
        return len;
    }

    public void setLen(Long len) {
        this.len = len;
    }
}
