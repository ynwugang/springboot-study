package com.wugang.controller;

import com.wugang.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    //只要我们的接口的返回值中存在实体类，这个实体类就会被扫描到swagger中
    @PostMapping("/user")
    public User user() {
        return new User();
    }

    @ApiOperation("get请求")
    @GetMapping("/hello/{uname}")
    public String hello1(@ApiParam("用户名") @PathVariable("uname") String username) {
        return "hello " + username;
    }

    @ApiOperation("post请求")
    @PostMapping("/hello/{uname}")
    public String hello2(@ApiParam("用户名") @PathVariable("uname") String username) {
        return "hello " + username;
    }
}
