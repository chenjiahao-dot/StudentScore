package org.example.Demo.Enummerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: Illya
 * @Date: 2024/7/15 上午9:24
 */
@Getter
@AllArgsConstructor
public enum WarehouseOperationStatusEnum {

    WAIT(0, "待审批"),
    START(1, "已通过"),
    STOP(2, "未通过");


    @EnumValue
    private final Integer code;
    @JsonValue
    private final String name;
}
