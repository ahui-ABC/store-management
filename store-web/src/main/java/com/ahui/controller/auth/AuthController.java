package com.ahui.controller.auth;

import com.ahui.dto.LoginDTO;
import com.ahui.result.Result;
import com.ahui.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO dto) {
        String token = authService.login(dto.getUsername(), dto.getPassword());
        return Result.success(token);
    }
}
