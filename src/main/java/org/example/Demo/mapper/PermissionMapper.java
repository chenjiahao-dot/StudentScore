package org.example.Demo.mapper;

import com.github.pagehelper.Page;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.Demo.DTO.Permission.PermissionPageQueryDTO;
import org.example.Demo.VO.Permission.PermissionVO;
import org.example.Demo.entity.Permission;

import java.util.List;

@Mapper
public interface PermissionMapper {
    @Select("select * from permission")
    List<PermissionVO> allPermission();

    Page<PermissionVO> pageQueryPermission(PermissionPageQueryDTO pageQueryDTO);
    @Select("select * from permission where id=#{id}")
    PermissionVO getById(Long id);

    @Select("select id from role where name=#{name}")
    Long getRoleId(String name);

    @Select("select permission_id from role_permission where role_id=#{roleId};")
    List<Long> getRolePermissionId(Long roleId);

    @Select("select chinese_name from permission where id=#{permissionId};")
    String getChineseName(Long permissionId);


    List<String> getPermissionsByUserId(Long userId);
}
