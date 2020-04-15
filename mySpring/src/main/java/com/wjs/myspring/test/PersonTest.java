package com.wjs.myspring.test;

import com.wjs.myspring.entity.Person;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 */
public class PersonTest {
    public static void main(String[] args) {
        ApplicationContext classPathXmlApplicationContext
                = new ClassPathXmlApplicationContext("BeanXml.xml");

          Person person = (Person)classPathXmlApplicationContext.getBean("person");

        System.out.println(person.getName());
    }



}
