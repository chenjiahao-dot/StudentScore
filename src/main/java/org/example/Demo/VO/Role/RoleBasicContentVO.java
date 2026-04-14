package org.example.Demo.VO.Role;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.enummerate.PrimaryRoleEnum;
import org.example.Demo.enummerate.UserTypeEnum;

import java.util.Date;

/**
 * @Author: Illya
 * @Date: 2024/3/30 7:28
 */

@Data
public class RoleBasicContentVO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "名称")
    private String name;
    @Schema(description = "中文名")
    private String chineseName;
    @Schema(description = "主角色")
    private Integer prima;
    @Schema(description = "介绍")
    private String comment;
    @Schema(description = "主角色枚举对象")
    private PrimaryRoleEnum primaryRoleEnum;
}
