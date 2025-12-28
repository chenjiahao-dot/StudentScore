package org.example.Demo.VO.roleVO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Author: Illya
 * @Date: 2024/3/30 7:28
 */

@Data
public class RoleBasicContentVO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "名称")
    private String name;
    @Schema(description = "中文名")
    private String chineseName;
    @Schema(description = "介绍")
    private String comment;
    @Schema(description = "创建时间")
    private Date createTime;
    @Schema(description = "修改时间")
    private Date updateTime;
    @Schema(description = "创建用户")
    private Long createUser;
    @Schema(description = "修改用户")
    private Long updateUser;
    @Schema(description = "创建用户姓名")
    private String createUserName;
    @Schema(description = "修改用户姓名")
    private String updateUserName;
}
