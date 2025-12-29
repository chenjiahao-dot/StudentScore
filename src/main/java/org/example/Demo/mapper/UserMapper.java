package org.example.Demo.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.Demo.DTO.UserDTO.UpdateUserStatus;
import org.example.Demo.DTO.UserDTO.UserPageQueryDTO;
import org.example.Demo.VO.UserVO.UserMessageVO;
import org.example.Demo.annotation.AutoFill;
import org.example.Demo.entity.User.User;
import org.example.Demo.entity.User.newUser;
import org.example.Demo.enumeration.OperationType;

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
    @Insert("insert into user ( name, user_name,user_type_enum,sex_enum,status_enum,number, mobile, " +
            "mail, password, create_time, update_time) " +
            "VALUE ( #{name}, #{userName} ,#{userTypeEnum},#{sexEnum},#{statusEnum}, #{number}, #{mobile}," +
            "#{mail}, #{password}, #{createTime}, #{updateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(newUser newuser);
    @Select("SELECT * FROM user WHERE mail = #{mail}")
    newUser selectByMail(String mail);

    // 根据手机号查询用户
    @Select("SELECT * FROM user WHERE mobile = #{mobile}")
    newUser selectByMobile(String mobile);

    Page<UserMessageVO> pageUserMessage(UserPageQueryDTO pageQueryDTO);
    /**
     * 更新用户信息
     *
     * @param newuser
     */
    @AutoFill(OperationType.UPDATE)
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
}
