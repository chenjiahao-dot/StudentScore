package com.common.Util;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.entity.userData;

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
    private userData userData;
    private List<String> operationList;
    private Map<String, List<MenuVO>> menuMap;
    private String name;

}
