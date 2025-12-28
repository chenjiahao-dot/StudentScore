package org.example.Demo.VO.PositionVO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.Enummerate.PositionEnum;
import org.example.Demo.Enummerate.PositionIdentityEnum;
import org.example.Demo.Enummerate.SexEnum;
import org.example.Demo.Enummerate.StatusEnum;

import java.util.Date;
@Data
public class PositionPageQueryVO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "主键ID")
    private Long id;
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "姓名(真实姓名不可修改)")
    private String name;
    @Schema(description = "用户名(用户名可修改)")
    private String userName;
    @Schema(description = "用户类型枚举类STUDENT学生,TEACHER教师")
    private String userTypeEnum;
    @Schema(description = "性别枚举对象WOMAN女,MAN男")
    private SexEnum sexEnum;
    @Schema(description = "账号状态枚举类")
    private StatusEnum StatusEnum;
    @Schema(description = "岗位名称")
    private PositionEnum  PositionName;
    @Schema(description = "身份名称")
    private PositionIdentityEnum identityName;
    @Schema(description = "岗位描述")
    private String description;
    @Schema(description = "学号/工号")
    private String number;
    @Schema(description = "邮箱")
    private String mail;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "更新时间")
    private Date updateTime;
}
