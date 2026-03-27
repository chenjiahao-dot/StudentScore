package org.example.Demo.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Users {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "姓名(真实姓名不可修改)")
    private String name;
    @Schema(description = "用户名(用户名可修改)")
    private String userName;
    @Schema(description = "用户类型枚举类STUDENT学生,TEACHER教师")
    private String userTypeEnum;
    @Schema(description = "性别枚举对象WOMAN女,MAN男")
    private String sexEnum;
    @Schema(description = "邮箱")
    private String mail;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "班级ID")
    private Long classId;
}
