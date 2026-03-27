package org.example.Demo.DTO.SemesterController;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ListSemesterDTO {
    @Schema(description = "学期名称")
    private String semesterName ;
    @Schema(description = "页码", defaultValue = "1", required = true)
    private Integer page;
    @Schema(description = "每页显示记录数", defaultValue = "10", required = true)
    private Integer pageSize;
}
