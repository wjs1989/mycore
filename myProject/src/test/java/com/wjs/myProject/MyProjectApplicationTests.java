package com.wjs.myProject;

import com.wjs.myProject.configuration.LoadBalanceRestConfiguration;
import com.wjs.myProject.core.aop.service.DbConnation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyProjectApplicationTests {

//	@Autowired(required = false)
	//DbConnation dbConnation;

	//@Value("${name}")
//	private String name;

	@Autowired
	LoadBalanceRestConfiguration loadBalanceRestConfiguration;

	@Test
	public void contextLoads(){

	//	dbConnation.exec("select 1 from wjs_t");
		System.out.println(loadBalanceRestConfiguration.getName());
	}
}
