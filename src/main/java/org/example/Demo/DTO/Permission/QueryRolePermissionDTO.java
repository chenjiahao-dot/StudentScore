package org.example.Demo.DTO.Permission;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QueryRolePermissionDTO {
    @Schema(description = "权限名称")
    private String name;
}
