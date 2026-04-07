package org.example.Demo.VO.User;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.example.Demo.enummerate.SexEnum;
import org.example.Demo.enummerate.UserTypeEnum;

@Data
public class UserExcelVO {
    @ExcelProperty(index = 0)   //姓名
    private String name;

    @ExcelProperty(index = 1)
    private String userName;    // 用户名

    @ExcelProperty(index = 2)
    private String password;    // 密码

    @ExcelProperty(index = 3)   //手机号码
    private Long mobile;

    @ExcelProperty(index = 4)   //邮箱
    private String mail;

    @ExcelProperty(index = 5)   //性别
    private SexEnum sexEnum;

    @ExcelProperty(index = 6)
    private UserTypeEnum userTypeEnum;   // 角色

    @ExcelProperty(index = 7)
    private Long classId;       // 班级ID（学生/老师可用）
}
