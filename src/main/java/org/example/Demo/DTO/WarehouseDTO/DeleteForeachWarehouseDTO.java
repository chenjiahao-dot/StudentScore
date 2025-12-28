package org.example.Demo.DTO.WarehouseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class DeleteForeachWarehouseDTO {
    @Schema(description = "需要删除的ID列表")
    private List<Long> id;
}
