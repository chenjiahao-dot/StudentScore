package org.example.Demo.VO.Course;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CourseVO {
    @Schema(description = "课程id")
    private Long id;
    @Schema(description = "班级id")
    private String classId;
    @Schema(description = "课程名称")
    private String courseName;
}