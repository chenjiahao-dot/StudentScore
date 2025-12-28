package org.example.Demo.VO.Warehouse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.Enummerate.WarehouseGoodsReturnStatusEnum;
import org.example.Demo.Enummerate.WarehouseOperationStatusEnum;
import org.example.Demo.Enummerate.WarehouseOperationTypeEnum;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleReturnVO {
    @Schema(description = "需要归还的物品ID")
    private Long warehouseGoodsId;
    @Schema(description = "归还的物品数量")
    private Integer quantity;
    @Schema(description = "用户Id")
    private Long userId;
    @Schema(description = "操作状态")
    private WarehouseOperationStatusEnum operationStatus;
    @Schema(description = "操作类型")
    private WarehouseOperationTypeEnum operationType;
    @Schema(description = "归还状态")
    private WarehouseGoodsReturnStatusEnum returnStatus;
}
