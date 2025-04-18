package com.ahui.controller.auth;

import com.ahui.config.LocalRateLimiter;
import com.ahui.dto.LoginDTO;
import com.ahui.dto.RegisterDTO;
import com.ahui.result.Result;
import com.ahui.service.AuthService;
import com.ahui.service.UserService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @ClassName 登录控制器$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private LocalRateLimiter rateLimiter;

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO dto) {
        String token = authService.login(dto.getUsername(), dto.getPassword());
        return Result.success(token);
    }

    @PostMapping("/register")
    public Result<String> register(@Valid @RequestBody RegisterDTO dto) {
        if (!rateLimiter.tryAcquire()) {
            throw new RuntimeException("请求过于频繁，请稍后再试");
        }
        userService.register(dto);
        return Result.success("注册成功");
    }

    @GetMapping("/testToken")
    public Result<String> testToken() {
        return Result.success("访问成功!!");
    }
}
