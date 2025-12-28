package com.example.Util;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.Enummerate.DirectionEnum;
import org.example.Demo.Enummerate.PrimaryRoleEnum;
import org.example.Demo.entity.User.UserData;

import java.util.List;
import java.util.Map;

/**
 * @Author: Illya
 * @Date: 2024/4/22 下午7:41
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginData {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long userId;
    private UserData userData;
    private List<String> operationList;
    private Map<String, List<MenuVO>> menuMap;
    private PrimaryRoleEnum primaryRoleEnum;
    private String name;
    private List<DirectionEnum> directionList;

}
