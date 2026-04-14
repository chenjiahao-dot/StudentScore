package org.example.Demo.server.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.Context.BaseContext;
import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.CourseException;
import org.example.Demo.DTO.Role.AddRolePermissionDTO;
import org.example.Demo.DTO.Role.RoleIdPermissionDTO;
import org.example.Demo.DTO.Role.RolePageQueryDTO;
import org.example.Demo.VO.Permission.PermissionVO;
import org.example.Demo.VO.Role.PressionVO;
import org.example.Demo.VO.Role.RoleBasicContentVO;
import org.example.Demo.VO.Role.RolesListAllVO;
import org.example.Demo.enummerate.OrderTypeEnum;
import org.example.Demo.enummerate.UserTypeEnum;
import org.example.Demo.mapper.RoleMapper;
import org.example.Demo.server.RoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;

    /**
     * 查询角色基本信息
     * @param id
     * @return
     */
    @Override
    public RoleBasicContentVO getRoleBasicContent(Long id) {
        return roleMapper.getRoleBasicContentById(id);
    }

    /**
     * 添加角色权限
     * @param addRolePermissionDTO
     * @return
     */
    @Override
    public Result AddPermissions(AddRolePermissionDTO addRolePermissionDTO) {
        Result result = new Result<>();
        List<Long> permissionList = new ArrayList<>();
        List<Long> permissionsIdList = roleMapper.getPermissions(addRolePermissionDTO.getRoleId());
        for (Long i : addRolePermissionDTO.getPermissionIdList()) {
            if (!permissionsIdList.contains(i)) {
                permissionList.add(i);
            }
        }
        if (!permissionList.isEmpty()) {
            addRolePermissionDTO.setPermissionIdList(permissionList);
            roleMapper.AddPermissions(addRolePermissionDTO);
            result.setMsg("添加" + permissionList + "成功,id如下");
            result.setCode(1);
            List<Long> IdByRolePermissions = roleMapper.getIdByRolePermissions(addRolePermissionDTO);
            result.setData(IdByRolePermissions);
        } else {
            result.setMsg("添加失败,重复权限id如下");
            addRolePermissionDTO.getPermissionIdList().retainAll(permissionsIdList);
            result.setData(addRolePermissionDTO.getPermissionIdList());
        }
        return result;
    }

    /**
     * 查询所有角色
     * @param rolePageQueryDTO
     * @return
     */
    @Override
    public PageResult<RolesListAllVO> listRole(RolePageQueryDTO rolePageQueryDTO) {
        UserTypeEnum userTypeEnum= BaseContext.getCurrentPrimaryUserEnum();
        if (userTypeEnum==UserTypeEnum.ADMIN) {
            PageHelper.startPage(rolePageQueryDTO.getPage(), rolePageQueryDTO.getPageSize());
            String sortField = "id";
            String sortOrder = "desc";
            // 如果前端传了字段 → 替换
            if (StrUtil.isNotBlank(rolePageQueryDTO.getSortField())) {
                sortField = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, rolePageQueryDTO.getSortField());
            }
            // 如果前端传了 ASC → 改成正序
            if (rolePageQueryDTO.getOrderType() == OrderTypeEnum.ASC) {
                sortOrder = "asc";
            }
            PageHelper.orderBy(sortField + " " + sortOrder);
            List<RolesListAllVO> list = roleMapper.pageQueryPermission(rolePageQueryDTO);
            Page<RolesListAllVO> page = (Page<RolesListAllVO>) list;

            return new PageResult<>(page.getTotal(), page.getResult());
        }else{
            throw new CourseException("无法查询权限，请找管理员！");
        }
    }
    /**
     * 根据id查询角色权限
     * @param roleIdPermissionDTO
     * @return
     */
    @Override
    public List<PressionVO> getPermissionsByRoleId(RoleIdPermissionDTO roleIdPermissionDTO) {
        return roleMapper.selectPermissionsByRoleId(roleIdPermissionDTO);
    }






}
