package com.wjs.myProject.core.distributedlock.lock;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author wenjs
 * @Description:
 * @date 2020/7/28 19:07
 */
public abstract class AbstractQueuedSynchronizerRedis extends AbstractQueuedSynchronizer {

    protected AbstractQueuedSynchronizerRedis() {
        run();
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

    /**
     * 锁失效时间，默认10s
     */
    private long expire = 10000;

    private DelayQueue<ThreadIdItem> delayQueue = new DelayQueue<>();

    private Set<String> delayQueueSet = new HashSet<String>();

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
        this.threadKey = "lock_thread_"+ redisKey;
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
        redisTemplate.opsForValue().set(redisKey,newState,expire, TimeUnit.MILLISECONDS);
    }
    protected  void deleteRedisState() {
        try{
            redisTemplate.delete(Arrays.asList(threadKey,redisKey));
        }catch (Exception e){
            e.printStackTrace();
        }
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
        if(redisTemplate.opsForValue().setIfAbsent(compareKey,compareKey,expire, TimeUnit.MILLISECONDS)){
            try {
                //设置成功，则操作
                int rValue = getRedisState();
                if (rValue == expect) {
                    this.setRedisState(update);
                    this.setRedisThreadId(threadId);
                    if(update == 0){
                        deleteRedisState();
                        delayQueueSet.remove(threadId);
                    }else {
                        if(!delayQueueSet.contains(threadId)){
                            delayQueueSet.add(threadId);
                            delayQueue.add(new ThreadIdItem(expire- 1000,threadId));
                        }
                    }
                    return true;
                }
            } finally {
                redisTemplate.delete(compareKey);
            }
        }
        return false;
    }


    /**
     * 线程的延迟队列
     */
    static final class ThreadIdItem implements Delayed {
        private final long delayTime; // 延迟时间
        private final long expire; // 到期时间
        private String key; // 数据

        ThreadIdItem(long delayTime,  String key) {
            this.delayTime = delayTime;
            this.expire = delayTime+System.currentTimeMillis();
            this.key = key;
        }


        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        public Object getKey() {
            return key;
        }
    }

    /**
     * 使用延迟队列，如果锁快到期，线程没有执行完成，则重置锁的过期时间
     */
    private void run(){
      new Thread(new Runnable() {
          @Override
          public void run() {
              for (; ; ) {
                  try {
                      ThreadIdItem threadIdItem = delayQueue.take();
                      if(delayQueueSet.contains(threadIdItem.key))  {
                          redisTemplate.expire(threadKey,expire, TimeUnit.MILLISECONDS);
                          delayQueue.add(new ThreadIdItem(expire- 1000,threadIdItem.key));
                      }
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
      }).start();
    }

//    public static void main(String[] args) {
//        DelayQueue<ThreadIdItem> delayQueue = new DelayQueue<>();
//        delayQueue.add(new ThreadIdItem(3000,  "123"));
//        delayQueue.add(new ThreadIdItem(6000,  "125"));
//
//        for (; ; ) {
//            try {
//                ThreadIdItem i = delayQueue.take();
//                System.out.println(i.getKey());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
