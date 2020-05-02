package com.wjs.mybatis.configuration;

import com.wjs.mybatis.pojo.Person;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.wjs.mybatis.annotation.LoadBalanceRest;
/**
 * @ClassName ConditionalConfiguration
 * @Description: Conditional 条件注解测试
 * @Author wjs
 * @Date 2020/3/26
 * @Version V1.0
 **/
@Configuration
public class ConditionalConfiguration {

    @LoadBalanceRest
    @Bean
    @ConditionalOnClass(Person.class)
    public Person person1() {
        Person person = new Person();
        person.setName("wjs");
        return person;
    }

}
