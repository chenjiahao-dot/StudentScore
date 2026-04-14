package org.example.Demo.VO.Role;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.enummerate.PrimaryRoleEnum;
import org.example.Demo.enummerate.UserTypeEnum;

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

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "角色名")
    private String chineseName;

    @Schema(description = "主角色")
    private Integer prima;

    @Schema(description = "主角色枚举对象")
    private PrimaryRoleEnum primaryRoleEnum;

    @Schema(description = "介绍")
    private String comment;


}
