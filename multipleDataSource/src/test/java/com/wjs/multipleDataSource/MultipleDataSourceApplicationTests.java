package com.wjs.multipleDataSource;

import com.wjs.multipleDataSource.master.entity.Sect;
import com.wjs.multipleDataSource.master.service.SectService;
import com.wjs.multipleDataSource.slave.entity.User;
import com.wjs.multipleDataSource.slave.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MultipleDataSourceApplicationTests {

	@Autowired
	SectService sectService;

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {

		System.out.println(sectService.selectList(null));
		System.out.println(userService.selectList(null));


		Sect sect = new Sect();
		sect.setAge(1);
		sect.setId(1111);
		sect.setName("123");
		sectService.insert(sect);

		User user = new User();
		user.setUsername("wenjs01");
		user.setPassword("234");
		user.setUid(1231);
		userService.insert(user);
	}

}
