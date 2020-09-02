package com.wjs.myProject.core.redis.config;

import org.springframework.data.redis.connection.DataType;

import java.util.Random;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 14:37
 */
public enum RedisKeyEnum {
    DISTRICT_TREE("district_tree",600,800,DataType.STRING),
    DISTRICT_CODE("district_code::",1000,1200,DataType.STRING),
    DISTRICT_ID("district_id::",1000,1200,DataType.STRING);

    private String key;
    private DataType dataType;

    /**
     * 单位，s
     */
    private int minExpire;
    private int maxExpire;

    RedisKeyEnum(String key,int minExpire,int maxExpire,DataType dataType){
        this.key = key;
        this.minExpire = minExpire;
        this.maxExpire = maxExpire;
        this.dataType = dataType;
    }

    public String getKey() {
        return key;
    }
    public DataType getDataType(){
        return dataType;
    }

    public int getExpire() {
        Random r = new Random();
        return r.nextInt(maxExpire - minExpire + 1) + minExpire;
    }
}
