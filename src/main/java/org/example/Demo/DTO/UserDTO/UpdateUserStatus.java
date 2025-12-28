package org.example.Demo.DTO.UserDTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Demo.Enummerate.StatusEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserStatus {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "用户ID", required = true)
    private Long id;
    @Schema(description = "用户状态枚举类 0为禁用/1为启用")
    private StatusEnum status;
}
