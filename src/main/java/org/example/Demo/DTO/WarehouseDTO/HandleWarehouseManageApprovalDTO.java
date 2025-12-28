package org.example.Demo.DTO.WarehouseDTO;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.Enummerate.WarehouseOperationStatusEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HandleWarehouseManageApprovalDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "仓库ID")
    private Long id;
    @Schema(description = "操作状态")
    private WarehouseOperationStatusEnum operationStatus;
}
