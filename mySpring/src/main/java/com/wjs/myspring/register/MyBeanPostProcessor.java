package com.wjs.myspring.register;


/**
 * @ClassName MyBeanPostProcessor
 * @Description: TODO
 * @Author wjs
 * @Date 2020/4/12
 * @Version V1.0
 **/
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

   @Override
   public  Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
