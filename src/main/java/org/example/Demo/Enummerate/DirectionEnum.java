package org.example.Demo.Enummerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Illya
 * @Date: 2024/4/17 下午8:06
 */

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DirectionEnum {

    /**
     * 方向枚举类
     **/

    CAD("建模设计", "CAD"),
    BASICS("工程设计", "BASICS"),
    AM("平面设计", "AM"),
    MD("模型设计", "MD");
//    BASICS("基础", "BASICS");

    private final String name;
    @EnumValue
    private final String enumName;

    DirectionEnum(String name, String enumName) {
        this.name = name;
        this.enumName = enumName;
    }

    public static DirectionEnum getDirection(String name) {
        for (DirectionEnum directionEnum : values()) {
            if (directionEnum.getName().equals(name) || directionEnum.getEnumName().equals(name) || directionEnum.name.equals(name)) {
                return directionEnum;
            }
        }
        return null;
    }

    public static List<DirectionEnum> toDirectionList(String[] split) {

        if (split == null || split.length == 0) {
            return null;
        }

        List<DirectionEnum> list = new ArrayList<DirectionEnum>();
        for (String s : split) {
            DirectionEnum item = getDirection(s);
            if (item != null) {
                list.add(item);
            }
        }
        return list;
    }
}
