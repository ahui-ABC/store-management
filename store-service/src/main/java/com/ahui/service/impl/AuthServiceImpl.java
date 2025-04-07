package com.ahui.service.impl;

import com.ahui.service.AuthService;
import com.ahui.store.entity.User;
import com.ahui.store.mapper.UserMapper;
import com.ahui.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import static com.ahui.utils.JwtUtils.generateToken;

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
    private PasswordEncoder passwordEncoder; // 使用BCrypt

    @Override
    public String login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 正确：仅传递username生成Token
        return JwtUtils.generateToken(user.getUsername());
    }
}
