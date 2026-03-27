package org.example.Demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Semester {
    @TableId(type = IdType.AUTO) // 数据库自增
    private Long id;
    @Schema(description = "学期名称")
    private String semesterName ;
    @Schema(description = "开始时间(使用13位时间戳)", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    @Schema(description = "结束时间(使用13位时间戳)", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    @Schema(description = "创建时间")
    private Date createTime;
}
