package org.example.Demo.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.example.Demo.DTO.RoleDTO.AddUserRoleDTO;
import org.example.Demo.DTO.RoleDTO.RolePageQueryDTO;
import org.example.Demo.DTO.RoleDTO.UpdateUserRoleDTO;
import org.example.Demo.DTO.RoleDTO.UserRolePageQueryDTO;
import org.example.Demo.VO.roleVO.RoleBasicContentVO;
import org.example.Demo.VO.roleVO.RolesListAllVO;
import org.example.Demo.VO.roleVO.UserRoleVO;
import org.example.Demo.annotation.AutoFill;
import org.example.Demo.entity.Role.Role;
import org.example.Demo.entity.Role.UserRole;
import org.example.Demo.enumeration.OperationType;

@Mapper
public interface RoleMapper {
    /**
     * 查询角色详情
     * @param id
     * @return
     */
    @Select("select * from role where id=#{id} ")
    RoleBasicContentVO getRoleBasicContentById(Long id);

    /**
     * 判断角色ID总数
     * @param id
     * @return
     */
    @Select("select count(*) from role where id=#{id}")
    int countRoleId(Long id);

    /**
     * 分页查询所有角色
     * @param rolePageQueryDTO
     * @return
     */
    Page<RolesListAllVO> pageQueryRole(RolePageQueryDTO rolePageQueryDTO);

    /**
     * 分页查询所有主角色
     * @param pageQueryDTO
     * @return
     */
    Page<RolesListAllVO> pageQueryPrimaRole(RolePageQueryDTO pageQueryDTO);

    /**
     * 分页查询所有次角色
     * @param pageQueryDTO
     * @return
     */
    Page<RolesListAllVO> pageQuerySecondaryRole(RolePageQueryDTO pageQueryDTO);

    /**
     * 判断是否存在相同的角色
     * @param chineseName
     * @return
     */
    @Select("SELECT * FROM role WHERE chinese_name = #{chineseName}")
    Role getRolechinesename(String chineseName);

    /**
     * 添加次角色
     * @param role
     */
    /**
     * 新建次角色
     *
     * @param role
     */
    @Insert("insert into role (id, name, chinese_name,comment, create_time, update_time, create_user, create_user_name, update_user, update_user_name,prima)" +
            " value (#{id},#{name},#{chineseName},#{comment}, #{createTime}, #{updateTime}, #{createUser}, #{createUserName}, #{updateUser}, #{updateUserName},0)")
    @AutoFill(OperationType.INSERT)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Role role);

    /**
     * 判断是否是次角色
     * @param aLong
     * @return
     */
    @Select("select id from role where role.prima=0 && id=#{id}")
    boolean select(Long aLong);

    /**
     * 删除角色
     * @param aLong
     */
    @Delete("delete from role where id=#{id}")
    void deleteRole(Long aLong);

    /**
     * 判断当前用户是否添加过角色
     * @param userId
     * @return
     */
    @Select("select * from user_role where id=#{userId}")
    Role selectUserRoleById(Long userId);




    /**
     * 为用户添加角色
     * @param userRole
     */
    @Insert("insert into user_role(user_id, role_id) VALUES (#{userId}, #{roleId})")
    void insertRole(UserRole userRole);

    /**
     * 判断角色是否存在
     * @param userId
     * @return
     */
    @Select("select * from role where id = #{id}")
    Role selectRoleById(Long userId);

    /**
     * 判断用户是否已添加过任何角色（通过user_id查user_role表）
     * @param userId 用户ID
     * @return 关联记录数（>0则已添加过角色）
     */
    @Select("select count(1) from user_role where user_id = #{userId}")
    int countRolesByUserId(@Param("userId") Long userId);
    /**
     * 判断要删除的用户角色id是否存在
     * @param id
     * @return
     */
    @Select("select user_role.id from user_role where id=#{id}")
    boolean selectUserRole(Long id);

    /**
     * 删除用户所拥有的角色
     * @param id
     */
    @Delete("delete  from user_role where id=#{id} ")
    void deleteUserRoleById(Long id);

    /**
     * 修改用户所拥有的角色
     * @param updateUserRoleDTO
     */
    void updateUserRole(UpdateUserRoleDTO updateUserRoleDTO);

    /**
     * 分页查询所有用户角色信息
     * @param userRolePageQueryDTO
     * @return
     */
    @Select("select * from user_role_name")
    Page<UserRoleVO> pageQueryUserRole(UserRolePageQueryDTO userRolePageQueryDTO);
}
