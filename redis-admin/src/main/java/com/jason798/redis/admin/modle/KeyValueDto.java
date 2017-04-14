package com.jason798.redis.admin.modle;


import java.io.Serializable;

public class KeyValueDto extends BaseDto implements Serializable {
    private String value;

    public KeyValueDto() {
    }

    @Override
    public String toString() {
        return super.toString()+"KeyValueDto{" +
                "value='" + value + '\'' +
                '}';
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
