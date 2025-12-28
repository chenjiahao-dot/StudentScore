package org.example.Demo.Enummerate;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Illya
 * @Date: 2024/4/18 下午6:20
 */
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PrimaryRoleEnum {

    /**
     * 主角色枚举类
     **/

    ADMIN("管理员", 0, "ADMIN"),
    PROJECT_MANAGE("项目经理", 1, "PROJECT_MANAGE"),
    SUPERVISOR("主管", 2, "SUPERVISOR"),
    PROJECT_LEADER("项目组长", 3, "PROJECT_LEADER"),
    PROJECT_MANAGE_SECRETARY("项目经理秘书", 4, "PROJECT_MANAGE_SECRETARY"),
    WAREHOUSE_MANAGE("仓库经理", 5, "WAREHOUSE_MANAGE"),
    PROJECT_CREW("项目组员", 6, "PROJECT_CREW"),
    INTERN("实习生", 7, "INTERN"),;
    private final String name;
    private final Integer priority;

    @EnumValue
    private final String enumName;

    PrimaryRoleEnum(String name, Integer priority, String enumName) {
        this.name = name;
        this.priority = priority;
        this.enumName = enumName;
    }

    public static List<PrimaryRoleEnum> getMANAGEPrimaryRoleEnum() {

        return List.of(PROJECT_MANAGE_SECRETARY, WAREHOUSE_MANAGE);

    }

    public static List<PrimaryRoleEnum> getLeaderPrimaryRoleEnum() {
        return List.of(PROJECT_LEADER, SUPERVISOR);
    }
    public static List<PrimaryRoleEnum> getCREWPrimaryRoleEnum() {
        return List.of(PROJECT_CREW,INTERN);
    }
    public static Boolean contrast(PrimaryRoleEnum role1, PrimaryRoleEnum role2) {
        return role1.getPriority() <= role2.getPriority();
    }

    public static List<PrimaryRoleEnum> getLowPrimaryRoleEnum(PrimaryRoleEnum role) {
        PrimaryRoleEnum[] primaryRoleEnum = PrimaryRoleEnum.values();
        List<PrimaryRoleEnum> lowPrimaryRoleEnum = new ArrayList<PrimaryRoleEnum>();
        for (PrimaryRoleEnum roleEnum : primaryRoleEnum) {
            if (role.getPriority() <= roleEnum.getPriority()) {
                lowPrimaryRoleEnum.add(roleEnum);
            }
        }
        return lowPrimaryRoleEnum;
    }

    public static PrimaryRoleEnum getPrimaryRoleEnumByName(String name) {
        for (PrimaryRoleEnum roleEnum : PrimaryRoleEnum.values()) {
            if (roleEnum.getName().equals(name)) {
                return roleEnum;
            }
        }
        return null;
    }

    public static PrimaryRoleEnum getPrimaryRoleEnumByPriority(Integer Priority) {
        for (PrimaryRoleEnum roleEnum : PrimaryRoleEnum.values()) {
            if (roleEnum.getPriority().equals(Priority)) {
                return roleEnum;
            }
        }
        return null;
    }
}
