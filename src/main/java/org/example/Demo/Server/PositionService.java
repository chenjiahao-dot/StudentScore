package org.example.Demo.Server;

import org.example.Demo.DTO.PositionDTO.AddUserPositionDTO;
import org.example.Demo.DTO.PositionDTO.PositionPageQueryDTO;
import org.example.Demo.DTO.PositionDTO.UpdateUserPositionDTO;
import org.example.Demo.Result.PageResult;
import org.example.Demo.Result.Result;
import org.example.Demo.VO.PositionVO.PositionPageQueryVO;
import org.example.Demo.VO.PositionVO.PositionVO;
import org.example.Demo.entity.Position.Position;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionService {
    void addUserPosition(AddUserPositionDTO addUserPositionDTO);

    PageResult<PositionPageQueryVO> pagePosition(PositionPageQueryDTO positionPageQueryDTO);

    void updateUserPosition(UpdateUserPositionDTO updateUserPositionDTO);

    Result deleteUserPosition(Long id);

    PositionVO getUserPositionById(Long id);
}
