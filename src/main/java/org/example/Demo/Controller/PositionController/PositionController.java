package org.example.Demo.Controller.PositionController;

import com.example.Context.BaseContext;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.Common.PositionException;
import org.example.Demo.DTO.PositionDTO.AddUserPositionDTO;
import org.example.Demo.DTO.PositionDTO.PositionPageQueryDTO;
import org.example.Demo.DTO.PositionDTO.UpdateUserPositionDTO;
import org.example.Demo.DTO.WarehouseDTO.AddWarehouseDTO;
import org.example.Demo.DTO.WarehouseDTO.UpdateWarehouseDTO;
import org.example.Demo.DTO.WarehouseDTO.WarehousePageQueryDTO;
import org.example.Demo.Result.PageResult;
import org.example.Demo.Result.Result;
import org.example.Demo.Server.PositionService;
import org.example.Demo.UserException.AddWarehouseException;
import org.example.Demo.VO.PositionVO.PositionPageQueryVO;
import org.example.Demo.VO.PositionVO.PositionVO;
import org.example.Demo.VO.Warehouse.WarehouseVO;
import org.example.Demo.entity.Permission.Permission;
import org.example.Demo.entity.Position.Position;
import org.example.Demo.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/position/basics")
@Slf4j
@Tag(name = "岗位管理")
@CrossOrigin
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;
    private final PositionMapper positionMapper;

    /**
     * 用户添加岗位
     * @param addUserPositionDTO
     * @return
     * @throws AddWarehouseException
     */
    @PostMapping("/addUserPosition")
    @Operation(summary = "用户添加岗位")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result addUserPosition(@RequestBody AddUserPositionDTO addUserPositionDTO) throws AddWarehouseException {
            positionService.addUserPosition(addUserPositionDTO);
            return Result.success("身份添加成功");
    }
    /**
     * 分页查询用户岗位信息
     * @param positionPageQueryDTO
     * @return
     */
    @PostMapping("/pageUserPosition")
    @Operation(summary = "分页查询用户岗位信息")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<PositionPageQueryVO>> pageWarehouse(@RequestBody PositionPageQueryDTO positionPageQueryDTO){
        log.info("开始分页查询用户岗位信息");
        try{
            PageResult<PositionPageQueryVO> pageResult= positionService.pagePosition(positionPageQueryDTO);
            return Result.success("查询成功",pageResult);
        }catch (BaseException e){
            return Result.error(e.getMessage());
        }
    }
    /**
     * 对用户岗位信息进行修改
     *
     * @param updateUserPositionDTO
     * @return
     */
    @PostMapping("/updateUserPosition")
    @Operation(summary = "对用户岗位进行修改")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result updateWarehouse(@RequestBody UpdateUserPositionDTO updateUserPositionDTO) {
        positionService.updateUserPosition(updateUserPositionDTO);
        return Result.success("修改成功", updateUserPositionDTO);
    }
    /**
     * 删除仓库
     *
     * @Param id
     * @Return
     */
    @PostMapping("/deleteUserPosition/{id}")
    @Operation(summary = "删除用户岗位")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result deleteWarehouse(@PathVariable @Parameter(description = "要删除的用户岗位的id") Long id) {
        Result result = new Result<>();
        try {
            positionService.deleteUserPosition(id);
            return Result.success("删除成功");
        } catch (Exception e) {
            return Result.error("删除失败，可能是id不存在");
        }
    }
    /**
     * 查询用户岗位详情
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("getUserPositionById/{id}")
    @Operation(summary = "查询用户岗位详情")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PositionVO> getUserPositionById(@PathVariable Long id) throws Exception {
        log.info("正在查询ID:{}的用户岗位详情", id);
        {
            //根据用户id去查找详情信息
            PositionVO positionVO= positionService.getUserPositionById(id);
            return Result.success("查询成功", positionVO);
        }
    }
}
