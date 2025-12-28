package org.example.Demo.entity.Position;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.Enummerate.PositionEnum;
import org.example.Demo.Enummerate.PositionIdentityEnum;

import java.util.Date;

@Data
public class Position {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "岗位id")
    private Long id;
    @Schema(description = "用户id")
    private Long userId;
    @Schema(description = "岗位名称")
    private PositionEnum positionName;
    @Schema(description = "岗位身份")
    private PositionIdentityEnum identityName;
    @Schema(description = "岗位描述")
    private String description;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "创建用户")
    private Long createUser;
    @Schema(description = "修改时间")
    private Date updateTime;
    @Schema(description = "修改用户")
    private Long updateUser;
    @Schema(description = "创建用户姓名")
    private String createUserName;
    @Schema(description = "修改用户姓名")
    private String updateUserName;
    public void setCreateTime() {
        this.createTime = new Date();
    }
    public void setUpdateTime() {
        this.updateTime = new Date();
    }
}
