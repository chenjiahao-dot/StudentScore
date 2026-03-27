package org.example.Demo.DTO.User;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserLoginDTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "邮箱(手机号输了可以不输)")
    private String mail;
    @Schema(description = "密码")
    private String password;
}
