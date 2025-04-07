package com.ahui.service;

import com.ahui.store.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author ahui
 * @since 2025-04-06
 */
public interface PermissionService extends IService<Permission> {

    List<String> getPermissionsByUsername(String username);
}
