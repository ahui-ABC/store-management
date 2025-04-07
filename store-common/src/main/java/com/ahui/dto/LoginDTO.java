package com.ahui.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @ClassName 登录请求DTO$
 * @Author pengzhaohui
 * @Description
 * @Date $ $
 **/

@Getter
@Setter
@ApiModel("登录参数")
public class LoginDTO {
    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名", required = true, example = "admin")
    private String username;

    @Size(min = 6, message = "密码长度至少6位")
    private String password;

}
