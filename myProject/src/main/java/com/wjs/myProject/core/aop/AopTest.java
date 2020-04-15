package com.wjs.myProject.core.aop;

import org.aspectj.lang.annotation.Aspect;
import org.junit.Test;

@Aspect
public class AopTest {

    @Test
    public void contextLoads(){
          DbConnation dbConnation = new DbConnation();

        dbConnation.exec("select 1 from wjs_t");
    }
}
