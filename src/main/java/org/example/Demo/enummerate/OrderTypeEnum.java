package org.example.Demo.enummerate;

import lombok.Getter;

/**
 * @Author: Illya
 * @Date: 2024/5/16 下午3:05
 */
@Getter
public enum OrderTypeEnum {
    /**
     * 排序类型枚举
     */

    ASC("升序", "ASC"),
    DESC("降序", "DESC");

    private final String name;
    private final String enumName;

    OrderTypeEnum(String name, String enumName) {
        this.name = name;
        this.enumName = enumName;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
