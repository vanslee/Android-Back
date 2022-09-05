package com.ldx.security.filter;

import com.alibaba.fastjson2.JSONObject;
import com.ldx.constants.AppHttpCodeEnum;
import com.ldx.constants.SystemConstant;
import com.ldx.pojo.User;
import com.ldx.utils.JwtUtils;
import com.ldx.utils.RedisUtils;
import com.ldx.utils.ResponseResult;
import com.ldx.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头中的token
        String token = request.getHeader("Authentication");
        if (!StringUtils.hasText(token) || "".equals(token)) {
            //说明该接口不需要登录  直接放行
            filterChain.doFilter(request, response);
            return;
        }
        //解析获取userid
        Claims claims = null;
        try {
            claims = JwtUtils.parse(token);
        } catch (Exception e) {
            e.printStackTrace();
            //token超时  token非法
            //响应告诉前端需要重新登录
            WebUtils.renderString(response, ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN).toString());
            return;
        }
        String userId = claims.getSubject();
        //从redis中获取用户信息
        JSONObject obj = redisUtils.getCacheObject(SystemConstant.REDIS_LOGIN + userId);
        User user = obj.getJSONObject("user").to(User.class);
        //如果获取不到
        if (Objects.isNull(user)) {
            //说明登录过期  提示重新登录
            WebUtils.renderString(response, ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN).toString());
            return;
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (String role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        //存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }


}
