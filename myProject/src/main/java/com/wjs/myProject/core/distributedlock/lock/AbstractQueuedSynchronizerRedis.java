package com.wjs.myProject.core.distributedlock.lock;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;

/**
 * @author wenjs
 * @Description:
 * @date 2020/7/28 19:07
 */
public abstract class AbstractQueuedSynchronizerRedis extends AbstractQueuedSynchronizer {

    protected AbstractQueuedSynchronizerRedis() {
        redisKey = "lock_"+ UUID.randomUUID().toString();
    }

    private RedisTemplate redisTemplate;

    protected void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 保存在redis中的key值，每把锁都补一样
     */
    private String redisKey;

    public void setRedisKey(String redisKey) {
        this.redisKey = "lock_" + redisKey;
    }

    /**
     * 获取当前redis中redisKey对应的值
     *
     * @return
     */
    protected int getRedisState() {
        return (int) redisTemplate.opsForValue().get(redisKey);
    }

    /**
     * 设置当前redis中redisKey对应的值
     *
     * @return
     */
    protected  void setRedisState(int newState) {
        redisTemplate.opsForValue().set(redisKey,newState);
    }
    protected  void deleteRedisState() {
        redisTemplate.delete(redisKey);
    }

    /**
     * 更改值，比较并设置
     * @param expect
     * @param update
     * @return
     */
    protected final boolean compareAndSetRedisState(int expect, int update) {
        String compareKey = "compare_"+redisKey;
        if(redisTemplate.opsForValue().setIfAbsent(compareKey,compareKey,10, TimeUnit.SECONDS)){

            //设置成功，则操作
            int rValue = getRedisState();
            if(rValue == expect){
                this.setRedisState(update);
                redisTemplate.delete(compareKey);
            }
            return true;
        }

        return false;
    }

}
