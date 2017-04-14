package com.jason798.redis.dto;

/**
 * for single thread
 *
 */
public class RedisConfig {

    protected String ip;//if use pool, format:   ip:port
    protected int port;//only used by SingleJedisFactory
    protected String auth;
    protected int db;
    protected String type;
    protected int timeout = 5000;

    public RedisConfig() {
    }


    @Override
    public String toString() {
        return "RedisConfig{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", auth='" + auth + '\'' +
                ", db=" + db +
                ", type='" + type + '\'' +
                ", timeout=" + timeout +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
