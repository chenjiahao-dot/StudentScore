package org.example.Demo.server;

import com.common.Result.PageResult;
import com.common.Result.Result;
import org.example.Demo.DTO.Role.AddRolePermissionDTO;
import org.example.Demo.DTO.Role.RoleIdPermissionDTO;
import org.example.Demo.DTO.Role.RolePageQueryDTO;
import org.example.Demo.VO.Role.PressionVO;
import org.example.Demo.VO.Role.RoleBasicContentVO;
import org.example.Demo.VO.Role.RolesListAllVO;

import java.util.List;

public interface RoleService {
    RoleBasicContentVO getRoleBasicContent(Long id);

    Result AddPermissions(AddRolePermissionDTO addRolePermissionDTO);

    PageResult<RolesListAllVO> listRole(RolePageQueryDTO rolePageQueryDTO);

    List<PressionVO> getPermissionsByRoleId(RoleIdPermissionDTO roleIdPermissionDTO);
}
