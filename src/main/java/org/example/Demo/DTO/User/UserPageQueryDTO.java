package org.example.Demo.DTO.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserPageQueryDTO implements Serializable {
    @Schema(description = "用户姓名")
    //用户姓名
    private String name;

    @Schema(description = "页码", defaultValue = "1")
    //页码
    private Integer page;
    @Schema(description = "每页显示记录数", defaultValue = "10")
    //每页显示记录数
    private Integer pageSize;

}