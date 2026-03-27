package org.example.Demo.enummerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 * @Author: Illya
 * @Date: 2024/4/23 上午9:29
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SexEnum {

    /**
     * 性别枚举类
     **/

    WOMAN("女", 0, "WOMAN"),
    MAN("男", 1, "MAN");

    private final String type;
    private final Integer code;
    @EnumValue
    private final String enumName;

    SexEnum(String type, Integer code, String enumName) {
        this.type = type;
        this.code = code;
        this.enumName = enumName;
    }

    public static SexEnum getSexEnum(String name) {
        for (SexEnum sexEnum : SexEnum.values()) {
            if (sexEnum.type.equals(name)) {
                return sexEnum;
            }
        }
        return null;
    }
}
