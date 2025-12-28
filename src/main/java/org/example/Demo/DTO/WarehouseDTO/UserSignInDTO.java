package org.example.Demo.DTO.WarehouseDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.Enummerate.PositionEnum;
import org.example.Demo.Enummerate.PositionIdentityEnum;
import org.example.Demo.Enummerate.SexEnum;
import org.example.Demo.OrderTypeEnum.UserTypeEnum;

/**
 * @author Illya
 */
@Data
public class UserSignInDTO {
    @Schema(description = "姓名")
    private String name;
    @Schema(description = "学号")
    private String number;
    @Schema(description = "电话号码")
    private String mobile;
    @Schema(description = "邮箱 唯一")
    private String mail;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "用户性别")
    private SexEnum sexEnum;
    @Schema(description = "用户类型")
    private UserTypeEnum userTypeEnum;
    @Schema(description = "职位")
    private PositionEnum positionEnum;
    @Schema(description = "职位身份")
    private PositionIdentityEnum positionIdentityEnum;
}
