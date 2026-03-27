package org.example.Demo.DTO.Class;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ListClassDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "班级id")
    private Long id;
    @Schema(description = "班级名称")
    private Long className;
    @Schema(description = "页码", defaultValue = "1", required = true)
    private Integer page;
    @Schema(description = "每页显示记录数", defaultValue = "10", required = true)
    private Integer pageSize;

}
