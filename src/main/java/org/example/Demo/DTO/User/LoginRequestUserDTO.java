package org.example.Demo.DTO.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@ApiModel(description = "登录请求参数")
public class LoginRequestUserDTO {

    @ApiModelProperty(value = "邮箱", example = "test@example.com")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "邮箱格式错误")
    private String mail; // 可选，与mobile二选一

    @ApiModelProperty(value = "手机号", example = "13800138000")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式错误")
    private String mobile; // 可选，与mail二选一

    @ApiModelProperty(value = "密码", example = "123456", required = true)
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度必须为6-20位")
    private String password; // 必选
}