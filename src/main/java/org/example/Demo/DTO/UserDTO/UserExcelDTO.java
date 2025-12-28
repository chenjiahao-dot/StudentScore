package org.example.Demo.DTO.UserDTO;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.example.Demo.Enummerate.SexEnum;
import org.example.Demo.Enummerate.StatusEnum;

/**
 * Excel导出的用户DTO
 */
@Data
public class UserExcelDTO {
    /**
     * Excel列名：用户ID
     */
    @ExcelProperty("用户ID")
    private Long id;


    /**
     * Excel列名：姓名
     */
    @ExcelProperty("姓名")
    private String name;

    /**
     * Excel列名：用户名
     */
    @ExcelProperty("用户名")
    private String userName;
    /**
     * Excel列名：用户类型
     */
    @ExcelProperty("用户类型")
    private String userTypeEnum;
    /**
     * Excel列名：学号
     */
    @ExcelProperty("学号")
    private String number;
    /**
     * Excel列名：密码
     */
    @ExcelProperty("用户名")
    private String passWord;
    /**
     * Excel列名：手机号
     */
    @ExcelProperty("手机号")
    private String mobile;

    /**
     * Excel列名：邮箱
     */
    @ExcelProperty("邮箱")
    private String mail;

    /**
     * Excel列名：性别
     */
    @ExcelProperty("性别")
    private String sexEnum;

    /**
     * Excel列名：启动状态
     */
    @ExcelProperty("启动状态")
    private String statusEnum;


}