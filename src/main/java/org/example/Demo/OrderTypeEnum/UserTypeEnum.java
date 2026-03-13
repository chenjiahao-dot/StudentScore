package org.example.Demo.OrderTypeEnum;

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
    ADMIN("管理员", "ADMIN"),
    TEACHER("老师", "TEACHER"),
    STUDENT("学生", "STUDENT");

    private final String type;
    private final String enumName;

    UserTypeEnum(String type, String enumName) {
        this.type = type;
        this.enumName = enumName;
    }
}
