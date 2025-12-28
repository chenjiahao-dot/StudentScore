package org.example.Demo.Server;

import org.example.Demo.entity.Permission.Permission;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PermissionService {
    List<Permission> allPermission();
}
