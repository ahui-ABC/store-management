package com.ahui.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName 权限校验DTO$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/
@Setter
@Getter
public class PermissionCheckDTO {
    private String requestUrl;   // 请求路径（如 "/api/user/list"）
    private String method;       // HTTP方法（如 "GET"）
    private String permission;   // 所需权限标识（如 "user:list"）
}
