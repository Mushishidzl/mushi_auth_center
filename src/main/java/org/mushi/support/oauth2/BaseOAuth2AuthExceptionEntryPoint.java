package org.mushi.support.oauth2;

import com.aolian.platform.common.response.ResultBuilder;
import org.mushi.utils.HttpUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class BaseOAuth2AuthExceptionEntryPoint extends OAuth2AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        HttpUtils.writerError(ResultBuilder.failTSingle(HttpStatus.UNAUTHORIZED.value(),e.getMessage()),response);
    }

}
