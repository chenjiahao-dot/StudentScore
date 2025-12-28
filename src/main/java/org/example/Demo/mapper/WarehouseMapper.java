package org.example.Demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.pageQuery.MyApplicationRecordPageQuery;
import com.example.pageQuery.WarehouseGoodsPageQuery;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.example.Demo.DTO.WarehouseDTO.DeleteForeachWarehouseDTO;
import org.example.Demo.DTO.WarehouseDTO.WarehousePageQueryDTO;
import org.example.Demo.VO.Warehouse.*;
import org.example.Demo.annotation.AutoFill;
import org.example.Demo.entity.Warehouse.Warehouse;
import org.example.Demo.entity.Warehouse.WarehouseGoods;
import org.example.Demo.entity.Warehouse.WarehouseGoodsUser;
import org.example.Demo.enumeration.OperationType;

@Mapper
public interface WarehouseMapper  extends BaseMapper<Warehouse> {
    /**
     * 判断仓库ID
     * @param id
     * @return
     */
    @Select("select * from warehouse where id=#{id}")
    WarehouseVO selectWarehouseById(Long id);

    /**
     * 判断仓库ID总数
     * @param id
     * @return
     */
    @Select("select count(*) from warehouse where id=#{id}")
    int countWarehouse(Long id);
    /**
     * 新增仓库
     *
     * @param warehouse
     */
    @Insert("INSERT INTO warehouse(name,address,create_user,create_user_name,create_time,update_user,update_user_name,update_time)" +
            " VALUES (#{name},#{address},#{createUser},#{createUserName},#{createTime},#{updateUser},#{updateUserName},#{updateTime})")
    @AutoFill(OperationType.INSERT)
    //插入后返回主键ID
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertWarehouse(Warehouse warehouse);
    /**
     * 对仓库进行修改
     *
     * @param warehouse
     */
    @Update("update warehouse set name=#{name}, address=#{address} where id=#{id}")
    @AutoFill(OperationType.UPDATE)
    void updateWarehouse(Warehouse warehouse);

    /**
     * 判断要删除的仓库id是否存在
     * @param id
     * @return
     */
    @Select("select warehouse.id from warehouse where id=#{id}")
    boolean selectById(Long id);

    /**
     * 删除仓库
     * @param id
     */
    @Delete("delete from warehouse where id=#{id}")
    void deleteById(Long id);

    /**
     * 分页查询所有仓库
     * @param warehousePageQueryDTO
     * @return
     */

    IPage<WarehouseVO> pageWarehouse2(IPage<WarehouseVO> page,
                                     @Param("dto") WarehousePageQueryDTO warehousePageQueryDTO);
    /**
     * 新增物品仓库
     *
     * @param warehouseGoods
     */
    @Insert("INSERT INTO warehouse_goods(warehouse_type,goods_type,name,model,warehouse_id,direction,comment,unit,total,residual,lent,collect,disuse,create_time,update_time)" +
            " VALUES (#{warehouseType.code},#{goodsType.code},#{name},#{model},#{warehouseId},#{direction},#{comment},#{unit},#{total},#{residual},#{lent},#{collect},#{disuse},#{createTime},#{updateTime})")
    @AutoFill(OperationType.INSERT)
    //插入后返回主键ID
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertWarehouseGoods(WarehouseGoods warehouseGoods);
    /**
     * 删除物品仓库
     * @param id
     */
    @Delete("delete from warehouse_goods where id=#{id}")
    void deleteWarehouseGoodsById(Long id);
    /**
     * 判断要删除的物品仓库id是否存在
     * @param id
     * @return
     */
    @Select("select warehouse_goods.id from warehouse_goods where id=#{id}")
    boolean selectWarehouseGoodsById(Long id);
    /**
     * 判断物品仓库ID
     * @param id
     * @return
     */
    @Select("select * from warehouse_goods where id=#{id}")
    WarehouseGoodsVO selectWarehouseGoods(Long id);
    /**
     * 判断物品仓库ID总数
     * @param id
     * @return
     */
    @Select("select count(*) from warehouse_goods where id=#{id}")
    int countWarehouseGoods(Long id);

    /**
     * 入库
     * @param warehouseGoodsUser
     */
    @Insert("INSERT INTO warehouse_goods_user(id,parent_id,warehouse_goods_id,user_id,operation_type,quantity,`use`,operation_status, return_status,create_time,update_time,return_time) " +
            "VALUES (#{id},#{parentId},#{warehouseGoodsId},#{userId},#{operationType.code},#{quantity},#{use},#{operationStatus.code},#{returnStatus.code},#{createTime},#{updateTime},#{returnTime})")
    @AutoFill(OperationType.INSERT)
    void insertPutWarehouse(WarehouseGoodsUser warehouseGoodsUser);
    /**
     * 出库
     * @param warehouseGoodsUser
     */
    @Insert("INSERT INTO warehouse_goods_user(id,parent_id,warehouse_goods_id,user_id,operation_type,quantity,`use`,operation_status, return_status,create_time,update_time,return_time) " +
            "VALUES (#{id},#{parentId},#{warehouseGoodsId},#{userId},#{operationType.code},#{quantity},#{use},#{operationStatus.code},#{returnStatus.code},#{createTime},#{updateTime},#{returnTime})")
    @AutoFill(OperationType.INSERT)
    void insertOutWarehouse(WarehouseGoodsUser warehouseGoodsUser);

    /**
     * 处理审批
     * @param warehouseGoodsUser
     */
    void updateWarehouseManageApproval(WarehouseGoodsUser warehouseGoodsUser);

    /**
     * 判断是否是未审批
     * @param id
     * @return
     */
    @Select("SELECT * FROM warehouse_goods_user WHERE id=#{id}")
    WarehouseGoodsUser selectStatusById(Long id);

    void updateWarehouseManageNoApproval(WarehouseGoodsUser warehouseGoodsUser);

    /**
     * 批量删除审批
     * @param
     * @return
     */
    void deleteForeachWarehouse(DeleteForeachWarehouseDTO deleteForeachWarehouseDTO);

    /**
     * 领取物品
     * @param warehouseGoodsUser
     */

    @Insert("INSERT INTO warehouse_goods_user(id,parent_id,warehouse_goods_id,user_id,operation_type,quantity,`use`,operation_status, return_status,create_time,update_time,return_time) " +
            "VALUES (#{id},#{parentId},#{warehouseGoodsId},#{userId},#{operationType.code},#{quantity},#{use},#{operationStatus.code},#{returnStatus.code},#{createTime},#{updateTime},#{returnTime})")
    @AutoFill(OperationType.INSERT)
    void insertReceiveWarehouse(WarehouseGoodsUser warehouseGoodsUser);

    /**
     * 借用物品
     * @param warehouseGoodsUser
     */
    @Insert("INSERT INTO warehouse_goods_user(id,parent_id,warehouse_goods_id,user_id,operation_type,quantity,`use`,operation_status, return_status,create_time,update_time,return_time) " +
            "VALUES (#{id},#{parentId},#{warehouseGoodsId},#{userId},#{operationType.code},#{quantity},#{use},#{operationStatus.code},#{returnStatus.code},#{createTime},#{updateTime},#{returnTime})")
    @AutoFill(OperationType.INSERT)
    void insertLentWarehouse(WarehouseGoodsUser warehouseGoodsUser);

    /**
     * 判断物品的审批状态
     * @param id
     * @return
     */
    @Select("select warehouse_goods_id,user_id,`use`,quantity,return_status,operation_status from warehouse_goods_user where id = #{id}")
    ArticleReturnVO selectReturnRecord(Long id);
    /**
     * 判断物品的归还状态
     * @param id
     * @return
     */
    @Select("select warehouse_goods_id,user_id,`use`,quantity,return_status,operation_type from warehouse_goods_user where id = #{id}")
    ArticleReturnVO selectArticleReturnStatus(Long id);

    /**
     * 归还物品
     * @param warehouseGoodsUser
     */
    @Insert("INSERT INTO warehouse_goods_user(id,parent_id,warehouse_goods_id,user_id,operation_type,quantity,`use`,operation_status, return_status,create_time,update_time,return_time) " +
            "VALUES (#{id},#{parentId},#{warehouseGoodsId},#{userId},#{operationType.code},#{quantity},#{use},#{operationStatus.code},#{returnStatus.code},#{createTime},#{updateTime},#{returnTime})")
    @AutoFill(OperationType.INSERT)
    void insertReturnRecord(WarehouseGoodsUser warehouseGoodsUser);
    /**
     * 分页查询物品管理
     *
     * @param warehouseGoodsPageQuery
     */
    Page<WarehouseUserManageVO> pageUserManage(WarehouseGoodsPageQuery warehouseGoodsPageQuery);

    /**
     * 分页查询申请记录
     * @param myApplicationRecordPageQuery
     * @return
     */
    Page<WarehouseUserRecordingVO> pageUserRecording(MyApplicationRecordPageQuery myApplicationRecordPageQuery);

    /**
     * 判断仓库类型
     * @param id
     * @return
     */
    @Select("SELECT count(*) FROM warehouse_goods WHERE id=#{id} AND warehouse_type=1")
    int selectInitiateLoanApplicationWarehouseType(Long id);
    /**
     * 判断仓库类型
     * @param warehouseGoodsId
     * @return
     */
    @Select("SELECT count(*) FROM warehouse_goods WHERE id=#{id} AND warehouse_type=0")
    int selectReceoveLoanApplicationWarehouseType(Long warehouseGoodsId);

    /**
     * 批量删除物品
     * @param deleteForeachWarehouseDTO
     */
    void deleteForeachWarehouseGoods(DeleteForeachWarehouseDTO deleteForeachWarehouseDTO);

    /**
     * 查询需要批量删除物品的id是否存在
     * @return
     */
    @Select("select warehouse_goods.id from warehouse_goods where id=#{id}")
    DeleteForeachWarehouseDTO selectWarehouseGoodsId();

    /**
     * 判断仓库审批id是否存在
     * @return
     */
    @Select("select warehouse_goods_user.id from warehouse_goods_user where id=#{id} AND operation_status=0")
    DeleteForeachWarehouseDTO selectWarehouseGoodsUserById();
}
