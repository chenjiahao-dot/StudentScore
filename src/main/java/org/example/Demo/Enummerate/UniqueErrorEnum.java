package org.example.Demo.Enummerate;

import lombok.Getter;

/**
 * @Author: Illya
 * @Date: 2024/5/10 下午1:42
 */
@Getter
public enum UniqueErrorEnum {


    /**
     * 唯一字段错误枚举类
     */
    USER_MAIL("user.mail", "用户邮箱已存在"),
    USER_NUMBER("user.number", "用户学号/工号已存在"),
    USER_USER_NAME("user.username", "用户用户名已存在"),
    USER_MOBILE("user.mobile", "用户手机号已存在"),
    USER_PHONE("user.phone", "用户手机号已存在"),
    TRAIN_SCORE_SCORE_PROJECT("train_score.train_score_train_project_user", "学生对应训练项目成绩已存在"),
    USER_ROLE_USER_ROLE("user_role.user_role_user_role", "用户已绑定相应角色"),
    USER_DIRECTION_USER_DIRECTION("user_direction.user_direction_direction_user", "用户已绑定对应方向"),
    ROLE_PERMISSION("role_permission.role_permission", "角色已绑定对应权限"),
    ROLE_MENU_ROLE_MENU("role_menu.role_menu_rome_menu", "角色已绑定对应菜单"),
    PROBLEM_SER_USER_USER_TYPE("problem_set_user.problem_set_user_user_type", "用户对问题集操作已存在"),
    DIRECTION_STAGE_DIRECTION_STAGE("direction_stage.direction_stage_direction_enum", "当前方向的对应阶段已存在"),
    LEARNING_MATERIALS_FILE_STAGE_FILE_TYPE("learning_materials_file.learning_materials_stage_file_type", "学习资源对应阶段文件已存在"),
    ERROR("", "未知唯一键或唯一组合键错误");

    public static final String UNIQUE_ERROR = "Duplicate entry";
    private final String key;
    private final String message;

    UniqueErrorEnum(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public static UniqueErrorEnum getUniqueErrorEnumByKey(String key) {
        for (UniqueErrorEnum uniqueErrorEnum : UniqueErrorEnum.values()) {
            if (uniqueErrorEnum.getKey().equals(key)) {
                return uniqueErrorEnum;
            }
        }
        return ERROR;
    }
}
