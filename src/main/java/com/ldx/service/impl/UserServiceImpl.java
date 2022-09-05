package com.ldx.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldx.constants.SystemConstant;
import com.ldx.pojo.User;
import com.ldx.security.UserDetails;
import com.ldx.service.UserService;
import com.ldx.mapper.UserMapper;
import com.ldx.utils.JwtUtils;
import com.ldx.utils.RedisUtils;
import com.ldx.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author ldx
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2022-09-01 17:20:51
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult<User> registry(User user) {
        String phone = user.getPhone();
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getPhone, phone);
        User hasUser = userMapper.selectOne(lqw);
        if (Objects.nonNull(hasUser)) {
            return ResponseResult.errorResult(503, "用户已存在");
        }
        if (userMapper.insert(user) > 0) {
            log.debug("userId:{}", String.valueOf(user.getId()));
            userMapper.setRole(user.getId(), SystemConstant.ROLE_USER);
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(503, "插入数据失败");
    }

    @Override
    public ResponseResult<JSONObject> login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getPhone(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("手机号或密码错误");
        }
        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
        Integer id = userDetails.getUser().getId();
        String token = JwtUtils.generator(String.valueOf(id));
        redisUtils.setCacheObject(SystemConstant.REDIS_LOGIN + id, userDetails);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("user", user);
        return ResponseResult.okResult(jsonObject);
    }

    @Override
    public ResponseResult<String> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        Integer id = userDetails.getId();
        redisUtils.deleteObject(SystemConstant.REDIS_LOGIN + id);
        return ResponseResult.okResult();
    }
}




