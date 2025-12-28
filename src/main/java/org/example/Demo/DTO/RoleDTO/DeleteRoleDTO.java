package org.example.Demo.DTO.RoleDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class DeleteRoleDTO {
    @Schema(description = "要删除的角色id")
    private List<Long> id;
}
