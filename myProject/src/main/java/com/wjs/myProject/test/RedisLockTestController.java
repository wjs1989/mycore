package com.wjs.myProject.test;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.wjs.myProject.core.distributedlock.lock.ReentrantRedisLock;
import com.wjs.myProject.core.redis.config.RedisKeyEnum;
import com.wjs.myProject.core.redis.model.BaseRedis;
import com.wjs.myProject.core.redis.model.RedisResult;
import com.wjs.myProject.core.redis.model.StringRedis;
import com.wjs.myProject.core.redis.service.RedisService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;

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
    RedisService redisService;

    @Autowired
    Redisson redisson;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        lock = new ReentrantRedisLock("redis_key", redisTemplate);
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

              //测试锁过期
              // Thread.sleep(11000);

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
    @Cacheable(value="db0",key="T(String).valueOf(#a)")
    @GetMapping("do2")
    public String getValue(int a){
        System.out.println("hello - "+ a);
        return "hello - "+ a;
    }


    @GetMapping("do3")
    public User getValue1(int a) throws IllegalAccessException {
        Map persion = redisTemplate.opsForHash().entries("persion");

        User user = new User("wenjs",18);
        user = mapToBean(persion,user);

       // redisTemplate.opsForHash().putAll("persion",objectToMap(user));
        redisTemplate.opsForList().leftPush("ll", "1");
        redisTemplate.opsForSet().add("member","1","2","3","4");


        StringRedis stringRedis = new StringRedis();
        stringRedis.setRedisKey(RedisKeyEnum.DISTRICT_ID);
        stringRedis.setValue("测试数据");
        redisService.save(stringRedis);

        List<Object> list = redisTemplate.opsForValue().multiGet(Arrays.asList("1", "2"));

        RedisResult redisResult = redisService.get(RedisKeyEnum.DISTRICT_ID);
        System.out.println(redisResult.getString().getValue());

        return user;
    }

    @GetMapping("do4")
    public String getDo4(String n){
        RLock lock = redisson.getLock("lock");
        try{
            lock.lock();

            System.out.println(n);
        }catch (Exception e){

        }finally {
            lock.unlock();
        }


        return "122";
    }



    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);

        beanMap.putAll(map);
        return bean;
    }
}
