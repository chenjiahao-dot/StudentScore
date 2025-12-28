package com.example.pageQuery;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.Enummerate.DirectionEnum;
import org.example.Demo.Enummerate.WarehouseGoodsTypeEnum;
import org.example.Demo.Enummerate.WarehouseTypeEnum;

import java.util.List;

@Data
public class WarehouseGoodsPageQuery {
    @Schema(description = "仓库物品ID")
    private Long id;
    @Schema(description = "方向枚举对象列表", defaultValue = "BASICS", required = true)
    private List<DirectionEnum> directionEnumList;
    @Schema(description = "物品名称")
    private String name;
    @Schema(description = "物品类型")
    private WarehouseGoodsTypeEnum goodsType;
    @Schema(description = "仓库类型")
    private WarehouseTypeEnum warehouseType;
    @Schema(description = "页码", defaultValue = "1", required = true)
    private Integer page;

    @Schema(description = "每页显示记录数", defaultValue = "10", required = true)
    private Integer pageSize;

}
