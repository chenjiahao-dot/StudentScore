package org.example.Demo.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.enummerate.ExamTypeEnum;

import java.util.Date;
@Data
public class ScoreExcel {
    @Schema(description = "成绩ID")
    private Long id;
    @Schema(description = "学生ID")
    private Long studentId ;
    @Schema(description = "课程ID")
    private Long courseId;
    @Schema(description = "成绩")
    private Long score;
    @Schema(description = "考试类型")
    private String examType;
    @Schema(description = "学期ID")
    private Long semesterId;
    @Schema(description = "录入时间")
    private Date createTime;
}
