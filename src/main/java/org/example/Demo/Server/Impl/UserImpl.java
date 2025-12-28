package org.example.Demo.Server.Impl;

import com.example.Util.JwtUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.Common.PasswordEditFailedException;
import org.example.Demo.DTO.UserDTO.LoginRequestUserDTO;
import org.example.Demo.DTO.UserDTO.UpdateUserStatus;
import org.example.Demo.DTO.UserDTO.UserPageQueryDTO;
import org.example.Demo.DTO.UserDTO.UserRePasswordDTO;
import org.example.Demo.DTO.WarehouseDTO.UserSignInDTO;
import org.example.Demo.Enummerate.StatusEnum;
import org.example.Demo.Result.PageResult;
import org.example.Demo.Result.Result;
import org.example.Demo.Server.UserService;
import org.example.Demo.UserException.AddUserException;
import org.example.Demo.VO.UserVO.UserMessageVO;
import org.example.Demo.entity.User.newUser;
import org.example.Demo.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserImpl implements UserService {
    private final UserMapper userMapper;
    @Resource
    private JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    /**
     * 用户注册
     * @param userSignInDTO
     */
    @Override
    public void signIn(UserSignInDTO userSignInDTO) {
        newUser newUser=userMapper.getByMail(userSignInDTO.getMail());
        if(newUser!=null){
            throw new AddUserException("用户已存在");
        }
        newUser =new newUser();
        BeanUtils.copyProperties(userSignInDTO,newUser);
        newUser.setPassword(DigestUtils.md5DigestAsHex(userSignInDTO.getPassword().getBytes()));
        newUser.setNumber(userSignInDTO.getNumber());
        newUser.setMobile(userSignInDTO.getMobile());
        newUser.setUserName(newUser.getName());
        newUser.setSexEnum(userSignInDTO.getSexEnum());
        newUser.setStatusEnum(StatusEnum.START);
        newUser.setUserTypeEnum(userSignInDTO.getUserTypeEnum());
        newUser.setPositionEnum(userSignInDTO.getPositionEnum());
        newUser.setPositionIdentityEnum(userSignInDTO.getPositionIdentityEnum());
        try{
            newUser.setCreateTime();
            newUser.setUpdateTime();
            userMapper.insertUser(newUser);
        }catch (Exception e){
            throw new AddUserException("新增用户失败，原因:"+e.getMessage());
        }
    }

    /**
     * 用户登录
     * @param loginRequestUserDTO
     * @return
     */
    @Override
    public String login(LoginRequestUserDTO loginRequestUserDTO) {
        newUser newuser = null;
        String inputPwdEncrypted = DigestUtils.md5DigestAsHex(loginRequestUserDTO.getPassword().getBytes());
        // 1. 根据邮箱或手机号查询用户
        if (loginRequestUserDTO.getMail() != null) {
            newuser = userMapper.selectByMail(loginRequestUserDTO.getMail());
        } else if (loginRequestUserDTO.getMobile() != null) {
            newuser = userMapper.selectByMobile(loginRequestUserDTO.getMobile());
        }

        // 2. 验证用户是否存在
        if (newuser == null) {
            throw new RuntimeException("用户不存在");
        }

        // 3. 验证密码
        if (!inputPwdEncrypted.equals(newuser.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 4. 生成token并返回
        return jwtUtil.generateToken(newuser.getId());
    }

    /**
     * 分页查询所有用户
     * @param pageQueryDTO
     * @return
     */
    @Override
    public PageResult<UserMessageVO> pageUserMessage(UserPageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<UserMessageVO> page = userMapper.pageUserMessage(pageQueryDTO);
        long total = page.getTotal();
        List<UserMessageVO> users = page.getResult();
        return new PageResult<>(total, users);
    }

    /**
     * 修改密码
     * @param userRePasswordDTO
     */
    @Override
    public void rePassword(UserRePasswordDTO userRePasswordDTO) {
        newUser newuser = userMapper.getUserById(userRePasswordDTO.getId());
        if (newuser == null) {
            throw new BaseException("用户不存在"); // 提前抛异常，避免空指针
        }
        // 2. 再校验旧密码是否正确
        if (!newuser.getPassword().equals(userRePasswordDTO.getOldPassword())) {
            throw new PasswordEditFailedException("旧密码错误");
        }
        if (userRePasswordDTO.getNewPassword().equals(userRePasswordDTO.getOldPassword())) {
            throw new PasswordEditFailedException("新密码不能与旧密码相同，请重新设置");
        }
        // 4. 对新密码进行加密
        String encryptedNewPassword = passwordEncoder.encode(userRePasswordDTO.getNewPassword());
        // 3. 最后更新新密码
        newuser.setPassword(encryptedNewPassword);
        userMapper.update(newuser);
    }

    /**
     * 更改用户状态
     * @param updateUserStatus
     * @return
     */
    @Override
    public Result updateUserStatus(UpdateUserStatus updateUserStatus) {
        newUser newuser = userMapper.getUserById(updateUserStatus.getId());
        if (newuser == null) {
            throw new BaseException("该用户不存在");
        }
        UpdateUserStatus updateUserStatus1=new UpdateUserStatus();
        updateUserStatus1.setId(updateUserStatus.getId());
        updateUserStatus1.setStatus(updateUserStatus.getStatus());
        userMapper.updateUserStatus(updateUserStatus1);
        return Result.success("修改成功",updateUserStatus1);
    }
}
