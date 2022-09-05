package com.ldx.security.handler;

import com.ldx.constants.AppHttpCodeEnum;
import com.ldx.utils.ResponseResult;
import com.ldx.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        String result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH).build();
        WebUtils.renderString(response, result);
    }
}
