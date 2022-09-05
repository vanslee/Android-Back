package com.ldx.service;

import com.alibaba.fastjson2.JSONObject;
import com.ldx.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ldx.utils.ResponseResult;

/**
 * @author ldx
 * @description 针对表【user】的数据库操作Service
 * @createDate 2022-09-01 17:20:51
 */
public interface UserService extends IService<User> {

    ResponseResult<User> registry(User user);

    ResponseResult<JSONObject> login(User user);

    ResponseResult<String> logout();
}
