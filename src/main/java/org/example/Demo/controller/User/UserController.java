package org.example.Demo.controller.User;

import com.common.Context.BaseContext;
import com.common.Util.JwtUtil;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.DTO.User.LoginRequestUserDTO;
import org.example.Demo.DTO.User.UserRePasswordDTO;
import org.example.Demo.DTO.User.UserSignInDTO;
import com.common.Result.Result;
import org.example.Demo.enummerate.SexEnum;
import org.example.Demo.enummerate.UserTypeEnum;
import org.example.Demo.server.UserService;
import org.example.Demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "用户相关接口")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    /**
     * Redis
     */
    private final RedisTemplate<String, String> redisTemplate;
    @Value("${file.upload-path}")
    private String uploadPath;

    @Value("${file.access-path}")
    private String accessPath;


    private final JwtUtil jwtUtil;

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 用户注册
     *
     * @param
     * @return
     */
    @PostMapping(value = "/signIn", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "用户注册")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<?> signIn(
            @RequestParam("name") String name,
            @RequestParam("userName") String userName,
            @RequestParam("userTypeEnum") String userTypeEnum,
            @RequestParam("sexEnum") String sexEnum,
            @RequestParam("mail") String mail,
            @RequestParam("mobile") String mobile,
            @RequestParam("password") String password,
            @RequestParam("classId") Long classId,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws Exception {
        UserSignInDTO dto = new UserSignInDTO();
        dto.setName(name);
        dto.setUserName(userName);
        dto.setUserTypeEnum(UserTypeEnum.valueOf(userTypeEnum));
        dto.setSexEnum(SexEnum.valueOf(sexEnum));
        dto.setMail(mail);
        dto.setMobile(mobile);
        dto.setPassword(password);
        dto.setClassId(classId);

        userService.signIn(dto, file);
        return Result.success("注册成功");
    }

    /**
     * 用户登录
     *
     * @param loginRequestUserDTO
     * @return
     */
    @PostMapping("/Login")
    @Operation(summary = "邮箱/手机登录")
    @ApiOperationSupport(author = "陈嘉豪")
    public Map<String, String> login(@Validated @RequestBody LoginRequestUserDTO loginRequestUserDTO) {
        // 调用服务层获取token
        String token = userService.login(loginRequestUserDTO);
        // 构建返回结果（包含token）
        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        result.put("message", "登录成功");
        return result;

    }

    @PostMapping("/logout")
    @Operation(summary = "退出登录")
    @ApiOperationSupport(author = "陈嘉豪")
    public void logout(String token) throws Exception {
        Long userId = jwtUtil.parseToken(token);
        if (userId == null) {
            throw new RuntimeException("token无效");
        }

        // 2. 删除Redis中的登录数据
        stringRedisTemplate.delete("login:token:" + userId);
        stringRedisTemplate.delete("login:token:" + token);

        // 3. 清除线程本地变量
        BaseContext.removeCurrentId();
    }


    /**
     * 修改密码
     *
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
    public Result<Long> getCurrentUserId() {
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
     * 判断当前用户是否为管理员
     *
     * @param
     * @return
     */
    @PostMapping("/isAdmin")
    @Operation(summary = "判断当前用户是否为管理员")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<Boolean> isCurrentUserAdmin() {
        // 从ThreadLocal中获取当前用户主角色
        UserTypeEnum UserEnum = BaseContext.getCurrentPrimaryUserEnum();

        // 校验角色是否为空（防止异常情况）
        if (UserEnum == null) {
            return Result.error("获取用户角色失败");
        }

        // 判断是否为管理员角色
        boolean isAdmin = (UserEnum == UserTypeEnum.ADMIN);

        // 返回判断结果
        return Result.success("角色判断成功", isAdmin);
    }


    /**
     * 单独的图片上传接口
     */
    @PostMapping("/upload")
    @Operation(summary = "单独文件上传")
    @ApiOperationSupport(author = "陈嘉豪")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        // 1. 获取后缀
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 2. 生成唯一文件名
        String fileName = UUID.randomUUID() + suffix;

        // 3. 创建文件夹
        File folder = new File(uploadPath);
        if (!folder.exists()) folder.mkdirs();

        // 4. 保存图片
        file.transferTo(new File(uploadPath, fileName));

        // 5. 返回文件名（存到数据库的就是这个）
        return fileName;
    }

}



