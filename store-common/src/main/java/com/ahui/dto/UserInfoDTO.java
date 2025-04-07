package com.ahui.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName 用户基本信息DTO$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@Setter
@Getter
public class UserInfoDTO {
    private Long id;
    private String username;
    private String avatar;       // 头像URL
    private List<String> roles;  // 角色编码列表
}
