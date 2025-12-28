package org.example.Demo.DTO.WarehouseDTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.Enummerate.WarehouseGoodsReturnStatusEnum;
import org.example.Demo.Enummerate.WarehouseOperationStatusEnum;
import org.example.Demo.Enummerate.WarehouseOperationTypeEnum;

@Data
public class ArticleReturnDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "借用或使用申请Id",required = true)
    private Long id;
    @Schema(description = "原因")
    private String use;
}
