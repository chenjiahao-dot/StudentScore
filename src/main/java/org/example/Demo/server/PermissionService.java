package org.example.Demo.server;

import com.common.Result.PageResult;
import org.example.Demo.DTO.Permission.PermissionPageQueryDTO;
import org.example.Demo.DTO.Permission.QueryRolePermissionDTO;
import org.example.Demo.VO.Permission.PermissionVO;
import org.example.Demo.entity.Permission;

import java.util.List;

public interface PermissionService {
    List<PermissionVO> allPermission();

    PageResult<PermissionVO> pagePermission(PermissionPageQueryDTO pageQueryDTO);

    PermissionVO getByID(Long id);

    List<String> queryRolePermission(QueryRolePermissionDTO queryRolePermissionDTO);


}
