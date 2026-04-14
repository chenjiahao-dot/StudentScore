package org.example.Demo.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.Demo.DTO.User.AddUserRoleDTO;
import org.example.Demo.DTO.User.UpdateUserStatus;
import org.example.Demo.DTO.User.UserPageQueryDTO;
import org.example.Demo.VO.User.UserVO;
import org.example.Demo.VO.User.userMessageVO;
import org.example.Demo.annotation.AutoFill;
import org.example.Demo.entity.Users;
import org.example.Demo.entity.User;
import org.example.Demo.entity.newUser;
import com.common.enumeration.OperationType;
import org.example.Demo.enummerate.UserTypeEnum;

import java.util.List;

public interface UserMapper {
    /**
     * 根据邮箱查询用户
     *
     * @param mail
     * @return
     */
    @Select("select * from user where mail=#{mail}")
    newUser getByMail(String mail);
    /**
     * 新增用户
     *
     * @param newuser
     */
    @AutoFill(OperationType.INSERT)
    @Insert("insert into user ( id,name, user_name,user_type_enum,mobile,mail,password,sex_enum,class_id,image_address) " +
            "VALUES ( #{id}, #{name} ,#{userName},#{userTypeEnum}, #{mobile},#{mail},#{password}, #{sexEnum},#{classId},#{imageAddress}  )")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(newUser newuser);
    @Select("SELECT * FROM user WHERE mail = #{mail}")
    newUser selectByMail(String mail);

    // 根据手机号查询用户
    @Select("SELECT * FROM user WHERE mobile = #{mobile}")
    newUser selectByMobile(String mobile);

    Page<userMessageVO> pageUserMessage(UserPageQueryDTO pageQueryDTO);
    /**
     * 更新用户信息
     *
     * @param newuser
     */
    @AutoFill(OperationType.UPDATE)
    @Update("update user set password =#{password} where id=#{id}")
    void update(newUser newuser);
    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    newUser getUserById(Long id);


    /**
     * 修改用户账号状态
     * @param updateUserStatus1
     */
    void updateUserStatus(UpdateUserStatus updateUserStatus1);

    /**
     * 导出所有用户数据
     * @return
     */
    @Select("select * from user")
    List<User> selectAllUser();

    /**
     * 查询当前用户姓名
     * @return
     */
    @Select("select user_name from user where id=#{userId}")
    String selectName(Long userId);

    /**
     * 查询当前用户身份
     * @param userTypeEnum
     * @return
     */
    @Select("select user_type_enum from user where user_type_enum=#{userTypeEnum} ")
    String selectUserType(UserTypeEnum userTypeEnum);

    /**
     * 查询用户身份
     * @param userId
     * @return
     */
    @Select("select user_type_enum from user where id=#{id}")
    Users selectById(Long userId);

    /**
     * 判断用户是否存在
     * @param classId
     * @return
     */
    @Select("select id from classes where id=#{id}")
    newUser getClassById(Long classId);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id} ")
    User getById(Long id);


    List<UserVO> pageUser(UserPageQueryDTO pageQueryDTO);

    User selectPermissionById(Long userId);
    @Select("select user_id from user_role where role_id=#{roleId}")
    List<Long> getRole(Long roleId);
    @AutoFill(OperationType.INSERT)
    void AddRole(AddUserRoleDTO addUserRoleDTO);

    List<Long> getIdByRolePermissions(AddUserRoleDTO addUserRoleDTO);
}
