package org.example.Demo.VO.Warehouse;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.Enummerate.DirectionEnum;
import org.example.Demo.Enummerate.WarehouseGoodsTypeEnum;
import org.example.Demo.Enummerate.WarehouseOperationTypeEnum;

import javax.management.OperationsException;
import java.util.Date;
import java.util.List;

@Data
public class WarehouseUserManageVO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "仓库物品id")
    private Long id;
    @Schema(description = "仓库类型")
    private Long warehouseType;
    @Schema(description = "物品类型")
    private WarehouseGoodsTypeEnum goodsType;
    @Schema(description = "物品名称")
    private String name;
    @Schema(description = "操作类型 ")
    private WarehouseOperationTypeEnum operationType;
    @Schema(description = "操作数量")
    private Integer quantity;
    @Schema(description = "规格型号")
    private String model;
    @Schema(description = "物品方向String类型")
    private String direction;
    @Schema(description = "物品方向")
    private List<DirectionEnum> directionEnumList;
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
    @Schema(description = "领取数量")
    private Long collect;
    @Schema(description = "报废数量")
    private Long disuse;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "创建用户")
    private Long createUser;
    @Schema(description = "修改时间")
    private Date updateTime;
    @Schema(description = "修改用户")
    private Long updateUser;
}
