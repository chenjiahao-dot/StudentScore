package org.example.Demo.Server;

import org.example.Demo.DTO.RoleDTO.AddUserRoleDTO;
import org.example.Demo.DTO.RoleDTO.DeleteRoleDTO;
import org.example.Demo.DTO.RoleDTO.RolePageQueryDTO;
import org.example.Demo.DTO.RoleDTO.RoleSignInDTO;
import org.example.Demo.Result.PageResult;
import org.example.Demo.Result.Result;
import org.example.Demo.VO.roleVO.RoleBasicContentVO;
import org.example.Demo.VO.roleVO.RolesListAllVO;

public interface RoleService {
    RoleBasicContentVO getRoleBasicContent(Long id);

    PageResult<RolesListAllVO> listRole(RolePageQueryDTO rolePageQueryDTO);

    PageResult<RolesListAllVO> pagePrimaRole(RolePageQueryDTO pageQueryDTO);

    PageResult<RolesListAllVO> pageSecondaryRole(RolePageQueryDTO pageQueryDTO);

    Long addRole(RoleSignInDTO roleSignInDTO);

    Result deleteRole(DeleteRoleDTO deleteRoleDTO);

    void addUserRole(AddUserRoleDTO addUserRoleDTO);
}
