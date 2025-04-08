package com.ahui.store.mapper;

import com.ahui.store.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author ahui
 * @since 2025-04-06
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<String> selectPermissionsByUsername(String username);

    @Select("SELECT p.code FROM sys_permission p " +
            "JOIN sys_role_permission rp ON p.id = rp.permission_id " +
            "JOIN sys_user_role ur ON rp.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId} AND p.type = 3") // type=3表示API权限
    List<String> selectPermissionCodesByUserId(Long id);
}
