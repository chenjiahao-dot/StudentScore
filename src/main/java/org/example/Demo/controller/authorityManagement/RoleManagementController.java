package org.example.Demo.controller.authorityManagement;

import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.DTO.Role.AddRolePermissionDTO;
import org.example.Demo.DTO.Role.RoleIdPermissionDTO;
import org.example.Demo.DTO.Role.RolePageQueryDTO;
import org.example.Demo.VO.Role.RoleBasicContentVO;
import org.example.Demo.VO.Role.PressionVO;
import org.example.Demo.VO.Role.RolesListAllVO;
import org.example.Demo.server.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "权限管理(角色)")
@Slf4j
@RequestMapping("/management/role")
@CrossOrigin
@RequiredArgsConstructor
public class RoleManagementController {
    private final RoleService roleService;

    @GetMapping("/getRoleById/{id}")
    @Operation(summary = "查看角色基本信息")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<RoleBasicContentVO> roleBasicContent(@PathVariable Long id) {
        RoleBasicContentVO role = roleService.getRoleBasicContent(id);
        Result<RoleBasicContentVO> modificationResult = new Result<>();
        return Result.success("查询成功", role);
    }
    @PostMapping("/addPermissions")
    @Operation(summary = "添加角色权限")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result addPermissions(@RequestBody AddRolePermissionDTO addRolePermissionDTO) {
        log.info("开始添加权限{}", addRolePermissionDTO.getRoleId());
        try {
            log.info("添加权限成功");
            return roleService.AddPermissions(addRolePermissionDTO);
        } catch (BaseException e) {
            log.info("权限添加失败");
            return Result.error("添加失败:" + e.getMessage());
        }
    }
    /**
     * 列出所有角色
     *
     * @Param RolePageQueryDTO
     * @Return Result<PageResult < RolesListAllVO>>
     */
    @PostMapping("/listRole")
    @Operation(summary = "列出所有角色")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<RolesListAllVO>> pagerole(@RequestBody RolePageQueryDTO rolePageQueryDTO) {
        log.info("列出所有角色");
        {
            //查询所有角色
            PageResult<RolesListAllVO> pageResult = roleService.listRole(rolePageQueryDTO);
            return Result.success("查询成功", pageResult);
        }
    }
    /**
     * 根据角色ID查询权限
     * @param roleIdPermissionDTO
     * @return
     */
    @PostMapping("/roleId")
    @Operation(summary = "根据角色ID查询权限")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<List<PressionVO>> roleId(@RequestBody RoleIdPermissionDTO roleIdPermissionDTO) {
        List<PressionVO> list = roleService.getPermissionsByRoleId(roleIdPermissionDTO);
        return Result.success("查询成功",list);
    }
}
