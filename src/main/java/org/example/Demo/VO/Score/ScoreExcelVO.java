package org.example.Demo.VO.Score;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.example.Demo.enummerate.ExamTypeEnum;

@Data
public class ScoreExcelVO {

    @ExcelProperty("学生ID")
    private Long studentId;

    @ExcelProperty("课程ID")
    private Long  courseId;

    @ExcelProperty("分数")
    private Long  score;

    @ExcelProperty("考试类型")
    private String examType;

    @ExcelProperty("学期ID")
    private Long  semesterId;
}