package org.example.Demo.entity;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.VO.Menu.MenuVO;

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
    private String name;

}
