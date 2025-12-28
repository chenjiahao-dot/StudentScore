package org.example.Demo.DTO.RoleDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 添加次角色
 */
@Data
public class RoleSignInDTO implements Serializable {
    //角色中文名
    @Schema(description = "中文名")
    private String chineseName;
    //角色名称
    @Schema(description = "名称")
    private String name;
    //角色介绍
    @Schema(description = "介绍")
    private String comment;

}
