package org.example.Demo.Enummerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Illya
 * @Date: 2024/7/15 上午9:24
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum WarehouseGoodsReturnStatusEnum {

    NOT(0, "未归还"),
    ALREADY(1, "已归还"),
    NO_NEED(2, "无需归还");

    @EnumValue
    private final Integer code;
    
    private final String name;
}
