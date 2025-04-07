package com.ahui.store.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author ahui
 * @since 2025-04-06
 */
@Getter
@Setter
@TableName("sys_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     */
    @TableField("name")
    private String name;

    /**
     * 权限标识符（如user:add）
     */
    @TableField("code")
    private String code;

    /**
     * 类型（1-菜单，2-按钮，3-API）
     */
    @TableField("type")
    private Byte type;

    /**
     * 权限路径/API路径
     */
    @TableField("url")
    private String url;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 父权限ID
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 排序号
     */
    @TableField("order_num")
    private Integer orderNum;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
