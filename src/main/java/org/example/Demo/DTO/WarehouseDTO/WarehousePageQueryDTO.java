package org.example.Demo.DTO.WarehouseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.OrderTypeEnum.OrderTypeEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehousePageQueryDTO {
    @Schema(description = "仓库ID")
    //仓库ID
    private Long id;
    @Schema(description = "仓库姓名")
    //仓库ID
    private String name;
    @Schema(description = "页码", defaultValue = "1")
    //页码
    private Integer page;
    @Schema(description = "每页显示记录数", defaultValue = "10")
    //每页显示记录数
    private Integer pageSize;
    @Schema(description = "排序字段")
    private String sortField;
    @Schema(description = "排序类型")
    private OrderTypeEnum orderType;
}
