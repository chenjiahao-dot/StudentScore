package org.example.Demo.Enummerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Illya
 * @Date: 2024/7/15 上午9:17
 */
@Getter
@AllArgsConstructor
public enum WarehouseOperationTypeEnum {

    COLLECT(0, "领取"),
    LENT(1, "借用"),
    RETURN(2, "归还"),
    ENTER(3, "入库"),
    OUT(4, "出库"),
    DISUSE(5, "报废");

    @EnumValue
    private final Integer code;
    @JsonValue
    private final String name;
}
