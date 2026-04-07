package org.example.Demo.DTO.Score;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.enummerate.OrderTypeEnum;
@Data
public class PageQueryClassStudentScoreDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "成绩")
    private String score;
    @Schema(description = "页码", defaultValue = "1", required = true)
    private Integer page;
    @Schema(description = "每页显示记录数", defaultValue = "10", required = true)
    private Integer pageSize;
    @Schema(description = "排序字段")
    private String sortField;
    @Schema(description = "排序类型")
    private OrderTypeEnum orderType;
}
