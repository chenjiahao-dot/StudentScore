package org.example.Demo.DTO.PositionDTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.Enummerate.PositionEnum;
import org.example.Demo.Enummerate.PositionIdentityEnum;
@Data
public class PositionPageQueryDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)

    @Schema(description = "用户名称")
    private String name;
    @Schema(description = "岗位名称")
    private PositionEnum positionName;
    @Schema(description = "岗位身份")
    private PositionIdentityEnum identityName;
    @Schema(description = "岗位描述")
    private String description;
    @Schema(description = "页码", defaultValue = "1")
    //页码
    private Integer page;
    @Schema(description = "每页显示记录数", defaultValue = "10")
    //每页显示记录数
    private Integer pageSize;
}
