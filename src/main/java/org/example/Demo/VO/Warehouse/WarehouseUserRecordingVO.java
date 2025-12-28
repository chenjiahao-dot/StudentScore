package org.example.Demo.VO.Warehouse;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.Enummerate.DirectionEnum;
import org.example.Demo.Enummerate.WarehouseOperationStatusEnum;
import org.example.Demo.Enummerate.WarehouseOperationTypeEnum;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseUserRecordingVO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "id")
    private Long id;
    @Schema(description = "仓库物品ID")
    private Long warehouseGoodsId;
    @Schema(description = "方向")
    private String direction;

    private List<DirectionEnum> directions;

    @Schema(description = "物品名称")
    private String name;
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "操作类型 ")
    private WarehouseOperationTypeEnum operationType;
    @Schema(description = "操作数量")
    private Integer quantity;
    @Schema(description = "用途")
    private String use;
    @Schema(description = "借用数量")
    private Long lent;
    @Schema(description = "物品型号")
    private String model;
    @Schema(description = "物品单位")
    private String unit;
    @Schema(description = "物品类型")
    private String goodsType;
    @Schema(description = "操作状态")
    private WarehouseOperationStatusEnum operationStatus;
    @Schema(description = "归还时间")
    private Date returnTime;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "修改时间")
    private Date updateTime;


}
