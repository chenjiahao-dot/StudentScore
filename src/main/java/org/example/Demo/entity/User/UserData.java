package org.example.Demo.entity.User;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.Enummerate.SexEnum;
import org.example.Demo.OrderTypeEnum.UserTypeEnum;

/**
 * @Author: Illya
 * @Date: 2024/5/4 上午10:55
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "邮箱")
    private String mail;
    @Schema(description = "学号/工号")
    private String number;
    @Schema(defaultValue = "身份")
    private UserTypeEnum userTypeEnum;
    @Schema(description = "性别(MAN男,WOMAN女)")
    private SexEnum sexEnum;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "用户名")
    private String userName;
}
