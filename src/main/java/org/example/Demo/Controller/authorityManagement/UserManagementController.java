package org.example.Demo.Controller.authorityManagement;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.DTO.UserDTO.UserPageQueryDTO;
import org.example.Demo.Result.PageResult;
import org.example.Demo.Result.Result;
import org.example.Demo.Server.UserService;
import org.example.Demo.VO.UserVO.UserMessageVO;
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
    @PostMapping("/page")
    @Operation(summary = "分页查询所有用户")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<UserMessageVO>> page(@RequestBody UserPageQueryDTO pageQueryDTO) {
        log.info("开始分页查询所有用户");
        try {
            PageResult<UserMessageVO> pageResult = userService.pageUserMessage(pageQueryDTO);
            return Result.success("查询成功", pageResult);
        } catch (BaseException e) {
            return Result.error(e.getMessage());
        }
    }

}
