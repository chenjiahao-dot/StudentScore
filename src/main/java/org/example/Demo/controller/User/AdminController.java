package org.example.Demo.controller.User;

import com.common.Result.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.entity.User;
import org.example.Demo.server.UserServer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
@Slf4j
@Tag(name = "管理员相关接口")
@CrossOrigin
@RequiredArgsConstructor
public class AdminController {
    private final UserServer userServer;
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询用户")
    @ApiOperationSupport(author = "汪润杰")
    public Result<User> getById(@PathVariable Long id) {
        log.info("正在查询ID:{}的用户信息", id);
        try {
            User user = userServer.getByID(id);
            log.info("查询成功");
            return Result.success(user);
        } catch (BaseException e) {
            log.info("未查询到ID:{}的用户", id);
            return Result.error("未查询到用户");
        }
    }
}
