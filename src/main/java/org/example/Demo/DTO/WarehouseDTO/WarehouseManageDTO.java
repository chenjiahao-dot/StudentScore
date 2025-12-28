package org.example.Demo.DTO.WarehouseDTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.Enummerate.DirectionEnum;
import org.example.Demo.Enummerate.WarehouseGoodsTypeEnum;
import org.example.Demo.Enummerate.WarehouseTypeEnum;
import org.example.Demo.OrderTypeEnum.OrderTypeEnum;

import java.util.List;

@Data
public class WarehouseManageDTO {
    //仓库物品ID
    @Schema(description = "仓库物品ID")
    private Long id;
    //物品名称
    @Schema(description = "物品名称")
    private String name;
    //页码
    @Schema(description = "页码", defaultValue = "1")
    private Integer page;

    //每页显示记录数
    @Schema(description = "每页显示的记录数", defaultValue = "10")
    private Integer pageSize;
    @Schema(description = "排序字段")
    private String sortField;

}
