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
public class UpdateWarehouseDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "仓库ID", required = true)
    private Long id;
    @Schema(description = "仓库名称")
    private String name;
    @Schema(description = "仓库地址")
    private  String address;
}
