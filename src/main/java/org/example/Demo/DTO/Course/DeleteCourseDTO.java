package org.example.Demo.DTO.Course;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
@Data
public class DeleteCourseDTO {
    @Schema(description = "id")
    private List<Long> id;
}
