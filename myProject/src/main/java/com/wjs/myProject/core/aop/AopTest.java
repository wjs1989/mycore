package com.wjs.myProject.core.aop;

import com.wjs.myProject.core.aop.service.DbConnation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootTest
//@ComponentScan("com.wjs.myProject")
public class AopTest {

    @Autowired
    private DbConnation dbConnation;

    @Autowired
    ApplicationContext applicationContext;

   // @Test
    public void contextLoads(){

       // ApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.wjs.myProject.core.aop");
       // DbConnation dbConnation = (DbConnation) applicationContext.getBean("dbConnation");
        dbConnation.exec("select 1 from wjs_t");
    }
}
