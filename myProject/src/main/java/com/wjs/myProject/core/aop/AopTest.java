package com.wjs.myProject.core.aop;

import com.wjs.myProject.core.aop.service.DbConnation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class AopTest {
    @Autowired
    DbConnation dbConnation;
    @Test
    public void contextLoads(){

        dbConnation.exec("select 1 from wjs_t");
    }
}
