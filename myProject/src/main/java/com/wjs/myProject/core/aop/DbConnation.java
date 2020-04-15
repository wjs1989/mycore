package com.wjs.myProject.core.aop;

/**
 * @author Administrator
 */
public class DbConnation {

    public int exec(String sql){
        System.out.println("执行sql："+sql);

        return 1;
    }
}
