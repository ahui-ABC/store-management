package com.ahui.store.mapper;

import com.ahui.store.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

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

    @Select("SELECT COUNT(*) > 0 FROM sys_user WHERE username = #{username}")
    boolean existsByUsername(String username);

    @Update("UPDATE sys_user SET last_login_time = #{date} WHERE id = #{id}")
    void updateLastLoginTime(Long id, Date date);
}
