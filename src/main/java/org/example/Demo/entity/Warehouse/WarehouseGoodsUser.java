package org.example.Demo.entity.Warehouse;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.example.Demo.Enummerate.*;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseGoodsUser {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "id")
    private Long id;
    @Schema(description = "对应操作记录ID")
    private Long parentId;
    @Schema(description = "仓库物品ID")
    private Long warehouseGoodsId;
    @Schema(description = "用户ID")
    private Long userId;
    @Schema(description = "操作类型 ")
    private WarehouseOperationTypeEnum operationType;
    @Schema(description = "操作数量")
    private Integer quantity;
    @Schema(description = "用途")
    private String use;
    @Schema(description = "操作状态")
    private WarehouseOperationStatusEnum operationStatus;
    @Schema(description = "归还状态")
    private WarehouseGoodsReturnStatusEnum returnStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS Z", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS Z", timezone = "GMT+8")
    @Schema(description = "修改时间")
    private Date updateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS Z", timezone = "GMT+8")
    @Schema(description = "归还时间")
    private Date returnTime;
    public void setCreateTime() {
        this.createTime = new java.util.Date();
    }
    public void setUpdateTime() {
        this.updateTime = new java.util.Date();
    }
    public void setReturnTime() {
        this.returnTime = new java.util.Date();
    }
}
