package org.example.Demo.pageQuery;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class WarehouseGoodsPageQuery {
    @Schema(description = "仓库物品ID")
    private Long id;

    @Schema(description = "物品名称")
    private String name;

    @Schema(description = "页码", defaultValue = "1", required = true)
    private Integer page;

    @Schema(description = "每页显示记录数", defaultValue = "10", required = true)
    private Integer pageSize;

}
