package org.example.Demo.VO.Course;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class courseListAllVO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "课程id")
    private Long id;
    @Schema(description = "课程名称")
    private String courseName;
    @Schema(description = "老师")
    private String teacher;
    @Schema(description = "学分")
    private String credit;
}
