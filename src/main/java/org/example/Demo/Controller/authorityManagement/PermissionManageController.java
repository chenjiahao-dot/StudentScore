package org.example.Demo.Controller.authorityManagement;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Result.Result;
import org.example.Demo.Server.PermissionService;
import org.example.Demo.entity.Permission.Permission;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/management/permission")
@Slf4j
@Tag(name = "权限管理(权限)")
@CrossOrigin
@RequiredArgsConstructor
public class PermissionManageController {

    private final PermissionService permissionService;

    /**
     * 查询所有权限
     * @return
     */
    @PostMapping("/allPermission")
    @Operation(summary = "所有权限")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<List<Permission>> listPermission() {
        List<Permission> permission = permissionService.allPermission();
        return Result.success("查询成功", permission);
    }
}
