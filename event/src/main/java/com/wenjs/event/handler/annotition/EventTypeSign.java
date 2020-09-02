package com.wenjs.event.handler.annotition;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 16:06
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventTypeSign {

    String name() default "";

    @AliasFor("name")
    String value() default "";
}

