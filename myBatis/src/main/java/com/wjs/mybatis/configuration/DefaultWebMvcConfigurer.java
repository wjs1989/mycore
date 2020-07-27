package com.wjs.mybatis.configuration;

import com.wjs.mybatis.configuration.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wenjs
 */
@Configuration
public class DefaultWebMvcConfigurer {

    @Bean //将组件注册在容器中
    public WebMvcConfigurer webMvcConfigurerAdapter(){
        return new WebMvcConfigurer(){
            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor());
            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 设置允许跨域的路径
                        .allowedOrigins("*") // 设置允许跨域请求的域名
                        .allowCredentials(true)// 是否允许证书
                        .allowedMethods("GET", "POST", "DELETE", "PUT","PATCH") // 设置允许的方法
                        .allowedHeaders("*")// 设置允许的header属性
                        .maxAge(3600);// 跨域允许时间
            }
        };
    }
}

