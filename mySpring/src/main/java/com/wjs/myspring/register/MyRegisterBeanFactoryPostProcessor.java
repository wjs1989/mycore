package com.wjs.myspring.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;


/**
 * @author wenjs
 */
@Component
public class MyRegisterBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
   Logger logger = LoggerFactory.getLogger(MyRegisterBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //这里可以注册bean定义信息，在refresh中比 postProcessBeanFactory 先执行
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        logger.info("MyRegisterBeanFactoryPostProcessor");
        for(String beanDefinition :beanDefinitionNames){
            logger.info(beanDefinition);
        }


    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
