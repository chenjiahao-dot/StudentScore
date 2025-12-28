package org.example.Demo.VO.roleVO;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.Enummerate.PrimaryRoleEnum;

import java.util.Date;

/**
 * 输出角色信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolesListAllVO {

    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "ID")
    private Long id;

    @Schema(description = "角色名")
    private String chineseName;

    @Schema(description = "是否为主角色,0为次角色,1为主角色")
    private Integer prima;

    @Schema(description = "主角色枚举对象")
    private PrimaryRoleEnum primaryRoleEnum;

    @Schema(description = "介绍")
    private String comment;

    @Schema(description = "创建时间")
    private Date createTime;

    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "创建用户ID")
    private Long createUser;

    @Schema(description = "更新时间")
    private Date updateTime;

    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "更新用户ID")
    private Long updateUser;

    @Schema(description = "创建用户姓名")
    private String createUserName;

    @Schema(description = "更新用户姓名")
    private String updateUserName;
}
