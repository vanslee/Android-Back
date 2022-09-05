package com.ldx.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.ldx.mapper.UserMapper;
import com.ldx.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPhone, username);
        User user = userMapper.selectOne(queryWrapper);
        List<Integer> rolesId = userMapper.getRolesId(user.getId());
        Set<String> rolesByIds = userMapper.getRolesByIds(rolesId);
        user.setRoles(rolesByIds);
        //判断是否查到用户  如果没查到抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        return new com.ldx.security.UserDetails(user);
    }
}
