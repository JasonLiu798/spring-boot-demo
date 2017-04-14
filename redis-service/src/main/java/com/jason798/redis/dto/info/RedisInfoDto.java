package com.jason798.redis.dto.info;

import com.jason798.character.StringCheckUtil;
import com.jason798.character.StringUtil;
import com.jason798.common.ReflectUtil;
import com.jason798.redis.dto.RedisConstant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class RedisInfoDto implements Serializable {
    private String raw;

    private ServerDto server;

    private ClientsDto clients;

    private MemoryDto memory;
    private PersistenceDto persistence;
    private StatsDto stats;
    private ReplicationDto replication;

    private CPUDto CPU;
    private Map<String, KeyspaceDto> keyspaceMap;

    public RedisInfoDto() {
    }

    /**
     * split raw string to map
     *
     * @param infoRawStr
     * @return
     */
    public static Map<String, Map<String, Object>> split(String infoRawStr) {
        String[] infos = infoRawStr.split("\\r\\n|\\n|\\r");
        Map<String, Map<String, Object>> infoMap = new HashMap<>();

        //init value
        String segName = "";
        Map<String, Object> segKeyValue = null;

        for (int i = 0; i < infos.length; i++) {
            String info = infos[i];
            if (info.startsWith("#")) {
                //newSeg = true;
                segName = info.substring(2);
                segKeyValue = new HashMap<>();
                int j = i + 1;
                while (j < infos.length && StringCheckUtil.isNotEmpty(infos[j])) {
                    //System.out.println("info j " + infos[j]);
                    if (StringCheckUtil.isEmpty(infos[j]) || infos[j].indexOf(":") < 0) {
                        j++;
                        continue;
                    }
                    String[] kvArr = infos[j].split(":");
                    if (kvArr.length == 2) {
                        segKeyValue.put(kvArr[0], kvArr[1]);
                    } else {
                        segKeyValue.put(kvArr[0], "");
                    }
                    j++;
                }
                i = j;
                infoMap.put(segName, segKeyValue);
            }
        }
        return infoMap;
    }

    public static RedisInfoDto build(Map<String, Map<String, Object>> map) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        RedisInfoDto res = new RedisInfoDto();
        for (Map.Entry<String, Map<String, Object>> seg : map.entrySet()) {
            String segName = seg.getKey();
            if (segName.equals("Keyspace")) {
                Map<String, KeyspaceDto> kss = KeyspaceDto.build(seg.getValue());
                res.setKeyspaceMap(kss);
                continue;
            }
            Class clz = Class.forName(RedisConstant.PKG_REDIS_INFO+"." + segName + "Dto");
            Object obj = clz.newInstance();
            Map<String, Object> kvMap = seg.getValue();
            ReflectUtil.setBean(kvMap, obj);
            ReflectUtil.setter(res, StringUtil.toLowerCaseFirstOne(segName) , clz, obj);
        }
        return res;
    }


    public ServerDto getServer() {
        return server;
    }

    public void setServer(ServerDto server) {
        this.server = server;
    }

    public ClientsDto getClients() {
        return clients;
    }

    public void setClients(ClientsDto clientsStatus) {
        this.clients = clientsStatus;
    }

    public MemoryDto getMemory() {
        return memory;
    }

    public void setMemory(MemoryDto memory) {
        this.memory = memory;
    }

    public PersistenceDto getPersistence() {
        return persistence;
    }

    public void setPersistence(PersistenceDto persistence) {
        this.persistence = persistence;
    }

    public StatsDto getStats() {
        return stats;
    }

    public void setStats(StatsDto stats) {
        this.stats = stats;
    }

    public ReplicationDto getReplication() {
        return replication;
    }

    public void setReplication(ReplicationDto replication) {
        this.replication = replication;
    }

    public CPUDto getCPU() {
        return CPU;
    }

    public void setCPU(CPUDto CPU) {
        this.CPU = CPU;
    }

    public Map<String, KeyspaceDto> getKeyspaceMap() {
        return keyspaceMap;
    }

    public void setKeyspaceMap(Map<String, KeyspaceDto> keyspaceMap) {
        this.keyspaceMap = keyspaceMap;
    }

    @Override
    public String toString() {
        return "RedisInfoDto{" +
                "raw='" + raw + '\'' +
                ", server=" + server +
                ", clientsStatus=" + clients +
                ", memory=" + memory +
                ", persistence=" + persistence +
                ", stats=" + stats +
                ", replication=" + replication +
                ", CPU=" + CPU +
                ", keyspaceMap=" + keyspaceMap +
                '}';
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
