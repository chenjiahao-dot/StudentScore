package org.example.Demo.enummerate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 * @Author: Illya
 * @Date: 2024/4/23 上午9:33
 */

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserTypeEnum {

    /**
     * 用户类型枚举类
     */

    STUDENT("学生", "STUDENT"),
    CLASSTEACHER("班主任", "CLASSTEACHER"),
    TEACHER("老师", "TEACHER"),
    ADMIN("管理员", "ADMIN");

    private final String type;
    private final String enumName;

    UserTypeEnum(String type, String enumName) {
        this.type = type;
        this.enumName = enumName;
    }
}
