package org.example.Demo.enummerate;

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
    CLASSTEACHER("班主任", 1, "CLASSTEACHER"),
    TEACHER("老师", 2, "TEACHER"),
    STUDENT("学生", 3, "STUDENT");

    private final String name;
    private final Integer priority;

    @EnumValue
    private final String enumName;

    PrimaryRoleEnum(String name, Integer priority, String enumName) {
        this.name = name;
        this.priority = priority;
        this.enumName = enumName;
    }

    public static List<PrimaryRoleEnum> getStudentPrimaryRoleEnum() {

        return List.of(STUDENT);

    }

    public static List<PrimaryRoleEnum> getTeacherPrimaryRoleEnum() {
        return List.of(CLASSTEACHER,TEACHER);
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
