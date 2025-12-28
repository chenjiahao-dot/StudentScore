package org.example.Demo.DTO.PositionDTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.Enummerate.PositionEnum;
import org.example.Demo.Enummerate.PositionIdentityEnum;
@Data
public class UpdateUserPositionDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "用户ID", required = true)
    private Long id;
    @Schema(description = "岗位名称")
    private PositionEnum positionName;
    @Schema(description = "岗位身份")
    private PositionIdentityEnum identityName;
    @Schema(description = "岗位描述")
    private String description;
}
