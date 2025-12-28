package org.example.Demo.Controller.UserController;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.Context.BaseContext;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.DTO.UserDTO.LoginRequestUserDTO;
import org.example.Demo.DTO.UserDTO.UpdateUserStatus;
import org.example.Demo.DTO.UserDTO.UserExcelDTO;
import org.example.Demo.DTO.UserDTO.UserRePasswordDTO;
import org.example.Demo.DTO.WarehouseDTO.UserSignInDTO;
import org.example.Demo.Result.Result;
import org.example.Demo.Server.UserService;
import org.example.Demo.entity.Position.ExcelPosition;
import org.example.Demo.entity.Position.Position;
import org.example.Demo.entity.User.User;
import org.example.Demo.mapper.UserManageMapper;
import org.example.Demo.mapper.UserMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "用户相关接口")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserManageMapper userManageMapper;
    /**
     * 用户注册
     * @param userSignInDTO
     * @return
     */
    @PostMapping("/signIn")
    @Operation(summary = "用户注册")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result signIn(@RequestBody UserSignInDTO userSignInDTO) {
        log.info("开始注册用户{}", userSignInDTO.getMail());
        userService.signIn(userSignInDTO);
        log.info("新增用户成功");
        return Result.success("注册成功", null);
    }

    /**
     * 用户登录
     * @param loginRequestUserDTO
     * @return
     */
    @PostMapping("/Login")
    @Operation(summary = "邮箱/手机登录")
    @ApiOperationSupport(author = "陈嘉豪")// 接口文档描述
    public Map<String, String> login(@Validated @RequestBody LoginRequestUserDTO loginRequestUserDTO) {
        // 调用服务层获取token
        String token = userService.login(loginRequestUserDTO);

        // 构建返回结果（包含token）
        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        result.put("message", "登录成功");
        return result;

    }

    /**
     * 修改密码
     * @param userRePasswordDTO
     * @return
     */
    @PutMapping("/rePassword")
    @Operation(summary = "修改密码")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result rePassword(@RequestBody UserRePasswordDTO userRePasswordDTO) {
        log.info("用户{}尝试修改密码", BaseContext.getCurrentId());
        userService.rePassword(userRePasswordDTO);
        log.info("用户{}修改密码成功", BaseContext.getCurrentId());
        return Result.success("修改成功", null);
    }
    /**
     * 查询当前用户ID
     *
     * @param
     * @return
     */
    @PostMapping("/UserId")
    @Operation(summary = "查询用户ID")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<Long> getCurrentUserId(){
        // 从ThreadLocal中获取当前用户ID
        Long userId = BaseContext.getCurrentId();

        // 校验用户ID是否为空（防止异常情况）
        if (userId == null) {
            return Result.error("获取用户ID失败，请重新登录");
        }

        // 返回用户ID
        return Result.success("用户已成功登录", userId);
    }

    /**
     * 查询当前用户姓名
     *
     * @param
     * @return
     */
    @PostMapping("/UserName")
    @Operation(summary = "查询用户姓名")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<String> getCurrentUserName(){
        // 从ThreadLocal中获取当前用户ID
        String userName = BaseContext.getCurrentName();

        // 校验用户ID是否为空（防止异常情况）
        if (userName == null) {
            return Result.error("获取用户姓名失败");
        }

        // 返回用户ID
        return Result.success("用户已成功登录", userName);
    }
    /**
     * 更改用户账号状态
     *
     * @param
     * @return
     */
    @PostMapping("/updateUserStatus")
    @Operation(summary = "更改用户帐号状态")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result updateUserStatus(@RequestBody UpdateUserStatus updateUserStatus) {
        try {
            userService.updateUserStatus(updateUserStatus);
            return Result.success("用户状态修改成功");
        } catch (RuntimeException e) {
            // 捕获业务异常，返回错误信息
            return Result.error(e.getMessage());
        }
    }



        /**
         * 导出所有用户数据到Excel
         * @param response HTTP响应对象，用于输出Excel流
         * @throws Exception 异常抛出（实际项目可自定义全局异常处理）
         */
        @GetMapping("/exportData")
        @Operation(summary = "导出数据")
        @ApiOperationSupport(author = "陈嘉豪")
        public void exportUserExcel(HttpServletResponse response) throws Exception {
            // 1. 配置响应头：告诉浏览器这是Excel文件，需要下载，解决中文文件名乱码
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            String fileName = "用户数据";
            // 编码处理：适配不同浏览器，替换+为%20避免空格问题
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()).replace("+", "%20");
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + encodedFileName + ".xlsx\"; filename*=UTF-8''" + encodedFileName + ".xlsx");

            // 2. 查询数据库：获取所有用户数据（MyBatis-Plus的selectList(null)表示无条件查询）
            List<User> userList = userMapper.selectAllUser();
            // 验证数据：如果无数据，抛出异常，避免生成0KB损坏文件
            if (CollectionUtils.isEmpty(userList)) {
                throw new RuntimeException("暂无用户数据可导出");
            }

            // 3. 转换数据：将数据库实体转换为Excel DTO（解耦，避免暴露数据库字段）
            List<UserExcelDTO> excelDTOList = userList.stream().map(user -> {
                UserExcelDTO dto = new UserExcelDTO();
                dto.setId(user.getId());
                dto.setName(user.getName());
                dto.setUserTypeEnum(user.getUserTypeEnum());
                dto.setNumber(user.getNumber());
                dto.setUserName(user.getUserName());
                dto.setPassWord(user.getPassword());
                dto.setStatusEnum(user.getStatusEnum());
                dto.setSexEnum(user.getSexEnum());
                dto.setMobile(user.getMobile());
                dto.setMail(user.getMail());
                return dto;
            }).collect(Collectors.toList());

            // 4. 写入Excel并输出到浏览器：使用try-with-resources自动关闭流，避免资源泄露
            try (ServletOutputStream outputStream = response.getOutputStream()) {
                EasyExcel.write(outputStream, UserExcelDTO.class)
                        .sheet("用户列表") // Excel工作表名称
                        .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()) // 自动适配列宽（优化体验）
                        .doWrite(excelDTOList); // 写入数据
                outputStream.flush(); // 强制刷写流，确保数据全部输出
            }
        }
    }
