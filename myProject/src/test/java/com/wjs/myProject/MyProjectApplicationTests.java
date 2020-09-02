package com.wjs.myProject;

import com.wjs.myProject.test.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;

@SpringBootTest
class MyProjectApplicationTests {

	@Autowired
	RedisTemplate redisTemplate;

	Lock lock = null;

	@Test
	void contextLoads() throws IllegalAccessException {

		User user = new User("wenjs",18);

		//redisTemplate.opsForHash().put("persion","persionKey",user);


		redisTemplate.opsForHash().putAll("persion",objectToMap(user));
	}
	public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
		Map<String, Object> map = new HashMap<String,Object>();
		Class<?> clazz = obj.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			field.setAccessible(true);
			String fieldName = field.getName();
			Object value =  String.valueOf(field.get(obj));
			map.put(fieldName, value);
		}
		return map;
	}
}
