package com.jason798.redis.admin.modle;


import java.io.Serializable;

public class BaseDto implements Serializable{
    protected Long ttl;
    protected String key;
    protected String type;


    public BaseDto() {
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    @Override
    public String toString() {
        return "BaseDto{" +
                "ttl=" + ttl +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
