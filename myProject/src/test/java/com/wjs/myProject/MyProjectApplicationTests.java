package com.wjs.myProject;

import com.wjs.myProject.core.distributedlock.lock.ReentrantRedisLock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class MyProjectApplicationTests {

	@Autowired
	RedisTemplate redisTemplate;

	Lock lock = null;

	@Test
	void contextLoads() {
		lock = new ReentrantRedisLock("redis_key_1",redisTemplate);
		test1(1);

	}
	void test1(int i){
		try {
			lock.lock();

			redisTemplate.opsForValue().set("count", i);
			System.out.println(redisTemplate.opsForValue().get("count"));
			if(i<10) {
				test1(++i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}
