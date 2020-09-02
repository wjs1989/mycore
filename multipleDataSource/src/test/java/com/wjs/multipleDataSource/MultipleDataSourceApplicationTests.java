package com.wjs.multipleDataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MultipleDataSourceApplicationTests {
//
//	@Autowired
//	SectService sectService;
//
//	@Autowired
//	UserService userService;
//
//	@Test
//	void contextLoads() {
//
//		System.out.println(sectService.selectList(null));
//		System.out.println(userService.getUsers());
//
//
//		Sect sect = new Sect();
//		sect.setAge(1);
//		sect.setId(1111);
//		sect.setName("123");
//		sectService.insert(sect);
//
//		User user = new User();
//		user.setUsername("wenjs01");
//		user.setPassword("234");
//		user.setUid(1231);
//		userService.insert(user);
//
//
//	}

	@Test
	void contextLoads() {
		String name = "wenjs";

		Sect sect = new Sect();
		sect.setName("wen"+"js");

		System.out.println(name == sect.getName());
	}

	class Sect {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
