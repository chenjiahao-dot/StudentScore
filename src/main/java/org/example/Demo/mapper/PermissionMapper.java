package org.example.Demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.Demo.entity.Permission.Permission;

import java.util.List;

@Mapper
public interface PermissionMapper {
    @Select("select * from permission")
    List<Permission> allPermission();
}
