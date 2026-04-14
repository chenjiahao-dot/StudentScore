package org.example.Demo.DTO.Role;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.enummerate.OrderTypeEnum;

import java.io.Serializable;

@Data
public class RoleIdPermissionDTO implements Serializable {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "角色id")
    private Long id;
    @Schema(description = "页码", defaultValue = "1", required = true)
    private Integer page;
    @Schema(description = "每页显示记录数", defaultValue = "10", required = true)
    private Integer pageSize;
}
