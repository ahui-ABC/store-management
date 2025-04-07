package com.ahui.interceptor;

import com.ahui.service.PermissionService;
import com.ahui.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName 权限拦截器$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private PermissionService permissionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");
        if (!JwtUtils.validateToken(token)) {
            throw new RuntimeException("无效Token");
        }

        String username = JwtUtils.getUsernameFromToken(token);
        List<String> permissions = permissionService.getPermissionsByUsername(username);

        String requestURI = request.getRequestURI();
        if (!permissions.contains(requestURI)) {
            throw new RuntimeException("无权限访问: " + requestURI);
        }
        return true;
    }
}
