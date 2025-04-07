package com.ahui.service.impl;

import com.ahui.service.UserRoleService;
import com.ahui.store.entity.UserRole;
import com.ahui.store.mapper.UserRoleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author ahui
 * @since 2025-04-06
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
