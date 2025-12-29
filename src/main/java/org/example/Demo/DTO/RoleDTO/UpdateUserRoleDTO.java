package org.example.Demo.DTO.RoleDTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateUserRoleDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "用户ID", required = true)
    private Long id;
    @Schema(description = "角色ID")
    private Long roleId;
}
