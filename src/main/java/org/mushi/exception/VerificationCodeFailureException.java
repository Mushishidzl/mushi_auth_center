package org.mushi.exception;


import org.springframework.security.core.AuthenticationException;

/**
 *
 */
public class VerificationCodeFailureException extends AuthenticationException {

    public VerificationCodeFailureException(String msg, Throwable t) {
        super(msg, t);
    }

    public VerificationCodeFailureException(String msg) {
        super(msg);
    }
}
