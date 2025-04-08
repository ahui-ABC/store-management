package com.ahui.service.impl;

import com.ahui.service.AuthService;
import com.ahui.store.entity.User;
import com.ahui.store.mapper.PermissionMapper;
import com.ahui.store.mapper.RoleMapper;
import com.ahui.store.mapper.UserMapper;
import com.ahui.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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
    @Autowired  // 或使用构造函数注入
    private JwtUtils jwtUtils; // ✅ 注入组件
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    @Transactional
    public String login(String username, String password) {
        // 1. 基础验证
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return "用户名不存在";
        }
        if (user.getStatus() == 0) {
            throw new DisabledException("用户已被禁用");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "密码错误";
        }

        // 2. 查询权限数据（合并角色和权限标识）

        // 2.1 获取角色编码（如 ROLE_ADMIN）
        List<String> roleCodes = roleMapper.selectRoleCodesByUserId(user.getId());
        List<String> authorities = roleCodes.stream()
                .map(code -> "ROLE_" + code).collect(Collectors.toList());

        // 2.2 获取权限标识符（如 user:add）
        List<String> permissionCodes = permissionMapper.selectPermissionCodesByUserId(user.getId());
        authorities.addAll(permissionCodes);

        // 3. 更新最后登录时间
        userMapper.updateLastLoginTime(user.getId(), new Date());

        // 4. 生成Token
        return jwtUtils.generateToken(
                user.getUsername(),
                authorities.stream().distinct().collect(Collectors.toList())
        );
    }
}
