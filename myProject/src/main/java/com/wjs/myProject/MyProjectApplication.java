package com.wjs.myProject;

import com.wjs.myProject.configuration.LoadBalanceRestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class MyProjectApplication {

	@Autowired
	LoadBalanceRestConfiguration loadBalanceRestConfiguration;


	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(MyProjectApplication.class, args);

		System.out.println(((LoadBalanceRestConfiguration)run.getBean("loadBalanceRestConfiguration")).getName());
	}

}
