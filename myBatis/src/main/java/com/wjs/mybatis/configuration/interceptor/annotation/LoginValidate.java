package com.wjs.mybatis.configuration.interceptor.annotation;

import java.lang.annotation.*;

/**
 * @author wenjs
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginValidate {

    /**
     * 不使用注解，默认需要校验用户登入，
     * 如果不需要校验，请使用@LoginValidate(false) 标注类或方法
     * @return
     */
    boolean value() default true;
}
