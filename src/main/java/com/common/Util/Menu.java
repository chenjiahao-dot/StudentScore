package com.common.Util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: Illya
 * @Date: 2024/5/1 上午8:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Menu {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long id;
    private String name;
    private String url;
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long parentId;
    private String comment;
    private Long createUser;
    private Date createTime;
    private String createUserName;
    private Long updateUser;
    private Date updateTime;
    private String updateUserName;
}
