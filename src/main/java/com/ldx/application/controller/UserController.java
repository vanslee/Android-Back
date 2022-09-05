package com.ldx.application.controller;

import com.alibaba.fastjson2.JSONObject;
import com.ldx.pojo.User;
import com.ldx.service.UserService;
import com.ldx.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    private ResponseResult<JSONObject> login(@RequestBody User user) {
        log.info("获取到登录用户的信息:{}", user);
        return userService.login(user);
    }

    @PostMapping("registry")
    private ResponseResult<User> registry(@RequestBody User user) {
        log.info("获取到注册的用户信息:{}", user);
        return userService.registry(user);
    }
    @PostMapping("logout")
    public ResponseResult<String> logout() {
        return userService.logout();
    }
}
