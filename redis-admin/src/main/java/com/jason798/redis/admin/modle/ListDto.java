package com.jason798.redis.admin.modle;


import java.io.Serializable;
import java.util.List;

public class ListDto extends BaseDto implements Serializable{
    private List<String> values;
    private Long len;

    public ListDto() {
    }

    public Long getLen() {
        return len;
    }

    public void setLen(Long len) {
        this.len = len;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return super.toString()+"ListDto{" +
                "values=" + values +
                ", len=" + len +
                '}';
    }
}
