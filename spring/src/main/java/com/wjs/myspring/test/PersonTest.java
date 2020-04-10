package com.wjs.myspring.test;

import com.wjs.myspring.entity.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext
                = new ClassPathXmlApplicationContext("BeanXml.xml");

        Person person = (Person)classPathXmlApplicationContext.getBean("person");

        ApplicationContext
        System.out.println(person.getName());
    }
}
