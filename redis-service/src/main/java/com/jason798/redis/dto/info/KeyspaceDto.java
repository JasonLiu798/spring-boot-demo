package com.jason798.redis.dto.info;

import com.jason798.common.ReflectUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class KeyspaceDto implements Serializable {
    private String keys;
    private String expires;
    private String avg_ttl;

    /**
     * Keyspace={db0=keys=6,expires=0,avg_ttl=0},
     * @param kv
     * @return
     */
    public static Map<String,KeyspaceDto> build(Map<String,Object> kv){
        Map<String,KeyspaceDto> res = new HashMap<>();
        for(Map.Entry<String,Object> entry:kv.entrySet()){
//            res.put(entry.getKey(),)
            String ksName = entry.getKey();
            String ksValue = String.valueOf(entry.getValue());
            String[] ksKvArr=ksValue.split(",");
            KeyspaceDto keyspaceDto = new KeyspaceDto();
            Map<String,Object> dbKv = new HashMap<>();
            for(String ksKv:ksKvArr){
                String[] kvArr = ksKv.split("=");
                if(kvArr.length==2) {
                    dbKv.put(kvArr[0], kvArr[1]);
                }else{
                    dbKv.put(kvArr[0], "");
                }
            }
            ReflectUtil.setBean(dbKv,keyspaceDto);
            res.put(ksName,keyspaceDto);
        }
        return res;
    }

    public KeyspaceDto() {
    }

    @Override
    public String toString() {
        return "KeyspaceDto{" +
                "keys='" + keys + '\'' +
                ", expires='" + expires + '\'' +
                ", avg_ttl='" + avg_ttl + '\'' +
                '}';
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getAvg_ttl() {
        return avg_ttl;
    }

    public void setAvg_ttl(String avg_ttl) {
        this.avg_ttl = avg_ttl;
    }
}
