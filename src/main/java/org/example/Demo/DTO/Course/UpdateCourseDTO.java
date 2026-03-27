package org.example.Demo.DTO.Course;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateCourseDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "课程id")
    private Long id;
    @Schema(description = "老师")
    private String teacher;
    @Schema(description = "学分")
    private String credit;
}
