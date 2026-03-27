package org.example.Demo.DTO.Score;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.enummerate.ExamTypeEnum;

@Data
public class AddScoreDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "学生ID")
    private String studentId ;
    @Schema(description = "课程ID")
    private String courseId;
    @Schema(description = "成绩")
    private String score;
    @Schema(description = "考试类型")
    private ExamTypeEnum examType;

}
