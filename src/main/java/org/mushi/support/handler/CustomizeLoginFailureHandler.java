package org.mushi.support.handler;

import com.aolian.platform.common.response.ResultBuilder;
import org.mushi.utils.HttpUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  登录失败处理器
 */
@Component
public class CustomizeLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        HttpUtils.writerError(ResultBuilder.failTSingle(401,exception.getMessage()),response);

    }
}
