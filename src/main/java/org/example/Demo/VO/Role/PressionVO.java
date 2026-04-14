package org.example.Demo.VO.Role;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.enummerate.PrimaryRoleEnum;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PressionVO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "名称")
    private String name;
    @Schema(description = "中文名")
    private String chineseName;
    @Schema(description = "资源路径")
    private String path;
    @Schema(description = "备注")
    private String comment;
    @Schema(description = "主角色枚举对象")
    private PrimaryRoleEnum primaryRoleEnum;

}
