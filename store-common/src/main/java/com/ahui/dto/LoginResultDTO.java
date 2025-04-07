package com.ahui.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName 登录响应DTO$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@Getter
@Setter
public class LoginResultDTO {
    private Long userId;
    private String username;
    private String token;
    private List<String> roles; // 用户角色编码列表（如 ["admin", "user"]）
}
