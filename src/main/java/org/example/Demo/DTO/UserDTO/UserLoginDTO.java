package org.example.Demo.DTO.UserDTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.Demo.Enummerate.WarehouseTypeEnum;
@Data
public class UserLoginDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "邮箱(手机号输了可以不输)")
    private String mail;
    @Schema(description = "密码")
    private String password;
}
