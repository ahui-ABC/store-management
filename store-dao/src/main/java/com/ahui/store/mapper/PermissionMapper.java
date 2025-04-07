package com.ahui.store.mapper;

import com.ahui.store.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
}
