package org.mushi.exception;

/**
 *  没有认证或是token过期
 **/
public class NotAuthException extends RuntimeException {

    public NotAuthException() {
        this("没有认证！");
    }

    public NotAuthException(String message) {
        super(message);
    }
}
