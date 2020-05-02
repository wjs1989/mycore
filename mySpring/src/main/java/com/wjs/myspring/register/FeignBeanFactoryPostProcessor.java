package com.wjs.myspring.register;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.wjs.myspring.register.BeanUtils.getFactory;

/**
 * @program: isky-bigdata-service
 * @description:
 * @author: wenjs
 * @create: 2020-04-13 18:32
 **/
@Component
//@DependsOn("student")
public class FeignBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (containsBeanDefinition(beanFactory, "feignContext", "eurekaAutoServiceRegistration")) {
            BeanDefinition bd = beanFactory.getBeanDefinition("feignContext");
            bd.setDependsOn("eurekaAutoServiceRegistration");
        }
    }

    private boolean containsBeanDefinition(ConfigurableListableBeanFactory beanFactory, String... beans) {

        getFactory("a");
        return false;//Arrays.stream(beans).allMatch(b -> beanFactory.containsBeanDefinition(b));
    }

}
