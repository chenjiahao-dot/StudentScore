package org.example.Demo.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.enumeration.OperationType;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.Demo.DTO.Role.AddRolePermissionDTO;
import org.example.Demo.DTO.Role.RoleIdPermissionDTO;
import org.example.Demo.DTO.Role.RolePageQueryDTO;
import org.example.Demo.VO.Role.PressionVO;
import org.example.Demo.VO.Role.RoleBasicContentVO;
import org.example.Demo.VO.Role.RolesListAllVO;
import org.example.Demo.annotation.AutoFill;

import java.util.List;

@Mapper
public interface RoleMapper {
    @Select("SELECT id, name, chinese_name,comment,prima,primary_role_enum FROM role WHERE id = #{id}")
    RoleBasicContentVO getRoleBasicContentById(Long id);
    @Select("select permission_id from role_permission where role_id=#{roleId}")
    List<Long> getPermissions(Long roleId);
    @AutoFill(OperationType.INSERT)
    void AddPermissions(AddRolePermissionDTO addRolePermissionDTO);

    List<Long> getIdByRolePermissions(AddRolePermissionDTO addRolePermissionDTO);

    List<RolesListAllVO> pageQueryPermission(RolePageQueryDTO rolePageQueryDTO);

    List<PressionVO> getPressionById(Long roleId);


    List<PressionVO> selectPermissionsByRoleId(RoleIdPermissionDTO roleIdPermissionDTO);
}
