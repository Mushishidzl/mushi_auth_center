package org.mushi.exception;

/**
 *  参数不正确
 **/
public class ArgumentsFailureException extends RuntimeException {

    public ArgumentsFailureException() {
        this("参数错误");
    }

    public ArgumentsFailureException(String message) {
        super(message);
    }
}
