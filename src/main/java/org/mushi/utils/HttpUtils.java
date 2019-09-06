package org.mushi.utils;

import com.aolian.platform.common.response.BaseResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * http工具类
 */
public class HttpUtils {

    public static void writerError(BaseResult baseResult, HttpServletResponse response) throws IOException {
        response.setContentType("application/json,charset=utf-8");
        response.setStatus(baseResult.getStatusCode());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(),baseResult);
    }

}
