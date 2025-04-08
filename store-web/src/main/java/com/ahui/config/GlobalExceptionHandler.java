package com.ahui.config;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName 全局异常处理$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied() {
        return ResponseEntity.status(403).body("无权访问");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthError() {
        return ResponseEntity.status(401).body("认证失败");
    }
}
