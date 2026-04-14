package org.example.Demo.controller.authorityManagement;

import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.DTO.Permission.PermissionPageQueryDTO;
import org.example.Demo.DTO.Permission.QueryRolePermissionDTO;
import org.example.Demo.VO.Permission.PermissionVO;
import org.example.Demo.entity.Permission;
import org.example.Demo.server.PermissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/permission")
@Slf4j
@Tag(name = "权限管理(权限)")
@CrossOrigin
@RequiredArgsConstructor
public class PermissionManagementController {
    private final PermissionService permissionService;

    @PostMapping("/allPermission")
    @Operation(summary = "所有权限")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<List<PermissionVO>> listPermission() {
        List<PermissionVO> permission = permissionService.allPermission();
        return Result.success("查询成功", permission);
    }

    @PostMapping("/pagePermission")
    @Operation(summary = "分页查询所有权限")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<PermissionVO>> pagePermission(@RequestBody PermissionPageQueryDTO pageQueryDTO) {
        log.info("开始分页查询所有权限");
        try {
            PageResult<PermissionVO> pageResult = permissionService.pagePermission(pageQueryDTO);
            return Result.success("查询成功", pageResult);
        } catch (BaseException e) {
            return Result.error(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询权限")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PermissionVO> getById(@PathVariable Long id) {
        log.info("正在查询ID:{}的权限信息", id);
        try {
            PermissionVO user = permissionService.getByID(id);
            log.info("查询成功");
            return Result.success("查询成功",user);
        } catch (BaseException e) {
            log.info("未查询到ID:{}的权限", id);
            return Result.error("未查询到权限");
        }
    }
    @PostMapping("/queryRolePermission")
    @Operation(summary = "查询角色所拥有的权限")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<List<String>> queryRolePermission(@RequestBody QueryRolePermissionDTO queryRolePermissionDTO) {
        try {
            List<String> rolePermissionVO = permissionService.queryRolePermission(queryRolePermissionDTO);
            return Result.success("查询成功",rolePermissionVO);
        } catch (BaseException e) {
            return Result.error("权限为空");
        }
    }
}
