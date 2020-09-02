package com.wjs.myProject.core.redis.model;

import com.wjs.myProject.core.redis.config.RedisKeyEnum;
import org.springframework.data.redis.connection.DataType;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 12:00
 */
public class BaseRedis {

    /**
     * 数据类型
     */
    protected DataType dataType;
    protected String key;
    protected String value;
    protected long expire;
    protected RedisKeyEnum redisKey;

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public RedisKeyEnum getRedisKey() {
        return redisKey;
    }

    public void setRedisKey(RedisKeyEnum redisKey) {
        this.redisKey = redisKey;
        this.dataType =redisKey.getDataType();
        this.key = redisKey.getKey();
        this.expire = redisKey.getExpire();
    }
}
