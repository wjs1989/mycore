package com.wjs.myProject;

import com.wjs.myProject.core.aop.service.DbConnation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyProjectApplicationTests {

	@Autowired
	DbConnation dbConnation;

	@Test
	public void contextLoads(){

		dbConnation.exec("select 1 from wjs_t");
	}
}
