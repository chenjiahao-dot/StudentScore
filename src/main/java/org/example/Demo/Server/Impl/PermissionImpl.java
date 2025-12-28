package org.example.Demo.Server.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Server.PermissionService;
import org.example.Demo.entity.Permission.Permission;
import org.example.Demo.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionImpl implements PermissionService {
    private final PermissionMapper permissionMapper;

    /**
     * 查询所有权限
     * @return
     */
    @Override
    public List<Permission> allPermission() {
        List<Permission> permissionList = permissionMapper.allPermission();
        return permissionList;
    }
}
