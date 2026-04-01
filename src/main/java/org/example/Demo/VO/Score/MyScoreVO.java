package org.example.Demo.VO.Score;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.enummerate.ExamTypeEnum;

import java.util.Date;
@Data
public class MyScoreVO {
    @Schema(description = "成绩ID")
    private Long id;
    @Schema(description = "学生ID")
    private Long studentId ;
    @Schema(description = "课程ID")
    private Long courseId;
    @Schema(description = "成绩")
    private String score;
    @Schema(description = "考试类型")
    private ExamTypeEnum examType;
    @Schema(description = "录入时间")
    private Date createTime;
}
