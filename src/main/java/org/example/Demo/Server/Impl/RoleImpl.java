package org.example.Demo.Server.Impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.example.Context.BaseContext;
import com.example.Util.PublicUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.AddUserRoleException;
import org.example.Demo.Common.BaseException;
import org.example.Demo.Common.PositionException;
import org.example.Demo.DTO.RoleDTO.*;
import org.example.Demo.Enummerate.PrimaryRoleEnum;
import org.example.Demo.OrderTypeEnum.OrderTypeEnum;
import org.example.Demo.Result.PageResult;
import org.example.Demo.Result.Result;
import org.example.Demo.Server.RoleService;
import org.example.Demo.UserException.AddUserException;
import org.example.Demo.VO.roleVO.RoleBasicContentVO;
import org.example.Demo.VO.roleVO.RolesListAllVO;
import org.example.Demo.VO.roleVO.UserRoleVO;
import org.example.Demo.entity.Role.Role;
import org.example.Demo.entity.Role.UserRole;
import org.example.Demo.entity.Role.UserRoleName;
import org.example.Demo.mapper.RoleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleImpl implements RoleService {

    private final RoleMapper roleMapper;

    /**
     * 查询角色基本信息
     * @param id
     * @return
     */
    @Override
    public RoleBasicContentVO getRoleBasicContent(Long id) {
        RoleBasicContentVO roleBasicContentVO = roleMapper.getRoleBasicContentById(id);
        if (roleMapper.countRoleId(id)==0) {
            throw new BaseException("用户详情不存在");
        }
        return roleBasicContentVO;
    }

    /**
     * 分页查询所有角色
     * @param rolePageQueryDTO
     * @return
     */
    @Override
    public PageResult<RolesListAllVO> listRole(RolePageQueryDTO rolePageQueryDTO) {
        PageHelper.startPage(rolePageQueryDTO.getPage(), rolePageQueryDTO.getPageSize());
        Page<RolesListAllVO> page = roleMapper.pageQueryRole(rolePageQueryDTO);
        long total = page.getTotal();
        List<RolesListAllVO> roles = page.getResult();
        return new PageResult<>(total, roles);
    }

    /**
     * 分页查询所有主角色
     * @param pageQueryDTO
     * @return
     */
    @Override
    public PageResult<RolesListAllVO> pagePrimaRole(RolePageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<RolesListAllVO> page = roleMapper.pageQueryPrimaRole(pageQueryDTO);
        long total = page.getTotal();
        List<RolesListAllVO> roles = page.getResult();
        return new PageResult<>(total, roles);
    }

    /**
     * 分页查询所有次角色
     * @param pageQueryDTO
     * @return
     */
    @Override
    public PageResult<RolesListAllVO> pageSecondaryRole(RolePageQueryDTO pageQueryDTO) {
        if (pageQueryDTO.getSortField() == null) {
            pageQueryDTO.setSortField("updateTime");
        }
        if (pageQueryDTO.getOrderType() == null) {
            pageQueryDTO.setOrderType(OrderTypeEnum.ASC);
        }
        PublicUtil.getPageHelper(new Role(), pageQueryDTO.getSortField(), pageQueryDTO.getOrderType());
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<RolesListAllVO> page = roleMapper.pageQuerySecondaryRole(pageQueryDTO);
        long total = page.getTotal();
        List<RolesListAllVO> roles = page.getResult();
        return new PageResult<>(total, roles);
    }
    /**
     * 新建次角色
     *
     * @param roleSignInDTO
     * @return 角色ID
     */
    @Override
    public Long addRole(RoleSignInDTO roleSignInDTO) {
        Role role = roleMapper.getRolechinesename(roleSignInDTO.getChineseName());
        if (role != null) {
            throw new AddUserException("新建角色失败，角色已存在");
        }
        role = new Role();
        BeanUtils.copyProperties(roleSignInDTO, role);
        role.setId(IdWorker.getId());
        role.setCreateTime();
        role.setUpdateTime();
        roleMapper.insert(role);
        return role.getId();
    }

    /**
     * 删除角色
     * @param deleteRoleDTO
     */
    @Override
    public Result deleteRole(DeleteRoleDTO deleteRoleDTO) {
        Result result = new Result();
        List<Long> idTwo = deleteRoleDTO.getId();
        for (int k = 0; k < idTwo.size(); k++) {
            if (roleMapper.select(idTwo.get(k))) {
                roleMapper.deleteRole(idTwo.get(k));
            }
        }
        result.setMsg("删除成功");
        result.setCode(1);
        return result;
    }


    /**
     * 为用户添加角色
     * @param addUserRoleDTO
     */
    @Override
    public void addUserRole(AddUserRoleDTO addUserRoleDTO) {
        Long userId=addUserRoleDTO.getUserId();
        Long roleId=addUserRoleDTO.getRoleId();
        String userName=roleMapper.selectUserName(userId);
        String roleName=roleMapper.selectRoleName(roleId);
        String comment=roleMapper.selectComment(roleId);
        String roleChineseName=roleMapper.selectRoleChineseName(roleId);
        Long targetRoleId = addUserRoleDTO.getRoleId();
        Role existRole = roleMapper.selectRoleById(targetRoleId);
        if (existRole == null) {
            throw new AddUserRoleException("要添加的角色不存在");
        }
        int userRoleCount = roleMapper.countRolesByUserId(userId);
        if (userRoleCount > 0) {
            throw new AddUserRoleException("当前用户已添加角色，无法重复添加");
        }
        UserRole userRole=new UserRole();
        BeanUtils.copyProperties(addUserRoleDTO,userRole);
        userRole.setId(addUserRoleDTO.getUserId());
        userRole.setUserId(userId);
        userRole.setRoleId(addUserRoleDTO.getRoleId());
        roleMapper.insertRole(userRole);

        UserRoleName userRoleName=new UserRoleName();
        BeanUtils.copyProperties(addUserRoleDTO,userRoleName);
        userRoleName.setUserId(userId);
        userRoleName.setRoleId(addUserRoleDTO.getRoleId());
        userRoleName.setUserName(userName);
        userRoleName.setRoleName(roleName);
        userRoleName.setRoleChineseName(roleChineseName);
        userRoleName.setComment(comment);
        userRoleName.setCreateTime(new Date());
        userRoleName.setUpdateTime(new Date());
        userRoleName.setCreateUser(userId);
        userRoleName.setUpdateUser(userId);
        userRoleName.setCreateUserName(userName);
        userRoleName.setUpdateUserName(userName);
        roleMapper.insertUserRole(userRoleName);
    }

    /**
     * 删除用户所拥有的角色
     * @param id
     */
    @Override
    public Result deleteUserRole(Long id) {
        Result result = new Result<>();
        if (roleMapper.selectUserRole(id)) {
            roleMapper.deleteUserRoleById(id);
            result.setMsg("删除成功");
            return result;
        }
        return result;
    }



    /**
     * 分页查询所有用户角色信息
     * @param userRolePageQueryDTO
     * @return
     */
    @Override
    public PageResult<UserRoleVO> listUserRole(UserRolePageQueryDTO userRolePageQueryDTO) {
        PageHelper.startPage(userRolePageQueryDTO.getPage(), userRolePageQueryDTO.getPageSize());
        Page<UserRoleVO> page = roleMapper.pageQueryUserRole(userRolePageQueryDTO);
        long total = page.getTotal();
        List<UserRoleVO> roles = page.getResult();
        return new PageResult<>(total, roles);
    }
}
