package com.wjs.myProject.annotation;

import java.lang.annotation.*;

/**
 * @author wenjs
 * @Description: 方法级别的注解，标识需要解析元数据
 * @date 2020/8/21 10:03
 */

@Documented
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionMetadataHandler {

}
