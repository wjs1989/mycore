package com.wjs.myProject.test;

import com.wjs.myProject.core.distributedlock.lock.ReentrantRedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.Lock;

/**
 * @author wenjs
 * @Description:
 * @date 2020/7/29 18:11
 */
@RestController
@RequestMapping("red")
public class RedisLockTestController {
    @Autowired
    RedisTemplate redisTemplate;

  //
  @GetMapping("do1")
    public String setRedis(){
//        Lock lock = new ReentrantRedisLock("redis_key_1",redisTemplate);
//        for (int i =0 ;i<100000000;i++) {
//            try {
//                lock.lock();
//
//                redisTemplate.opsForValue().set("count", i);
//                System.out.println(redisTemplate.opsForValue().get("count"));
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        }

        return "OK";
    }

}
