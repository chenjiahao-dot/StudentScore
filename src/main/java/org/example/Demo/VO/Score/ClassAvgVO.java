package org.example.Demo.VO.Score;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClassAvgVO {
    @Schema(description = "班级名称")
    private String className;
    @Schema(description = "平均分")
    private Long avgScore;
    @Schema(description = "最高分")
    private Float maxScore;
    @Schema(description = "最低分")
    private Float minScore;
}
