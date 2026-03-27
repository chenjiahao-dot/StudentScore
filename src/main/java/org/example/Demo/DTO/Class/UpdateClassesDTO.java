package org.example.Demo.DTO.Class;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateClassesDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "班级id")
    private Long id;
    @Schema(description = "班级名称")
    private Long className;
    @Schema(description = "年级")
    private Long grade;
}
