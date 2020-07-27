package com.wjs.mybatis.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SystemInterceptor
 * @Description: TODO
 * @Author wjs
 * @Date 2020/5/2
 * @Version V1.0
 **/
public class SystemInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("SystemInterceptor->"+handler.getClass().getSimpleName());
        return true;
    }
}
