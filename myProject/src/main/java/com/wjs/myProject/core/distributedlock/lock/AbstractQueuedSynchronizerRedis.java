package com.wjs.myProject.core.distributedlock.lock;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
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
        String key = getUniqueId();
        redisKey = "lock_"+ key;
        threadKey = "lock_thread_"+ key;
    }

    private RedisTemplate redisTemplate;

    /**
     * 保存在redis中的key值，每把锁都补一样
     */
    private String redisKey;


    /**
     * 线程在redis上的id，需要全局唯一
     * 这里使用uuid
     */
    private String threadKey;

    protected void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String getUniqueId(){
       return UUID.randomUUID().toString().replace("-","");
    }

    public void setRedisKey(String redisKey) {
        if (redisKey == null || redisKey == "") {
            return;
        }

        this.redisKey = "lock_" + redisKey;
    }

    public String getThreadId(){
      return (String) redisTemplate.opsForValue().get(threadKey);
    }

    /**
     * 获取当前redis中redisKey对应的值
     *
     * @return
     */
    protected int getRedisState() {
        Integer state = (Integer) redisTemplate.opsForValue().get(redisKey);
        return state == null ? 0 : state.intValue();
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
        redisTemplate.delete(Arrays.asList(threadKey,redisKey));
    }

    protected void setRedisThreadId(String id){
        redisTemplate.opsForValue().set(threadKey,id);
    }
    /**
     * 更改值，比较并设置
     * @param expect
     * @param update
     * @return
     */
    protected final boolean compareAndSetRedisState(int expect, int update,String threadId) {
        String compareKey = "compare_"+redisKey;
        if(redisTemplate.opsForValue().setIfAbsent(compareKey,compareKey,10, TimeUnit.SECONDS)){
            try {
                //设置成功，则操作
                int rValue = getRedisState();
                if (rValue == expect) {
                    this.setRedisState(update);
                    this.setRedisThreadId(threadId);
                    return true;
                }
            } finally {
                redisTemplate.delete(compareKey);
            }
        }
        return false;
    }

}
