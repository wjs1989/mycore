package com.wjs.myProject.core.annotation;


/**
 *TODO
 * 注解定义
 * LoadBalanceRest这个注解配合 @Autowired(required = false) 使用，可以加载只有含有该注解的对象
 * 注意 只有注解中包含@Retention(RetentionPolicy.RUNTIME) 和 @Qualifier 才有这个功能
 * @see com.wjs.myProject.core.annotation.LoadBalanceRest
 */