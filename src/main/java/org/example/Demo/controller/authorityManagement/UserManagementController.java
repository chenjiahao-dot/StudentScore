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
import org.example.Demo.DTO.User.AddUserRoleDTO;
import org.example.Demo.DTO.User.UserPageQueryDTO;
import org.example.Demo.VO.User.UserVO;
import org.example.Demo.entity.User;
import org.example.Demo.server.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management/user")
@Slf4j
@Tag(name = "权限管理(用户)")
@CrossOrigin
@RequiredArgsConstructor
public class UserManagementController {
    private final UserService userService;

    /**
     * 分页查询所有用户
     * @param pageQueryDTO
     * @return
     */
    @PostMapping("/pageUser")
    @Operation(summary = "分页查询所有用户")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<UserVO>> page(@RequestBody UserPageQueryDTO pageQueryDTO) {
        log.info("开始分页查询所有用户");
        try {
            PageResult<UserVO> pageResult = userService.pageUser(pageQueryDTO);
            return Result.success("查询成功", pageResult);
        } catch (BaseException e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 添加用户角色
     * @param addUserRoleDTO
     * @return
     */
    @PostMapping("/addUserRole")
    @Operation(summary = "添加用户角色")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result addPermissions(@RequestBody AddUserRoleDTO addUserRoleDTO) {
        log.info("开始添加角色{}", addUserRoleDTO.getRoleId());
        try {
            log.info("添加角色成功");
            return userService.AddRole(addUserRoleDTO);
        } catch (BaseException e) {
            log.info("权限角色失败");
            return Result.error("添加失败:" + e.getMessage());
        }
    }
}
