package com.ahui.service.impl;

import com.ahui.service.PermissionService;
import com.ahui.store.entity.Permission;
import com.ahui.store.mapper.PermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author ahui
 * @since 2025-04-06
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> getPermissionsByUsername(String username) {
        // 直接查询数据库（可根据性能需求添加本地缓存）
        return permissionMapper.selectPermissionsByUsername(username);
    }
}
