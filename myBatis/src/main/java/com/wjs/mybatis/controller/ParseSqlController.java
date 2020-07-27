package com.wjs.mybatis.controller;

import com.alibaba.fastjson.JSONObject;
import com.wjs.mybatis.model.Animal;
import com.wjs.mybatis.sqlparse.ISqlParse;
import com.wjs.mybatis.sqlparse.MySqlParse;
import com.wjs.mybatis.sqlparse.SqlParseFactory;
import com.wjs.mybatis.sqlparse.model.GenericSqlModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ParseSqlController
 * @Description: TODO
 * @Author wjs
 * @Date 2020/4/17
 * @Version V1.0
 **/
@RestController
public class ParseSqlController {


    @PostMapping("/parse")
    public Animal SqlParse(@RequestBody GenericSqlModel genericSqlModel) throws Exception {

        ISqlParse parser = SqlParseFactory.createParser(MySqlParse.class);
        Animal animal = new Animal();
        animal.setName(parser.parse(genericSqlModel));
        return animal;
    }


}
