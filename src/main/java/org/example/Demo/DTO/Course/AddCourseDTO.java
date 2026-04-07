package org.example.Demo.DTO.Course;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddCourseDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "课程名称")
    private String courseName;
    @Schema(description = "老师")
    private String teacherName;
}
