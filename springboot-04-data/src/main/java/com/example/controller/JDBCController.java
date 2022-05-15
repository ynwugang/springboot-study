package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询数据库的所有信息
    @GetMapping("/userList")
    @ResponseBody
    public List<Map<String, Object>> userList() {
        String sql = "select * from user";
        List<Map<String, Object>> userList = jdbcTemplate.queryForList(sql);
        return userList;
    }

    @GetMapping("/addUser")
    public String addUser() {
        String sql = "insert into user (id,name,pwd) values (99,'测试99','123456')";
        jdbcTemplate.update(sql);

        return "addUser-OK";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id) {
        String sql = "update user set name=?,pwd=? where id=" + id;

        //封装数据
        Object[] objects = new Object[2];
        objects[0] = "update后的测试99";
        objects[1] = "999999";

        jdbcTemplate.update(sql, objects);

        return "updateUser-ok";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        String sql = "delete from user where id = ?";
        jdbcTemplate.update(sql,id);

        return "deleteUser-ok";
    }
}
