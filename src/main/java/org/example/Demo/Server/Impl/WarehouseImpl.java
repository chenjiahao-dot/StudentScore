package org.example.Demo.Server.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.Context.BaseContext;
import com.example.Util.PublicUtil;
import com.example.pageQuery.MyApplicationRecordPageQuery;
import com.example.pageQuery.WarehouseGoodsPageQuery;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.Common.ManageApprovalException;
import org.example.Demo.DTO.WarehouseDTO.*;
import org.example.Demo.Enummerate.DirectionEnum;
import org.example.Demo.Enummerate.WarehouseGoodsReturnStatusEnum;
import org.example.Demo.Enummerate.WarehouseOperationStatusEnum;
import org.example.Demo.Enummerate.WarehouseOperationTypeEnum;
import org.example.Demo.VO.Warehouse.*;
import org.example.Demo.mapper.WarehouseMapper;
import org.example.Demo.Result.PageResult;
import org.example.Demo.Result.Result;
import org.example.Demo.Server.WarehouseService;
import org.example.Demo.entity.Warehouse.Warehouse;
import org.example.Demo.entity.Warehouse.WarehouseGoods;
import org.example.Demo.entity.Warehouse.WarehouseGoodsUser;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WarehouseImpl implements WarehouseService {
    private final WarehouseMapper warehouseMapper;

    /**
     * 查询仓库详情
     * @param id
     * @return
     */
    @Override
    public WarehouseVO getWarehouseById(Long id) {
        WarehouseVO warehouseVO = warehouseMapper.selectWarehouseById(id);
        if (warehouseMapper.countWarehouse(id) == 0) {
            throw new BaseException("仓库详情不存在");
        }
        return warehouseVO;
    }

    /**
     * 添加仓库
     *
     * @param addWarehouseDTO
     * @return
     */
    @Override
    public void addWarehouse(AddWarehouseDTO addWarehouseDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setCreateTime();
        warehouse.setUpdateTime();
        BeanUtils.copyProperties(addWarehouseDTO, warehouse);
        warehouseMapper.insertWarehouse(warehouse);
    }

    /**
     * 修改仓库
     *
     * @param updateWarehouseDTO
     */
    @Override
    public void updateWarehouse(UpdateWarehouseDTO updateWarehouseDTO) {
        Warehouse warehouse = new Warehouse();
        warehouse.setCreateTime();
        warehouse.setUpdateTime();
        BeanUtils.copyProperties(updateWarehouseDTO, warehouse);
        warehouseMapper.updateWarehouse(warehouse);
    }

    /**
     * 删除仓库
     *
     * @param id
     */
    @Override
    public Result deleteWarehouse(Long id) {
        Result result = new Result<>();
        if (warehouseMapper.selectById(id)) {
            warehouseMapper.deleteById(id);
            result.setMsg("删除成功");
            return result;
        }
        return result;
    }

    /**
     * 分页查询所有仓库
     *
     * @param warehousePageQueryDTO
     * @return
     */
    @Override
    public PageResult<WarehouseVO> pageWarehouse(WarehousePageQueryDTO warehousePageQueryDTO) {
        Warehouse warehouse = new Warehouse();
        BeanUtils.copyProperties(warehousePageQueryDTO, warehouse);
        // PublicUtil.getPageHelper(new Warehouse (), warehousePageQueryDTO.getSortField(), warehousePageQueryDTO.getOrderType());
        //PageHelper.startPage(warehousePageQueryDTO.getPage(), warehousePageQueryDTO.getPageSize());
        Page<WarehouseVO> pageReq = new Page<>(warehousePageQueryDTO.getPage(), warehousePageQueryDTO.getPageSize());

        IPage<WarehouseVO> page = warehouseMapper.pageWarehouse2(pageReq, warehousePageQueryDTO);
        long total = page.getTotal();
        List<WarehouseVO> roles = page.getRecords();
        return new PageResult<>(total, roles);
    }

    /**
     * 新增物品仓库
     *
     * @param addOtherWarehouseDTO
     */
    @Override
    public void addWarehouseGoods(AddOtherWarehouseDTO addOtherWarehouseDTO) {
        WarehouseGoods warehouseGoods = new WarehouseGoods();
        warehouseGoods.setWarehouseId(addOtherWarehouseDTO.getWarehouseId());
        warehouseGoods.setCreateTime();
        warehouseGoods.setUpdateTime();
        BeanUtils.copyProperties(addOtherWarehouseDTO, warehouseGoods);
        warehouseMapper.insertWarehouseGoods(warehouseGoods);
    }

    /**
     * 删除物品仓库
     * @param id
     * @return
     */
    @Override
    public Result deleteWarehouseGoods(Long id) {
        Result result = new Result<>();
        if (warehouseMapper.selectWarehouseGoodsById(id)) {
            warehouseMapper.deleteWarehouseGoodsById(id);
            result.setMsg("删除成功");
            return result;
        }
        return result;
    }

    /**
     * 查询仓库物品详情
     * @param id
     * @return
     */
    @Override
    public WarehouseGoodsVO getWarehouseGoodsById(Long id) {
        WarehouseGoodsVO warehouseGoodsVO = warehouseMapper.selectWarehouseGoods(id);
        if (warehouseMapper.countWarehouseGoods(id) == 0) {
            throw new BaseException("仓库详情不存在");
        }
        return warehouseGoodsVO;
    }

    /**
     * 入库
     * @param addUserWarehouseDTO
     */
    @Override
    public void putWarehouse(PutWarehouseDTO addUserWarehouseDTO) {
        WarehouseGoodsUser warehouseGoodsUser = new WarehouseGoodsUser();
        warehouseGoodsUser.setId(IdWorker.getId());
        warehouseGoodsUser.setUserId(BaseContext.getCurrentId());
        warehouseGoodsUser.setWarehouseGoodsId(addUserWarehouseDTO.getWarehouseGoodsId());
        warehouseGoodsUser.setOperationType(WarehouseOperationTypeEnum.ENTER);
        warehouseGoodsUser.setQuantity(addUserWarehouseDTO.getQuantity());
        warehouseGoodsUser.setOperationStatus(WarehouseOperationStatusEnum.WAIT);
        warehouseGoodsUser.setReturnStatus(WarehouseGoodsReturnStatusEnum.NO_NEED);
        warehouseGoodsUser.setUse(addUserWarehouseDTO.getUse());
        warehouseGoodsUser.setCreateTime();
        warehouseGoodsUser.setUpdateTime();
        warehouseGoodsUser.setReturnTime();
        warehouseMapper.insertPutWarehouse(warehouseGoodsUser);
    }

    /**
     * 出库
     * @param outWarehouseDTO
     */
    @Override
    public void outWarehouse(OutWarehouseDTO outWarehouseDTO) {
        WarehouseGoodsUser warehouseGoodsUser = new WarehouseGoodsUser();
        warehouseGoodsUser.setId(IdWorker.getId());
        warehouseGoodsUser.setUserId(BaseContext.getCurrentId());
        warehouseGoodsUser.setWarehouseGoodsId(outWarehouseDTO.getWarehouseGoodsId());
        warehouseGoodsUser.setOperationType(WarehouseOperationTypeEnum.OUT);
        warehouseGoodsUser.setQuantity(outWarehouseDTO.getQuantity());
        warehouseGoodsUser.setOperationStatus(WarehouseOperationStatusEnum.WAIT);
        warehouseGoodsUser.setReturnStatus(WarehouseGoodsReturnStatusEnum.NO_NEED);
        warehouseGoodsUser.setUse(outWarehouseDTO.getUse());
        warehouseGoodsUser.setCreateTime();
        warehouseGoodsUser.setUpdateTime();
        warehouseGoodsUser.setReturnTime();
        warehouseMapper.insertOutWarehouse(warehouseGoodsUser);
    }

    /**
     * 处理审批
     * @param handleWarehouseManageApprovalDTO
     */
    @Override
    public void updateWarehouseManageApproval(HandleWarehouseManageApprovalDTO handleWarehouseManageApprovalDTO) {
        WarehouseGoodsUser warehouseGoodsUser=warehouseMapper.selectStatusById(handleWarehouseManageApprovalDTO.getId());
        WarehouseOperationStatusEnum status=warehouseGoodsUser.getOperationStatus();
        if (status==WarehouseOperationStatusEnum.WAIT) {
            BeanUtils.copyProperties(handleWarehouseManageApprovalDTO, warehouseGoodsUser);
            warehouseMapper.updateWarehouseManageApproval(warehouseGoodsUser);
        }
        if (status==WarehouseOperationStatusEnum.START) {
            throw new ManageApprovalException("已审批，无需再次审批");
        }
        if (status==WarehouseOperationStatusEnum.STOP){
            throw new ManageApprovalException("请求已被驳回，无法审批");
        }
    }

    /**
     * 处理仓库物品未通过审批
     * @param handleWarehouseManageApprovalDTO
     */
    @Override
    public void updateWarehouseManageNoApproval(HandleWarehouseManageApprovalDTO handleWarehouseManageApprovalDTO) {
        WarehouseGoodsUser warehouseGoodsUser=warehouseMapper.selectStatusById(handleWarehouseManageApprovalDTO.getId());
        WarehouseOperationStatusEnum status=warehouseGoodsUser.getOperationStatus();
        if (status==WarehouseOperationStatusEnum.WAIT) {
            BeanUtils.copyProperties(handleWarehouseManageApprovalDTO, warehouseGoodsUser);
            warehouseMapper.updateWarehouseManageNoApproval(warehouseGoodsUser);
        }
        if (status==WarehouseOperationStatusEnum.START) {
            throw new ManageApprovalException("已审批，无法进行驳回处理");
        }
        if (status==WarehouseOperationStatusEnum.STOP){
            throw new ManageApprovalException("请求已被驳回，无需再次驳回");
        }
    }

    /**
     * 批量删除审批
     * @param deleteForeachWarehouseDTO
     * @return
     */
    @Override
    public Result deleteForeachWarehouseGoodsUser(DeleteForeachWarehouseDTO deleteForeachWarehouseDTO) {
        if (warehouseMapper.selectWarehouseGoodsUserById()==null){
            throw new BaseException("该审批已经被删除或已经被审批无法被删除");
        }
        Result result = new Result<>();
        warehouseMapper.deleteForeachWarehouse(deleteForeachWarehouseDTO);
        result.setMsg("删除成功");
        return result;
    }

    /**
     * 领取物品
     * @param receiveWarehouseManageDTO
     */
    @Override
    public void ReceiveWarehouseManage(ReceiveWarehouseManageDTO receiveWarehouseManageDTO) {
        if (warehouseMapper.selectReceoveLoanApplicationWarehouseType(receiveWarehouseManageDTO.getWarehouseGoodsId()) == 0) {
            throw new BaseException("不是领取仓库");
        }
        if (receiveWarehouseManageDTO.getUse() == null) {
            throw new BaseException("用途不能为null");
        }
        if (receiveWarehouseManageDTO.getQuantity() == null) {
            throw new BaseException("数量不能为null");
        }
        WarehouseGoodsUser warehouseGoodsUser = new WarehouseGoodsUser();
        warehouseGoodsUser.setId(IdWorker.getId());
        warehouseGoodsUser.setUserId(BaseContext.getCurrentId());
        warehouseGoodsUser.setWarehouseGoodsId(receiveWarehouseManageDTO.getWarehouseGoodsId());
        warehouseGoodsUser.setOperationType(WarehouseOperationTypeEnum.COLLECT);
        warehouseGoodsUser.setQuantity(receiveWarehouseManageDTO.getQuantity());
        warehouseGoodsUser.setOperationStatus(WarehouseOperationStatusEnum.WAIT);
        warehouseGoodsUser.setReturnStatus(WarehouseGoodsReturnStatusEnum.NO_NEED);
        warehouseGoodsUser.setUse(receiveWarehouseManageDTO.getUse());
        warehouseGoodsUser.setCreateTime();
        warehouseGoodsUser.setUpdateTime();
        warehouseGoodsUser.setReturnTime();
        warehouseMapper.insertReceiveWarehouse(warehouseGoodsUser);
    }

    /**
     * 借用物品
     * @param lentWarehouseManageDTO
     */
    @Override
    public void LentWarehouseManage(LentWarehouseManageDTO lentWarehouseManageDTO) {
        Long userId = BaseContext.getCurrentId();
        System.out.println("业务方法中获取的userId:" +userId);
        if (userId == null) {
            throw new BaseException("用户Id为空");
        }
        if (warehouseMapper.selectInitiateLoanApplicationWarehouseType(lentWarehouseManageDTO.getWarehouseGoodsId()) == 0) {
            throw new BaseException("不是借用仓库");
        }
        if (lentWarehouseManageDTO.getUse() == null) {
            throw new BaseException("用途不能为null");
        }
        if (lentWarehouseManageDTO.getQuantity() == null) {
            throw new BaseException("数量不能为null");
        }
        WarehouseGoodsUser warehouseGoodsUser = new WarehouseGoodsUser();
        warehouseGoodsUser.setId(IdWorker.getId());
        warehouseGoodsUser.setUserId(BaseContext.getCurrentId());
        warehouseGoodsUser.setWarehouseGoodsId(lentWarehouseManageDTO.getWarehouseGoodsId());
        warehouseGoodsUser.setOperationType(WarehouseOperationTypeEnum.LENT);
        warehouseGoodsUser.setQuantity(lentWarehouseManageDTO.getQuantity());
        warehouseGoodsUser.setOperationStatus(WarehouseOperationStatusEnum.WAIT);
        warehouseGoodsUser.setReturnStatus(WarehouseGoodsReturnStatusEnum.NOT);
        warehouseGoodsUser.setUse(lentWarehouseManageDTO.getUse());
        warehouseGoodsUser.setCreateTime();
        warehouseGoodsUser.setUpdateTime();
        warehouseGoodsUser.setReturnTime();
        warehouseMapper.insertLentWarehouse(warehouseGoodsUser);

    }

    /**
     * 归还物品
     * @param articleReturnDTO
     */
    @Override
    public void articleReturn(ArticleReturnDTO articleReturnDTO) {
        Long id=articleReturnDTO.getId();
        Long userId = BaseContext.getCurrentId();
        ArticleReturnVO articleReturnState=warehouseMapper.selectArticleReturnStatus(id);
        ArticleReturnVO returnRecord=warehouseMapper.selectReturnRecord(id);
        if (returnRecord.getOperationStatus()!=WarehouseOperationStatusEnum.START){
            throw new ArithmeticException("此申请未通过或已被驳回");
        } else if (articleReturnState.getReturnStatus()==WarehouseGoodsReturnStatusEnum.ALREADY|| articleReturnState.getReturnStatus()==WarehouseGoodsReturnStatusEnum.NO_NEED) {
            throw new ArithmeticException("无需归还或已经归还");
        } else if (articleReturnState.getOperationType()==WarehouseOperationTypeEnum.LENT) {
            WarehouseGoodsUser warehouseGoodsUser = new WarehouseGoodsUser();
            warehouseGoodsUser.setId(IdWorker.getId());
            warehouseGoodsUser.setUserId(BaseContext.getCurrentId());
            warehouseGoodsUser.setQuantity(articleReturnState.getQuantity());
            warehouseGoodsUser.setWarehouseGoodsId(articleReturnState.getWarehouseGoodsId());
            warehouseGoodsUser.setOperationType(WarehouseOperationTypeEnum.RETURN);
            warehouseGoodsUser.setOperationStatus(WarehouseOperationStatusEnum.WAIT);
            warehouseGoodsUser.setReturnStatus(WarehouseGoodsReturnStatusEnum.NO_NEED);
            warehouseGoodsUser.setUse(articleReturnDTO.getUse());
            warehouseGoodsUser.setCreateTime();
            warehouseGoodsUser.setUpdateTime();
            warehouseGoodsUser.setReturnTime();
            if (articleReturnDTO.getUse()==null){
                warehouseGoodsUser.setUse("");
            }else{
                warehouseGoodsUser.setUse(articleReturnDTO.getUse());
            }
            warehouseMapper.insertReturnRecord(warehouseGoodsUser);
        }else{
            throw new ArithmeticException("此申请操作不是借用或使用");
        }
    }
    /**
     * 查询物品管理
     *
     * @param warehouseManageDTO
     */
    @Override
    public PageResult<WarehouseUserManageVO> pageWarehouseManage(WarehouseManageDTO warehouseManageDTO) {
        WarehouseGoodsPageQuery warehouseGoodsPageQuery = new WarehouseGoodsPageQuery();
        BeanUtils.copyProperties(warehouseManageDTO, warehouseGoodsPageQuery);
        PageHelper.startPage(warehouseManageDTO.getPage(), warehouseManageDTO.getPageSize());
        com.github.pagehelper.Page<WarehouseUserManageVO> page = warehouseMapper.pageUserManage(warehouseGoodsPageQuery);
        long total = page.getTotal();
        List<WarehouseUserManageVO> roles = page.getResult();
        for (WarehouseUserManageVO warehouseRequisitionVO : roles) {
            List<DirectionEnum> directionEnumList = new ArrayList<>();
            String[] directionList = warehouseRequisitionVO.getDirection().replace("[", "").replace("]", "").replace("\"", "").split(",");
            for (String direction : directionList) {
                directionEnumList.add(DirectionEnum.valueOf(direction));
            }
            warehouseRequisitionVO.setDirectionEnumList(directionEnumList);
        }
        return new PageResult<>(total, roles);
    }

    /**
     * 分页查询所有申请记录
     * @param applicationRecordPageQueryDTO
     * @return
     */
    @Override
    public PageResult<WarehouseUserRecordingVO> pageUserRecording(ApplicationRecordPageQueryDTO applicationRecordPageQueryDTO) {
        MyApplicationRecordPageQuery myApplicationRecordPageQuery = new MyApplicationRecordPageQuery();
        BeanUtils.copyProperties(applicationRecordPageQueryDTO, myApplicationRecordPageQuery);
        Long userId=BaseContext.getCurrentId();
        PublicUtil.getPageHelper(new WarehouseGoodsUser(), applicationRecordPageQueryDTO.getSortField(), applicationRecordPageQueryDTO.getOrderType());
        PageHelper.startPage(applicationRecordPageQueryDTO.getPage(), applicationRecordPageQueryDTO.getPageSize());
        com.github.pagehelper.Page<WarehouseUserRecordingVO> page = warehouseMapper.pageUserRecording(myApplicationRecordPageQuery);
        long total = page.getTotal();
        List<WarehouseUserRecordingVO> roles = page.getResult();
        for (WarehouseUserRecordingVO warehouseRequisitionVO : roles) {
            List<DirectionEnum> directionEnumList = new ArrayList<>();
            String[] directionList = warehouseRequisitionVO.getDirection().replace("[", "").replace("]", "").replace("\"", "").split(",");
            for (String direction : directionList) {
                directionEnumList.add(DirectionEnum.valueOf(direction));
            }
            warehouseRequisitionVO.setDirections(directionEnumList);
        }
        return new PageResult<>(total, roles);
    }

    /**
     * 批量删除仓库物品
     * @param deleteForeachWarehouseDTO
     * @return
     */
    @Override
    public Result deleteForeachWarehouseGoods(DeleteForeachWarehouseDTO deleteForeachWarehouseDTO) {
        if (warehouseMapper.selectWarehouseGoodsId()==null){
            throw new BaseException("仓库物品可能已经被删除或不存在");
        }
        Result result = new Result<>();
        warehouseMapper.deleteForeachWarehouseGoods(deleteForeachWarehouseDTO);
        result.setMsg("删除成功");
        return result;
    }


}
