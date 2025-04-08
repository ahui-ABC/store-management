package com.ahui.exception;

/**
 * @ClassName 异常处理$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
public class JwtAuthenticationException extends RuntimeException {
    public JwtAuthenticationException(String message) {
        super(message);
    }
}
