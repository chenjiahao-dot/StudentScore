package org.example.Demo.Enummerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PositionEnum {
    TECHNOLOGICAL_POST("技术岗位",0,"TECHNOLOGICAL_POST"),
    MANAGERIAL_POSITION("管理岗位",1,"MANAGERIAL_POSITION");
    private final String type;
    private final Integer code;
    @EnumValue
    private final String enumName;
    PositionEnum(String type, Integer code, String enumName) {
        this.type = type;
        this.code = code;
        this.enumName = enumName;
    }
    public  static PositionEnum getEnumByCode(Integer code){
        for (PositionEnum positionEnum : PositionEnum.values()) {
            if (positionEnum.getCode().equals(code)) {
                return positionEnum;
            }
        }
        return null;
    }
}
