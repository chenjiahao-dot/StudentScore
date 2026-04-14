package org.example.Demo.server.Impl;

import com.common.Context.BaseContext;
import com.common.Result.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.CourseException;
import org.example.Demo.DTO.Permission.PermissionPageQueryDTO;
import org.example.Demo.DTO.Permission.QueryRolePermissionDTO;
import org.example.Demo.UserException.AccountNotFoundException;
import org.example.Demo.VO.Permission.PermissionVO;
import org.example.Demo.enummerate.OrderTypeEnum;
import org.example.Demo.enummerate.UserTypeEnum;
import org.example.Demo.mapper.PermissionMapper;
import org.example.Demo.server.PermissionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionMapper permissionMapper;

    /**
     * 查询所有权限
     * @return
     */
    @Override
    public List<PermissionVO> allPermission() {
        List<PermissionVO> permissionList = permissionMapper.allPermission();
        return permissionList;
    }

    /**
     * 分页查询所有权限
     * @param pageQueryDTO
     * @return
     */
    @Override
    public PageResult<PermissionVO> pagePermission(PermissionPageQueryDTO pageQueryDTO) {
        UserTypeEnum userTypeEnum= BaseContext.getCurrentPrimaryUserEnum();
        if (userTypeEnum==UserTypeEnum.ADMIN) {
            PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
            String sortField = "id";
            String sortOrder = "desc";
            // 如果前端传了字段 → 替换
            if (StrUtil.isNotBlank(pageQueryDTO.getSortField())) {
                sortField = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, pageQueryDTO.getSortField());
            }
            // 如果前端传了 ASC → 改成正序
            if (pageQueryDTO.getOrderType() == OrderTypeEnum.ASC) {
                sortOrder = "asc";
            }
            PageHelper.orderBy(sortField + " " + sortOrder);
            List<PermissionVO> list = permissionMapper.pageQueryPermission(pageQueryDTO);
            Page<PermissionVO> page = (Page<PermissionVO>) list;

            return new PageResult<>(page.getTotal(), page.getResult());
        }else{
            throw new CourseException("无法查询权限，请找管理员！");
        }
    }

    /**
     * 查询权限详情
     * @param id
     * @return
     */
    @Override
    public PermissionVO getByID(Long id) {
        PermissionVO permission = permissionMapper.getById(id);
        if (permission == null) {
            throw new AccountNotFoundException("权限不存在");
        }
        return permission;
    }

    /**
     * 查询角色拥有权限
     * @param queryRolePermissionDTO
     * @return
     */
    @Override
    public List<String> queryRolePermission(QueryRolePermissionDTO queryRolePermissionDTO) {
        Long roleId = permissionMapper.getRoleId(queryRolePermissionDTO.getName());
        List<Long> permissionId = permissionMapper.getRolePermissionId(roleId);
        List<String> rolePermission = new ArrayList<>();
        for (Long aLong : permissionId) {
            String chineseName = permissionMapper.getChineseName(aLong);
            rolePermission.add(chineseName);
        }
        return rolePermission;
    }



}
