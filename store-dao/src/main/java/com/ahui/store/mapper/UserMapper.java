package com.ahui.store.mapper;

import com.ahui.store.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author ahui
 * @since 2025-04-06
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM sys_user WHERE username = #{username}")
    User selectByUsername(String username);
}
