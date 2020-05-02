package com.wjs.myspring.register;

import com.wjs.myspring.entity.Student;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * @ClassName BeanRegister
 * @Description: TODO
 * @Author wjs
 * @Date 2020/4/12
 * @Version V1.0
 **/
@Component
public class BeanRegister implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        MutablePropertyValues propertyValues = genericBeanDefinition.getPropertyValues();
        propertyValues.add("userName","我是wenjs");

        genericBeanDefinition.setBeanClass(Student.class);
        beanDefinitionRegistry.registerBeanDefinition("student",genericBeanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
