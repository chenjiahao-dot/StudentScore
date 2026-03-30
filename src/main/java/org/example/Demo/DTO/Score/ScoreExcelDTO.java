package org.example.Demo.DTO.Score;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.enummerate.ExamTypeEnum;

import java.util.Date;

@Data
public class ScoreExcelDTO {
    /**
     * Excel列名：成绩ID
     */
    @ExcelProperty("成绩ID")
    private Long id;
    /**
     * Excel列名：学生姓名
     */
    @ExcelProperty("学生姓名")
    private String studentName ;
    /**
     * Excel列名：课程名称
     */
    @ExcelProperty("课程名称")
    private String courseName;
    /**
     * Excel列名：成绩
     */
    @ExcelProperty("成绩")
    private String score;
    /**
     * Excel列名：考试类型
     */
    @ExcelProperty("考试类型")
    private String examType;
    /**
     * Excel列名：学期名称
     */
    @ExcelProperty("学期名称")
    private String semesterName;
    /**
     * Excel列名：用户ID
     */
    @ExcelProperty("录入时间")
    private Date createTime;
}
