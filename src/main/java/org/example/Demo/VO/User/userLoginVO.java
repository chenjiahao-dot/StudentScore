package org.example.Demo.VO.User;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Illya
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//返回前端用户登录数据
public class userLoginVO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "邮箱")
    private String mail;
    @Schema(description = "手机号")
    private String mobile;
    @Schema(description = "token")
    private String token;
    @Schema(description = "姓名")
    private String name;
}
