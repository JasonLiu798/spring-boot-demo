package com.jason798.redis.admin.modle;

import com.jason798.redis.dto.RedisConstant;

import java.io.Serializable;
import java.util.Set;

public class SetDto extends BaseDto implements Serializable{
    private Set<String> values;
    private Long len;

    public SetDto() {
        this.type = RedisConstant.TP_SET;
    }

    @Override
    public String toString() {
        return "SetDto{" +
                "values=" + values +
                ", len=" + len +
                '}';
    }

    public Set<String> getValues() {
        return values;
    }

    public void setValues(Set<String> values) {
        this.values = values;
    }

    public Long getLen() {
        return len;
    }

    public void setLen(Long len) {
        this.len = len;
    }
}
