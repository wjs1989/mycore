package com.wjs.myspring.test;

import com.wjs.myspring.entity.Person;
import com.wjs.myspring.entity.Student;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 */
public class PersonTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("BeanXml.xml");
       // Person person = (Person)classPathXmlApplicationContext.getBean("person");
        // System.out.println(person.getName());

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.wjs.myspring.register");
        Student student = (Student)applicationContext.getBean("student");
        System.out.println(student.getUserName());

        ApplicationContext classPathXmlApplicationContext1
                = new ClassPathXmlApplicationContext("BeanXml.xml");

          Person person = (Person)classPathXmlApplicationContext.getBean("person");

        System.out.println(person.getName());
    }



}
