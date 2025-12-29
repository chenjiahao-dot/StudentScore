package org.example.Demo.entity.Role;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.Enummerate.PrimaryRoleEnum;

import java.util.Date;
@Data
public class UserRoleName {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "角色ID")
    private Long roleId;
    @Schema(description = "用户姓名")
    private String userName;
    @Schema(description = "角色姓名")
    private PrimaryRoleEnum roleName;
    @Schema(description = "角色中文名")
    private String roleChineseName;
    @Schema(description = "角色简介")
    private String comment;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "修改时间")
    private Date updateTime;
    @Schema(description = "创建用户")
    private Long createUser;
    @Schema(description = "修改用户")
    private Long updateUser;
    @Schema(description = "创建用户姓名")
    private String createUserName;
    @Schema(description = "修改用户姓名")
    private String updateUserName;

}
