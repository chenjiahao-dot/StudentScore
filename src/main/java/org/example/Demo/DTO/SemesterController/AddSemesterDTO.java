package org.example.Demo.DTO.SemesterController;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.enummerate.ExamTypeEnum;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AddSemesterDTO {
    @Schema(description = "学期名称")
    private String semesterName ;
    @Schema(description = "开始时间(使用13位时间戳)", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;
    @Schema(description = "结束时间(使用13位时间戳)", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;
}
