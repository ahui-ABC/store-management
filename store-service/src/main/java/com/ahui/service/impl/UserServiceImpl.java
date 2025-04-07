package com.ahui.service.impl;

import com.ahui.service.UserService;
import com.ahui.store.entity.User;
import com.ahui.store.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author ahui
 * @since 2025-04-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
