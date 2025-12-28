package org.example.Demo.entity.Warehouse;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.Enummerate.PrimaryRoleEnum;

import java.util.Date;

/**
 * @Author: Illya
 * @Date: 2024/7/15 上午9:32
 * 仓库实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "id")
    private Long id;
    @Schema(description = "仓库名称")
    private String name;
    @Schema(description = "仓库地址")
    private String address;
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
