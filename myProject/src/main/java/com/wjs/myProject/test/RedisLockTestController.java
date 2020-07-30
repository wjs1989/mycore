package com.wjs.myProject.test;

import com.wjs.myProject.core.distributedlock.lock.ReentrantRedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wenjs
 * @Description:
 * @date 2020/7/29 18:11
 */
@RestController
@RequestMapping("red")
public class RedisLockTestController {
    RedisTemplate redisTemplate;
    Lock lock = null;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        lock = new ReentrantRedisLock("redis_key_1", redisTemplate);
    }
  //
  @GetMapping("do1")
    public String setRedis(){
      try {
          Integer count = null;
          try {
              lock.lock();
              count = (Integer) redisTemplate.opsForValue().get("count");
              redisTemplate.opsForValue().set("count", count == null ? 0 : ++count);
              count = (Integer) redisTemplate.opsForValue().get("count");
              System.out.println(count);
          } catch (Exception e) {
              throw e;
          } finally {
              lock.unlock();
          }
          return "count="+count;
      }catch (Exception e){
          return "执行异常"+e.toString();
      }
    }

}
