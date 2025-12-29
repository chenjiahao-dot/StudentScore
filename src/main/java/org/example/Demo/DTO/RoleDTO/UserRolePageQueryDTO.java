package org.example.Demo.DTO.RoleDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.OrderTypeEnum.OrderTypeEnum;

@Data
public class UserRolePageQueryDTO {
    @Schema(description = "用户姓名")
    private String userName;
    @Schema(description = "角色姓名")
    private String roleName;
    @Schema(description = "页码", defaultValue = "1", required = true)
    private Integer page;

    //每页显示记录数
    @Schema(description = "每页显示记录数", defaultValue = "10", required = true)
    private Integer pageSize;

    @Schema(description = "排序字段,默认为修改时间", defaultValue = "updateTime")
    private String sortField;

    @Schema(description = "排序类型，默认为ASC升序排序", defaultValue = "ASC")
    private OrderTypeEnum orderType;
}
