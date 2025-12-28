package org.example.Demo.Controller.WarehouseController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.DTO.WarehouseDTO.*;
import org.example.Demo.Result.PageResult;
import org.example.Demo.Result.Result;
import org.example.Demo.Server.WarehouseService;
import org.example.Demo.UserException.AddWarehouseException;
import org.example.Demo.VO.Warehouse.WarehouseGoodsVO;
import org.example.Demo.VO.Warehouse.WarehouseUserManageVO;
import org.example.Demo.VO.Warehouse.WarehouseUserRecordingVO;
import org.example.Demo.VO.Warehouse.WarehouseVO;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;


@CrossOrigin()
@RestController
@RequestMapping("/warehouse/basics/")
@Slf4j
@Tag(name = "仓库管理")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;
    /**
     * 查询仓库详情
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("getWarehouseById/{id}")
    @Operation(summary = "查询仓库详情")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<WarehouseVO> getWarehouseById(@PathVariable Long id) throws Exception {
        log.info("正在查询ID:{}的作品详情", id);
        {
            //根据用户id去查找详情信息
           WarehouseVO warehouseVO= warehouseService.getWarehouseById(id);
           return Result.success("查询成功", warehouseVO);
        }
    }
    /**
     * 新增仓库
     * @param addWarehouseDTO
     * @return
     */
    @PostMapping("/addWarehouse")
    @Operation(summary = "新增仓库")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result addWarehouse(@RequestBody AddWarehouseDTO addWarehouseDTO){
        if (addWarehouseDTO.getName()!=null && addWarehouseDTO.getAddress()!=null){
            warehouseService.addWarehouse(addWarehouseDTO);
            return Result.success("新增成功",addWarehouseDTO);
        }else{
            throw new AddWarehouseException();
        }
    }
    /**
     * 对仓库进行修改
     *
     * @param updateWarehouseDTO
     * @return
     */
    @PostMapping("/updateWarehouse")
    @Operation(summary = "对仓库进行修改")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result updateWarehouse(@RequestBody UpdateWarehouseDTO updateWarehouseDTO) {
        warehouseService.updateWarehouse(updateWarehouseDTO);
        return Result.success("修改成功", updateWarehouseDTO);
    }
    /**
     * 删除仓库
     *
     * @Param id
     * @Return
     */
    @PostMapping("/deleteWarehouse/{id}")
    @Operation(summary = "删除仓库")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result deleteWarehouse(@PathVariable @Parameter(description = "要删除的仓库的id") Long id) {
        Result result = new Result<>();
        try {
            warehouseService.deleteWarehouse(id);
            return Result.success("删除成功");
        } catch (Exception e) {
            return Result.error("删除失败，可能是id不存在");
        }
    }
    /**
     * 分页查询所有仓库
     * @param warehousePageQueryDTO
     * @return
     */
    @PostMapping("/PageWarehouse")
    @Operation(summary = "分页查询所有仓库")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<WarehouseVO>> pageWarehouse(@RequestBody WarehousePageQueryDTO warehousePageQueryDTO){
        log.info("开始分页查询所有仓库");
        try{
            PageResult<WarehouseVO> pageResult= warehouseService.pageWarehouse(warehousePageQueryDTO);
            return Result.success("查询成功",pageResult);
        }catch (BaseException e){
            return Result.error(e.getMessage());
        }
    }
    /**
     * 新增物品仓库
     * @param addOtherWarehouseDTO
     * @return
     */
    @PostMapping("/addWarehouseGoods")
    @Operation(summary = "新增物品仓库")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result addWarehouseGoods(@RequestBody AddOtherWarehouseDTO addOtherWarehouseDTO){
        if (addOtherWarehouseDTO.getName()!=null){
            warehouseService.addWarehouseGoods(addOtherWarehouseDTO);
            return Result.success("新增成功",addOtherWarehouseDTO);
        }else{
            throw new AddWarehouseException();
        }
    }
    /**
     * 删除物品仓库
     *
     * @Param id
     * @Return
     */
    @PostMapping("/deleteWarehouseGoods/{id}")
    @Operation(summary = "删除物品仓库")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result deleteWarehouseGoods(@PathVariable @Parameter(description = "要删除的仓库的id") Long id) {
        Result result = new Result<>();
        try {
            warehouseService.deleteWarehouseGoods(id);
            return Result.success("删除成功");
        } catch (Exception e) {
            return Result.error("删除失败，可能是id不存在");
        }
    }
    /**
     * 查询物品仓库详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("getWarehouseGoodsById/{id}")
    @Operation(summary = "查询物品仓库详情")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<WarehouseGoodsVO> getWarehouseGoodsById(@PathVariable Long id) throws Exception {
        log.info("正在查询ID:{}的作品详情", id);
        {
            //根据用户id去查找详情信息
            WarehouseGoodsVO warehouseGoodsVO= warehouseService.getWarehouseGoodsById(id);
            return Result.success("查询成功", warehouseGoodsVO);
        }
    }

    /**
     * 入库物品
     * @param addUserWarehouseDTO
     * @return
     */
    @PostMapping("/putWarehouse")
    @Operation(summary = "入库")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result putWarehouse(@RequestBody PutWarehouseDTO addUserWarehouseDTO){
        warehouseService.putWarehouse(addUserWarehouseDTO);
        return Result.success("新增成功",addUserWarehouseDTO);
    }
    /**
     * 入库物品
     * @param outWarehouseDTO
     * @return
     */
    @PostMapping("/outWarehouse")
    @Operation(summary = "出库")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result outWarehouse(@RequestBody OutWarehouseDTO outWarehouseDTO){
        warehouseService.outWarehouse(outWarehouseDTO);
        return Result.success("新增成功",outWarehouseDTO);
    }
    /**
     * 处理仓库审批
     * @param handleWarehouseManageApprovalDTO
     * @return
     */
    @PostMapping("/HandleWarehouseManageApproval")
    @Operation(summary = "处理仓库物品审批")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result HandleWarehouseManageApproval(@RequestBody HandleWarehouseManageApprovalDTO handleWarehouseManageApprovalDTO) {
        warehouseService.updateWarehouseManageApproval(handleWarehouseManageApprovalDTO);
        return Result.success("修改成功", handleWarehouseManageApprovalDTO);
    }
    /**
     * 处理仓库物品未通过审批
     * @param handleWarehouseManageApprovalDTO
     * @return
     */
    @PostMapping("/HandleWarehouseManageNoApproval")
    @Operation(summary = "处理仓库物品未通过审批")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result HandleWarehouseManageNoApproval(@RequestBody HandleWarehouseManageApprovalDTO handleWarehouseManageApprovalDTO) {
        warehouseService.updateWarehouseManageNoApproval(handleWarehouseManageApprovalDTO);
        return Result.success("修改成功", handleWarehouseManageApprovalDTO);
    }
    /**
     * 批量删除审批
     * @param
     * @return
     */
    @PostMapping("/deleteWarehouseGoodsUserApproval")
    @Operation(summary = "批量删除审批")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result deleteWarehouse(@RequestBody DeleteForeachWarehouseDTO deleteForeachWarehouseDTO) {
        Result result = new Result<>();
        warehouseService.deleteForeachWarehouseGoodsUser(deleteForeachWarehouseDTO);
        return Result.success("删除成功");
    }
    /**
     * 领取物品
     */
    @PostMapping("/ReceiveWarehouseManage")
    @Operation(summary = "领取物品")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result ReceiveWarehouseManage(@RequestBody ReceiveWarehouseManageDTO receiveWarehouseManageDTO){
        warehouseService.ReceiveWarehouseManage(receiveWarehouseManageDTO);
        return Result.success("领取成功", receiveWarehouseManageDTO);
    }
    /**
     * 借用物品
     */
    @PostMapping("/LentWarehouseManage")
    @Operation(summary = "借用物品")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result LentWarehouseManage(@RequestBody LentWarehouseManageDTO lentWarehouseManageDTO){
        warehouseService.LentWarehouseManage(lentWarehouseManageDTO);
        return Result.success("借用成功",lentWarehouseManageDTO);
    }

    /**
     * 物品归还
     * @param articleReturnDTO
     * @return
     */
    @PostMapping("/articleReturn")
    @Operation(summary = "物品归还")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result articleReturn(@RequestBody ArticleReturnDTO articleReturnDTO) {
        warehouseService.articleReturn(articleReturnDTO);
        return Result.success("归还成功", null);
    }
    /**
     * 分页查询物品管理
     *
     * @param warehouseManageDTO
     * @return
     */
    @PostMapping("/pageWarehouseManage")
    @Operation(summary = "分页查询物品管理")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<WarehouseUserManageVO>> pageWarehouseManage(@RequestBody WarehouseManageDTO warehouseManageDTO) {
        log.info("开始分页查询物品管理");
        PageResult<WarehouseUserManageVO> pageResult = warehouseService.pageWarehouseManage(warehouseManageDTO);
        return Result.success("查询成功", pageResult);
    }
    /**
     * 查询所有用户申请记录
     *
     * @param applicationRecordPageQueryDTO
     * @return.
     */
    @PostMapping("/pageUserRecording")
    @Operation(summary = "分页查询所有申请记录")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<WarehouseUserRecordingVO>> pageUserRecording(@RequestBody ApplicationRecordPageQueryDTO applicationRecordPageQueryDTO) {
        log.info("开始查询所有用户申请记录");
        PageResult<WarehouseUserRecordingVO> pageResult = warehouseService.pageUserRecording(applicationRecordPageQueryDTO);
        return Result.success("查询成功", pageResult);
    }

    /**
     * 批量删除物品
     * @param deleteForeachWarehouseDTO
     * @return
     */
    @DeleteMapping("/deleteForeachWarehouse")
    @Operation(summary = "批量删除物品")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result deleteWarehouseGoods(@RequestBody DeleteForeachWarehouseDTO deleteForeachWarehouseDTO) {
        Result result = new Result<>();
        warehouseService.deleteForeachWarehouseGoods(deleteForeachWarehouseDTO);
        return Result.success("删除成功");
    }
}
