package org.example.Demo.Enummerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Illya
 * @Date: 2024/7/15 上午9:13
 */
@AllArgsConstructor
@Getter
public enum WarehouseGoodsTypeEnum {

    ARCHITECTURE(0,"建筑"),
    CONSTRUCTION(1,"土木"),
    FACILITY(2, "设备"),
    CONSUMABLES(3, "耗材"),
    MACHINE(4,"电子");


    @EnumValue
    private final Integer code;

    @JsonValue
    private final String name;

    public static WarehouseGoodsTypeEnum toEnum(String goodsType) {
        for (WarehouseGoodsTypeEnum item : WarehouseGoodsTypeEnum.values()) {
            if (item.name.equals(goodsType)) {
                return item;
            }
        }
        return null;
    }
}
