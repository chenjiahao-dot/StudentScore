package com.example.pageQuery;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.Enummerate.DirectionEnum;

import java.util.List;
@Data

public class MyApplicationRecordPageQuery {
    @Schema(description = "方向枚举对象列表", defaultValue = "BASICS", required = true)
    private List<DirectionEnum> directionEnumList;
    @Schema(description = "物品名称")
    private String name;
    @Schema(description = "申请类型")
    private String operationType;
    @Schema(description = "数量")
    private Integer quantity;
    @Schema(description = "审批状态")
    private String operationStatus;
    @Schema(description = "用途")
    private String use;
    @Schema(description = "页码", defaultValue = "1", required = true)
    private Integer page;
    @Schema(description = "每页显示记录数", defaultValue = "10", required = true)
    private Integer pageSize;
}
