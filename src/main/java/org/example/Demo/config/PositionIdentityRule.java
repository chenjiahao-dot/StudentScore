package org.example.Demo.config;

import java.util.List;
import com.google.common.collect.Lists;

// 枚举：岗位名称对应的合法身份
public enum PositionIdentityRule {
    // 岗位名称 → 允许的身份列表
    MANAGERIAL_POSITION(Lists.newArrayList("董事会", "秘书")),
    TECHNOLOGICAL_POST(Lists.newArrayList("程序员", "测试工程师"));

    private final List<String> validIdentities;

    PositionIdentityRule(List<String> validIdentities) {
        this.validIdentities = validIdentities;
    }

    // 根据岗位名称获取合法身份列表
    public static List<String> getValidIdentities(String positionName) {
        for (PositionIdentityRule rule : values()) {
            if (rule.name().equals(positionName)) {
                return rule.validIdentities;
            }
        }
        // 岗位名称不存在时抛异常
        throw new RuntimeException("不存在的岗位名称：" + positionName);
    }
}