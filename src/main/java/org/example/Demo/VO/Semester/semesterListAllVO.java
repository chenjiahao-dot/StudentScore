package org.example.Demo.VO.Semester;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
@Data
public class semesterListAllVO {
    @Schema(description = "学期id")
    private Long id;
    @Schema(description = "学期名称")
    private String semesterName ;
    @Schema(description = "学期开始时间")
    private Date startTime;
    @Schema(description = "学期结束时间")
    private Date endTime;
    @Schema(description = "创建时间")
    private Date createTime;
}
