package com.wjs.myProject.core.redis.model;

import org.springframework.data.redis.connection.DataType;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 13:40
 */
public class StringRedis extends BaseRedis {

    public StringRedis(){
        super();
        this.dataType = DataType.STRING;
    }

    public StringRedis(String key, String value, long expire){
        this();
        this.key = key;
        this.value = value;
        this.expire = expire;
    }
}
