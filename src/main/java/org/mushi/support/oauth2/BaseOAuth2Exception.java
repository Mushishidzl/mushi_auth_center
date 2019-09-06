package org.mushi.support.oauth2;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 *  自定义异常
 */
public class BaseOAuth2Exception extends OAuth2Exception {

    public BaseOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }
    public BaseOAuth2Exception(String msg) {
        super(msg);

    }


}
