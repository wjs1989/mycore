package com.wjs.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wjs.entity.UserTemplate;
import com.wjs.mybatis.Service.IPersonService;
import com.wjs.mybatis.annotation.LoadBalanceRest;
import com.wjs.mybatis.configuration.datasource.DynamicDataSource;
import com.wjs.mybatis.dao.PersonMapper;
import com.wjs.mybatis.dao.UserMapper;
import com.wjs.mybatis.model.Animal;
import com.wjs.mybatis.model.MyDataSource;
import com.wjs.mybatis.pojo.Person;
import com.wjs.mybatis.pojo.User;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.hibernate.validator.constraints.Length;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import static com.zaxxer.hikari.util.ClockSource.currentTime;
import static com.zaxxer.hikari.util.ClockSource.elapsedDisplayString;
import static com.zaxxer.hikari.util.ClockSource.elapsedMillis;
import static com.zaxxer.hikari.util.ClockSource.plusMillis;
/**
 * @ClassName MyBatisApplicationTest
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/20
 * @Version V1.0
 **/
@SpringBootTest
public class MyBatisApplicationTest {

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @Test
    @PerfTest(invocations = 1000,threads = 50)
    public void test(){
        System.out.println(Thread.currentThread().getName());
    }

//    @Autowired
//    private UserTemplate userTemplate;

    @Autowired
    private IPersonService personService;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @LoadBalanceRest
    @Autowired(required = false)
    private Person person;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyDataSource myDataSource;

    @Transactional
    @Test
    void UserMapperTest() throws SQLException {
//         try {
             Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("select * from user ");
//             ResultSet resultSet = preparedStatement.executeQuery();
//
//
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//
//         User user = userMapper.selectByPrimaryKey(5);

        Person person = personService.selectByPrimaryKey("5");
        System.out.println(person);
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

       if( dataSource instanceof DynamicDataSource){
           DynamicDataSource d = (DynamicDataSource) dataSource;
           Map<Object, Object> targetDataSourcesSub = d.getTargetDataSources();
           targetDataSourcesSub.put("mysql4",myDataSource.getDataSource());
           d.setTargetDataSources(targetDataSourcesSub);
       }

       try {

            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user ");
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
         }

    }

}
