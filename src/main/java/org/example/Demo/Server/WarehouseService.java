package org.example.Demo.Server;

import org.example.Demo.DTO.WarehouseDTO.*;
import org.example.Demo.Result.PageResult;
import org.example.Demo.Result.Result;
import org.example.Demo.VO.Warehouse.WarehouseGoodsVO;
import org.example.Demo.VO.Warehouse.WarehouseUserManageVO;
import org.example.Demo.VO.Warehouse.WarehouseUserRecordingVO;
import org.example.Demo.VO.Warehouse.WarehouseVO;

public interface WarehouseService {

    WarehouseVO getWarehouseById(Long id);

    void addWarehouse(AddWarehouseDTO addWarehouseDTO);

    void updateWarehouse(UpdateWarehouseDTO updateWarehouseDTO);

    Result deleteWarehouse(Long id);

    PageResult<WarehouseVO> pageWarehouse(WarehousePageQueryDTO warehousePageQueryDTO);

    void addWarehouseGoods(AddOtherWarehouseDTO addOtherWarehouseDTO);

    Result deleteWarehouseGoods(Long id);

    WarehouseGoodsVO getWarehouseGoodsById(Long id);



    void putWarehouse(PutWarehouseDTO addUserWarehouseDTO);

    void outWarehouse(OutWarehouseDTO outWarehouseDTO);

    void updateWarehouseManageApproval(HandleWarehouseManageApprovalDTO handleWarehouseManageApprovalDTO);

    void updateWarehouseManageNoApproval(HandleWarehouseManageApprovalDTO handleWarehouseManageApprovalDTO);

    Result deleteForeachWarehouseGoodsUser(DeleteForeachWarehouseDTO deleteForeachWarehouseDTO);

    void ReceiveWarehouseManage(ReceiveWarehouseManageDTO receiveWarehouseManageDTO);

    void LentWarehouseManage(LentWarehouseManageDTO lentWarehouseManageDTO);

    void articleReturn(ArticleReturnDTO articleReturnDTO);

    PageResult<WarehouseUserManageVO> pageWarehouseManage(WarehouseManageDTO warehouseManageDTO);

    PageResult<WarehouseUserRecordingVO> pageUserRecording(ApplicationRecordPageQueryDTO applicationRecordPageQueryDTO);

    Result deleteForeachWarehouseGoods(DeleteForeachWarehouseDTO deleteForeachWarehouseDTO);
}
