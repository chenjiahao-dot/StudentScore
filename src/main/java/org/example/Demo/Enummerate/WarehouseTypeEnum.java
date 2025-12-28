package org.example.Demo.Enummerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Illya
 * @Date: 2024/7/15 上午8:58
 */
@Getter
@AllArgsConstructor
public enum WarehouseTypeEnum {

    COMMONALITY(0, "公共仓库"),
    PRIVATELY(1, "私有仓库"),
    INHERENT(2,"固有仓库");

    @EnumValue
    private final Integer code;

    @JsonValue
    private final String name;

    public static WarehouseTypeEnum toEnum(String warehouseType) {
        for (WarehouseTypeEnum warehouseTypeEnum : WarehouseTypeEnum.values()) {
            if (warehouseTypeEnum.name.equals(warehouseType)) {
                return warehouseTypeEnum;
            }
        }
        return null;
    }

}
