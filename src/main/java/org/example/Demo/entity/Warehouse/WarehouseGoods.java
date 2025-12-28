package org.example.Demo.entity.Warehouse;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.Enummerate.DirectionEnum;
import org.example.Demo.Enummerate.PrimaryRoleEnum;
import org.example.Demo.Enummerate.WarehouseGoodsTypeEnum;
import org.example.Demo.Enummerate.WarehouseTypeEnum;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseGoods {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "id")
    private Long id;
    @Schema(description = "仓库类型")
    private WarehouseTypeEnum warehouseType;
    @Schema(description = "物品类型")
    private WarehouseGoodsTypeEnum goodsType;
    @Schema(description = "物品名称")
    private String name;
    @Schema(description = "规格型号")
    private String model;
    @Schema(description = "仓库ID")
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
    @Schema(description = "总数量")
    private Long collect;
    @Schema(description = "总数量")
    private Long disuse;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "创建用户")
    private Long createUser;
    @Schema(description = "创建时用户身份")
    private PrimaryRoleEnum createUserPrimaryRole;
    @Schema(description = "修改时间")
    private Date updateTime;
    @Schema(description = "修改用户")
    private Long updateUser;
    @Schema(description = "创建用户名")
    private String createUserName;
    @Schema(description = "更新用户名")
    private String updateUserName;

    public void setCreateTime() {
        this.createTime = new Date();
    }
    public void setUpdateTime() {
        this.updateTime = new Date();
    }
}
