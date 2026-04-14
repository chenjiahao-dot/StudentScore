package org.example.Demo.DTO.Permission;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.enummerate.OrderTypeEnum;

import java.io.Serializable;

/**
 * 分页查询权限信息
 */
@Data
public class PermissionPageQueryDTO {
    @Schema(description = "权限名称")
    private String name;
    @Schema(description = "页码", defaultValue = "1", required = true)
    private Integer page;
    @Schema(description = "每页显示记录数", defaultValue = "10", required = true)
    private Integer pageSize;
    @Schema(description = "排序字段")
    private String sortField;
    @Schema(description = "排序类型")
    private OrderTypeEnum orderType;
}
