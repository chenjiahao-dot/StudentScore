package org.example.Demo.DTO.WarehouseDTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiveWarehouseManageDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "需要使用的仓库ID")
    private Long warehouseGoodsId;
    @Schema(description = "数量")
    private Integer quantity;
    @Schema(description = "数量")
    private String use;
}
