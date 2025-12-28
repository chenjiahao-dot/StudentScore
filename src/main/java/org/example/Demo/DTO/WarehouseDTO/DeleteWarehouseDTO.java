package org.example.Demo.DTO.WarehouseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteWarehouseDTO {
    @Schema(description = "要删除的角色id")
    private Long id;
}
