package com.wjs.mybatis.configuration.interceptor;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wjs.mybatis.interceptor.SystemInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @ClassName WebMvcConfiguration
 * @Description: TODO
 * @Author wjs
 * @Date 2020/5/2
 * @Version V1.0
 **/
@Configuration
public class WebMvcConfiguration {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer(){

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new SystemInterceptor());
            }

            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                FastJsonHttpMessageConverter fjc = new FastJsonHttpMessageConverter();
                FastJsonConfig fj = new FastJsonConfig();
                fj.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
                fjc.setFastJsonConfig(fj);
                converters.add(fjc);
            }
        };
    }
}
