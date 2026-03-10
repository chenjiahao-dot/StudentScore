package org.example.Demo.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.Demo.DTO.PositionDTO.PositionPageQueryDTO;
import org.example.Demo.Enummerate.PositionEnum;
import org.example.Demo.Enummerate.PositionIdentityEnum;
import org.example.Demo.Server.Impl.PositionImpl;
import org.example.Demo.VO.PositionVO.PositionPageQueryVO;
import org.example.Demo.VO.PositionVO.PositionVO;
import org.example.Demo.entity.Position.Position;

@Mapper
public interface PositionMapper {
    /**
     * 判断用户是否添加过岗位
     *
     * @return
     */
    @Select("select * from position where user_id=#{userId}")
    Position selectUserById(Long userId);

    /**
     * 为用户添加岗位
     * @param position
     */
    void insert(Position position);

    /**
     * 查询用户姓名
     * @return
     */
    @Select("select user_name from user where id=#{userId}")
    String selectUserId(Long id);

    /**
     * 分页查询用户岗位信息
     * @param positionPageQueryDTO
     * @return
     */
    Page<PositionPageQueryVO> pageUserPosition(PositionPageQueryDTO positionPageQueryDTO);

    /**
     * 修改用户岗位
     * @param position
     */
    void updateUserPosition(Position position);
    /**
     * 判断要删除的用户岗位id是否存在
     * @param id
     * @return
     */
    @Select("select position.id from position where id=#{id}")
    boolean selectWarehouseGoodsById(Long id);
    /**
     * 删除用户岗位
     * @param id
     */
    @Delete("delete from position where id=#{id}")
    void deleteWarehouseGoodsById(Long id);

    /**
     * 判断用户岗位Id是否存在
     * @param id
     * @return
     */
    @Select("select * from position where id=#{id}")
    PositionVO selectWarehouseById(Long id);

    /**
     * 查看用户岗位详情
     * @param id
     * @return
     */
    @Select("select count(*) from position where id=#{id} ")
    int countUserPositionById(Long id);

    /**
     * 查询当前用户的岗位
     * @param userId
     * @return
     */
    @Select("select identity_name from position where id=#{userId} ")
    PositionIdentityEnum selectPositionIdentityById(Long userId);
}
