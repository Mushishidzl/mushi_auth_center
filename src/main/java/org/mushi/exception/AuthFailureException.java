package org.mushi.exception;

/**
 *  认证失败
 **/
public class AuthFailureException extends RuntimeException {

    public AuthFailureException() {
        this("认证失败！");
    }

    public AuthFailureException(String message) {
        super(message);
    }
}
