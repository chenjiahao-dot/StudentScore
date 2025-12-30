package org.example.Demo.Server.Impl;

import com.example.Context.BaseContext;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.AddUserRoleException;
import org.example.Demo.Common.BaseException;
import org.example.Demo.Common.PositionException;
import org.example.Demo.DTO.PositionDTO.AddUserPositionDTO;
import org.example.Demo.DTO.PositionDTO.PositionPageQueryDTO;
import org.example.Demo.DTO.PositionDTO.UpdateUserPositionDTO;
import org.example.Demo.Enummerate.PositionEnum;
import org.example.Demo.Enummerate.PositionIdentityEnum;
import org.example.Demo.Result.PageResult;
import org.example.Demo.Result.Result;
import org.example.Demo.Server.PositionService;
import org.example.Demo.VO.PositionVO.PositionPageQueryVO;
import org.example.Demo.VO.PositionVO.PositionVO;
import org.example.Demo.VO.Warehouse.WarehouseVO;
import org.example.Demo.entity.Position.Position;
import org.example.Demo.mapper.PositionMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PositionImpl implements PositionService {
    private final PositionMapper positionMapper;

    /**
     * 为用户添加岗位
     * @param addUserPositionDTO
     */
    @Override
    public void addUserPosition(AddUserPositionDTO addUserPositionDTO) {
        Long userId = BaseContext.getCurrentId();
        // 新增：校验用户是否已存在岗位
        Position existing = positionMapper.selectUserById(addUserPositionDTO.getUserId());
        PositionIdentityEnum positionidentityEnum=positionMapper.selectPositionidentityById(userId);
        if (existing != null) {
            throw new PositionException("当前用户已添加岗位，无法重复添加");
        }
        if (positionidentityEnum==PositionIdentityEnum.BOARD_OF_DIRECTORS){
            String user=positionMapper.selectUserId(userId);
            Position position = new Position();
            BeanUtils.copyProperties(addUserPositionDTO, position);
            position.setUserId(addUserPositionDTO.getUserId());
            position.setCreateUser(userId);
            position.setCreateUserName(user);
            position.setUpdateUser(userId);
            position.setUpdateUserName(user);
            position.setCreateTime();
            position.setUpdateTime();
            positionMapper.insert(position);
        }else {
            throw new AddUserRoleException("无权限添加角色");
        }

    }

    /**
     * 分页查询用户岗位信息
     * @param positionPageQueryDTO
     * @return
     */
    @Override
    public PageResult<PositionPageQueryVO> pagePosition(PositionPageQueryDTO positionPageQueryDTO) {
        PageHelper.startPage(positionPageQueryDTO.getPage(), positionPageQueryDTO.getPageSize());
        Page<PositionPageQueryVO> page = positionMapper.pageUserPosition(positionPageQueryDTO);
        long total = page.getTotal();
        List<PositionPageQueryVO> users = page.getResult();
        return new PageResult<>(total, users);
    }

    /**
     * 修改用户岗位
     * @param updateUserPositionDTO
     */
    @Override
    public void updateUserPosition(UpdateUserPositionDTO updateUserPositionDTO) {
        Long userId = BaseContext.getCurrentId();
        PositionIdentityEnum positionidentityEnum = positionMapper.selectPositionidentityById(userId);
        if (positionidentityEnum == PositionIdentityEnum.BOARD_OF_DIRECTORS) {
            String user = positionMapper.selectUserId(userId);
            Position position = new Position();
            BeanUtils.copyProperties(updateUserPositionDTO, position);
            position.setCreateUser(userId);
            position.setUpdateUser(userId);
            position.setCreateTime();
            position.setUpdateTime();
            position.setUpdateUserName(user);
            position.setCreateUserName(user);
            positionMapper.updateUserPosition(position);
        }else {
            throw new AddUserRoleException("无权限修改角色");
        }
    }

    /**
     * 删除用户岗位
     * @param id
     * @return
     */
    @Override
    public Result deleteUserPosition(Long id) {
        Result result = new Result<>();
        if (positionMapper.countUserPositionById(id) == 0) {
            throw new BaseException("用户岗位信息不存在，无法删除");
        }else
            positionMapper.selectWarehouseGoodsById(id);{
            positionMapper.deleteWarehouseGoodsById(id);
            result.setMsg("删除成功");
            return result;
        }
    }

    /**
     * 查询用户岗位详情
     * @param id
     * @return
     */
    @Override
    public PositionVO getUserPositionById(Long id) {
        PositionVO positionVO = positionMapper.selectWarehouseById(id);
        if (positionMapper.countUserPositionById(id) == 0) {
            throw new BaseException("用户岗位详情不存在");
        }
        return positionVO;
    }


}
