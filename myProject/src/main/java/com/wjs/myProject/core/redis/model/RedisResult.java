package com.wjs.myProject.core.redis.model;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 15:32
 */
public class RedisResult {
    private StringRedis string;

    public StringRedis getString() {
        return string;
    }

    public void setString(StringRedis string) {
        this.string = string;
    }
}
