package org.example.Demo.DTO.Course;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ListCourseDTO {
    @Schema(description = "课程名称")
    private String courseName;
    @Schema(description = "页码", defaultValue = "1", required = true)
    private Integer page;
    @Schema(description = "每页显示记录数", defaultValue = "10", required = true)
    private Integer pageSize;
}
