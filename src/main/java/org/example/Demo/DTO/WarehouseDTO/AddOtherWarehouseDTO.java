package org.example.Demo.DTO.WarehouseDTO;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.Enummerate.DirectionEnum;
import org.example.Demo.Enummerate.WarehouseGoodsTypeEnum;
import org.example.Demo.Enummerate.WarehouseTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddOtherWarehouseDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "仓库类型")
    private WarehouseTypeEnum warehouseType;
    @Schema(description = "物品类型")
    private WarehouseGoodsTypeEnum goodsType;
    @Schema(description = "物品名称")
    private String name;
    @Schema(description = "规格型号")
    private String model;
    @Schema(description = "仓库id")
    private Long warehouseId;
    @Schema(description = "仓库类型")
    private DirectionEnum direction;
    @Schema(description = "备注")
    private String comment;
    @Schema(description = "单位")
    private String unit;
    @Schema(description = "总数量")
    private Long total;
    @Schema(description = "剩余数量")
    private Long residual;
    @Schema(description = "出借数量")
    private Long lent;
    @Schema(description = "报废数量")
    private Long collect;
    @Schema(description = "领取数量")
    private Long disuse;

}
