package com.ahui.service.impl;

import com.ahui.dto.RegisterDTO;
import com.ahui.service.UserService;
import com.ahui.store.entity.User;
import com.ahui.store.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author ahui
 * @since 2025-04-06
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

        @Autowired
        private UserMapper userMapper;
        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void register(RegisterDTO dto) {
            // 1. 校验用户名唯一性
            if (userMapper.existsByUsername(dto.getUsername())) {
                throw new RuntimeException("用户名已存在");
            }
            // 2. 密码加密
            User user = new User();
            user.setUsername(dto.getUsername());
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            user.setEmail(dto.getEmail());
            user.setMobile(dto.getMobile());
            user.setStatus(1); // 默认启用状态

            // 3. 保存用户
            userMapper.insert(user);

            // 4. 初始化用户权限（可选）
            initUserPermissions(user.getId());
        }

        private void initUserPermissions(Long userId) {
            // 关联默认角色或权限...
        }
}
