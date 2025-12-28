package org.example.Demo.Enummerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 * @Author: Illya
 * @Date: 2024/4/23 上午9:38
 */

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusEnum {

    /**
     * 状态枚举类
     */

    STOP("禁用", 0, "STOP"),
    START("启用", 1, "START");

    private final String name;
    private final Integer code;
    @EnumValue
    private final String enumName;

    StatusEnum(String name, Integer code, String enumName) {
        this.name = name;
        this.code = code;
        this.enumName = enumName;
    }
}
