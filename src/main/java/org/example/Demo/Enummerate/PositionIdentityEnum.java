package org.example.Demo.Enummerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.List;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PositionIdentityEnum {
    BOARD_OF_DIRECTORS("董事会",0,"BOARD_OF_DIRECTORS"),
    PROGRAMMER("程序员",1,"PROGRAMMER"),
    SECRETARY("秘书",2,"SECRETARY");
    private final String name;
    private final Integer priority;
    @EnumValue
    private final String enumName;

    PositionIdentityEnum(String name, Integer priority, String enumName) {
        this.name = name;
        this.priority = priority;
        this.enumName = enumName;
    }
    public static List<PositionIdentityEnum> getBOARDPrimaryRoleEnum() {

        return List.of(BOARD_OF_DIRECTORS);

    }
    public static List<PositionIdentityEnum> getPROGRAMMERPrimaryRoleEnum() {

        return List.of(PROGRAMMER);

    }
    public static List<PositionIdentityEnum> getSECRETARYPrimaryRoleEnum() {

        return List.of(SECRETARY);

    }
    public static PositionIdentityEnum getPositionIdentityEnumByName(String name) {
        for (PositionIdentityEnum identityEnumEnum : PositionIdentityEnum.values()) {
            if (identityEnumEnum.getName().equals(name)) {
                return identityEnumEnum;
            }
        }
        return null;
    }
    public  static PositionIdentityEnum getPositionIdentityEnumByPriority(Integer Priority){
        for (PositionIdentityEnum identityEnum : PositionIdentityEnum.values()) {
            if (identityEnum.getName().equals(Priority.toString())) {
                return identityEnum;
            }
        }
        return null;
    }
}
