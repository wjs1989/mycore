package com.wjs.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wjs.entity.UserTemplate;
import com.wjs.mybatis.Service.IPersonService;
import com.wjs.mybatis.dao.PersonMapper;
import com.wjs.mybatis.dao.UserMapper;
import com.wjs.mybatis.model.Animal;
import com.wjs.mybatis.pojo.Person;
import com.wjs.mybatis.pojo.User;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * @ClassName MyBatisApplicationTest
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/20
 * @Version V1.0
 **/
@SpringBootTest
public class MyBatisApplicationTest {

    @Autowired
    private UserTemplate userTemplate;

    @Autowired
    private IPersonService personService;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private Person person;

    @Autowired
    private UserMapper userMapper;

     @Test
    void UserMapperTest() {
        User user = userMapper.selectByPrimaryKey(5);
        System.out.println(user);
    }

   // @Test
    void contextLoads() {
        Animal animal = new Animal();

        MetaObject metaObject = SystemMetaObject.forObject(animal);
        metaObject.setValue("name","bean");
        System.out.print(animal.getName());


    }

    //@Test
    void contextSql() {
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setName("李大大");
        //personService.insert(person);


       // Person person1 = personService.selectByPrimaryKey("3f6d45b2-db58-4132-b868-ba4b01ac5af1");


        // PersonMapper personMapper = sqlSessionTemplate.getMapper(PersonMapper.class);
        // PersonMapper personMapper1 = sqlSessionTemplate.getMapper(PersonMapper.class);
          PersonMapper personMapper2 = sqlSessionTemplate.getMapper(PersonMapper.class);


        Page<Person> objects = PageHelper.offsetPage(1, 2);
        Person person1 = personMapper2.selectByPrimaryKey("3f6d45b2-db58-4132-b868-ba4b01ac5af1");

        System.out.print(person1.getName());

    }

    @Test
    public void ApplicationConfigurationTest(){
        userTemplate.doPrint(person.getName());

    }

}
