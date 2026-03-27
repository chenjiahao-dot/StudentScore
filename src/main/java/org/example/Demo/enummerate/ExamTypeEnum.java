package org.example.Demo.enummerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExamTypeEnum {
    /**
     * 考试类型枚举类
     */

    MIDTERM("期中", 0, "MIDTERM"),
    FINAL("期末", 1, "FINAL");
    private final String name;
    private final Integer code;
    @EnumValue
    private final String enumName;

    ExamTypeEnum(String name, Integer code, String enumName) {
        this.name = name;
        this.code = code;
        this.enumName = enumName;
    }


}
