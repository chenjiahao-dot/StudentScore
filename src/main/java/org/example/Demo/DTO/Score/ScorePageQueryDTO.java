package org.example.Demo.DTO.Score;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ScorePageQueryDTO {
    @Schema(description = "学生ID")
    private String studentId ;
    @Schema(description = "课程ID")
    private String courseId;
    @Schema(description = "页码", defaultValue = "1", required = true)
    private Integer page;
    @Schema(description = "每页显示记录数", defaultValue = "10", required = true)
    private Integer pageSize;
}
