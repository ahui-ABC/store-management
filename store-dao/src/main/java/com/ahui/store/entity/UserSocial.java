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
 * 第三方用户关联表
 * </p>
 *
 * @author ahui
 * @since 2025-04-06
 */
@Getter
@Setter
@TableName("sys_user_social")
public class UserSocial implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 系统用户ID（未绑定则为空）
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 平台类型（wechat/apple/qq）
     */
    @TableField("platform")
    private String platform;

    /**
     * 第三方平台唯一ID
     */
    @TableField("open_id")
    private String openId;

    /**
     * 微信unionId
     */
    @TableField("union_id")
    private String unionId;

    /**
     * 访问令牌
     */
    @TableField("access_token")
    private String accessToken;

    /**
     * 令牌过期时间
     */
    @TableField("expire_time")
    private LocalDateTime expireTime;

    /**
     * 第三方昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 第三方头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 原始用户数据
     */
    @TableField("raw_data")
    private String rawData;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
