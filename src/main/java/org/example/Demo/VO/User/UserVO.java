package org.example.Demo.VO.User;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.enummerate.PrimaryRoleEnum;
import org.example.Demo.enummerate.SexEnum;
import org.example.Demo.enummerate.UserTypeEnum;

/**
 * @Author: Illya
 * @Date: 2024/7/17 下午1:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserVO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "姓名(真实姓名不可修改)")
    private String name;
    @Schema(description = "用户名(用户名可修改)")
    private String userName;
    @Schema(description = "头像图片地址")
    private String imageAddress;
    @Schema(description = "用户类型枚举对象,STUDENT学生,TEACHER教师,STATISTICS班主任")
    private UserTypeEnum userTypeEnum;
    @Schema(description = "邮箱")
    private String mail;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "性别枚举对象 WOMAN 女 MAN 男")
    private SexEnum sexEnum;
    @Schema(description = "主角色枚举对象")
    private PrimaryRoleEnum primaryRoleEnum;
}
