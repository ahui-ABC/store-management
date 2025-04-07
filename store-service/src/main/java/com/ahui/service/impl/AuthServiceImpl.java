package com.ahui.service.impl;

import com.ahui.service.AuthService;
import com.ahui.store.entity.User;
import com.ahui.store.mapper.UserMapper;
import com.ahui.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName $
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder; // 现在可以正常注入

    @Override
    public String login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null || !passwordEncoder.matches(password + user.getSalt(), user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        return JwtUtils.generateToken(username); // 返回JWT
    }
}
