package org.example.Demo.VO.Warehouse;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseVO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "仓库ID")
    private Long id;
    @Schema(description = "仓库姓名")
    private String name;
    @Schema(description = "仓库地址")
    private String address;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "创建用户")
    private Long createUser;
    @Schema(description = "修改时间")
    private Date updateTime;
    @Schema(description = "修改用户")
    private Long updateUser;
    @Schema(description = "创建用户名")
    private String createUserName;
    @Schema(description = "更新用户名")
    private String updateUserName;
}
