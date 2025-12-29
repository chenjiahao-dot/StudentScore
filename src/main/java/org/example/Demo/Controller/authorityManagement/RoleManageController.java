package org.example.Demo.Controller.authorityManagement;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.DTO.PositionDTO.AddUserPositionDTO;
import org.example.Demo.DTO.RoleDTO.*;
import org.example.Demo.DTO.WarehouseDTO.UpdateWarehouseDTO;
import org.example.Demo.Result.PageResult;
import org.example.Demo.Result.Result;
import org.example.Demo.Server.RoleService;
import org.example.Demo.UserException.AddWarehouseException;
import org.example.Demo.VO.roleVO.RoleBasicContentVO;
import org.example.Demo.VO.roleVO.RolesListAllVO;
import org.example.Demo.VO.roleVO.UserRoleVO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management/role")
@Slf4j
@Tag(name = "权限管理(角色)")
@CrossOrigin
@RequiredArgsConstructor
public class RoleManageController {

    private final RoleService roleService;

    /**
     * 查询角色基本信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getRoleById/{id}")
    @Operation(summary = "查询角色基本信息")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<RoleBasicContentVO> roleBasicContent(@PathVariable Long id) {
        RoleBasicContentVO role = roleService.getRoleBasicContent(id);
        return Result.success("查询成功", role);
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
     * 分页查询所有主角色
     *
     * @Param RolePageQueryDTO
     * @Return Result<PageResult < RolesListAllVO>>
     */
    @PostMapping("/pagePrimaRole")
    @Operation(summary = "分页查询所有主角色")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<RolesListAllVO>> pagePrimaRole(@RequestBody RolePageQueryDTO pageQueryDTO) {
        log.info("开始分页查询所有主角色");
        {
            //查询所有主角色
            PageResult<RolesListAllVO> pageResult = roleService.pagePrimaRole(pageQueryDTO);
            return Result.success("查询成功", pageResult);
        }
    }
    /**
     * 分页查询所有次角色
     *
     * @param pageQueryDTO
     * @return Result<PageResult < RolesListAllVO>>
     */
    @PostMapping("/pageSecondaryRole")
    @Operation(summary = "分页查询所有次角色")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<RolesListAllVO>> pageSecondaryRole(@RequestBody RolePageQueryDTO pageQueryDTO) {
        log.info("开始分页查询所有次角色");
        PageResult<RolesListAllVO> pageResult = roleService.pageSecondaryRole(pageQueryDTO);
        return Result.success("查询成功", pageResult);
    }
    /**
     * 新建次角色
     *
     * @param roleSignInDTO
     * @return Result<Long>
     */
    @PostMapping("/addRole")
    @Operation(summary = "新建角色")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<Long> addRole(@RequestBody RoleSignInDTO roleSignInDTO) {
        log.info("开始注册角色{}", roleSignInDTO.getChineseName());
        Long roleID = roleService.addRole(roleSignInDTO);
        log.info("新增角色成功");
        return Result.success("新增成功", roleID);
    }
    /**
     * 点击后可以删除角色
     *
     * @param deleteRoleDTO
     * @return
     */
    @PostMapping("/deleteRole")
    @Operation(summary = "点击后可以删除角色")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result deleteRole(@RequestBody DeleteRoleDTO deleteRoleDTO) {
        Result result = new Result<>();
        try {
            roleService.deleteRole(deleteRoleDTO);
            return Result.success("删除成功", null);
        } catch (Exception e) {
            return Result.error("无法删除主角色或者id不存在");
        }
    }
    /**
     * 用户添加角色
     * @param addUserRoleDTO
     * @return
     * @throws AddWarehouseException
     */
    @PostMapping("/addUserRole")
    @Operation(summary = "用户添加角色")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result addUserPosition(@RequestBody AddUserRoleDTO addUserRoleDTO) throws AddWarehouseException {
        roleService.addUserRole(addUserRoleDTO);
        return Result.success("身份添加成功");
    }
    /**
     * 删除用户所拥有的角色
     *
     * @Param id
     * @Return
     */
    @PostMapping("/deleteUserRole/{id}")
    @Operation(summary = "删除用户所拥有的角色")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result deleteUserRole(@PathVariable @Parameter(description = "要删除的用户角色的id") Long id) {
        try {
            roleService.deleteUserRole(id);
            return Result.success("删除成功");
        } catch (Exception e) {
            return Result.error("删除失败，可能是用户无角色");
        }
    }
    /**
     * 对用户所拥有的角色进行修改
     *
     * @param updateUserRoleDTO
     * @return
     */
    @PostMapping("/updateUserRole")
    @Operation(summary = "对用户所拥有的角色进行修改")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result updateWarehouse(@RequestBody UpdateUserRoleDTO updateUserRoleDTO) {
        roleService.updateUserRole(updateUserRoleDTO);
        return Result.success("修改成功", updateUserRoleDTO);
    }
    /**
     * 列出所有用户角色信息
     *
     * @Param RolePageQueryDTO
     * @Return Result<PageResult < RolesListAllVO>>
     */
    @PostMapping("/listUserRole")
    @Operation(summary = "列出所有用户角色")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<UserRoleVO>> pageUserRole(@RequestBody UserRolePageQueryDTO userRolePageQueryDTO) {
        log.info("列出所有用户角色");
        {
            //查询所有角色
            PageResult<UserRoleVO> pageResult = roleService.listUserRole(userRolePageQueryDTO);
            return Result.success("查询成功", pageResult);
        }
    }
}
