package org.example.Demo.server.Impl;

import com.common.Context.BaseContext;
import com.common.Util.JwtUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.Common.ClassException;
import org.example.Demo.Common.PasswordEditFailedException;
import org.example.Demo.Common.UserException;
import org.example.Demo.DTO.User.LoginRequestUserDTO;
import org.example.Demo.DTO.User.UserRePasswordDTO;
import org.example.Demo.DTO.User.UserSignInDTO;
import org.example.Demo.UserException.AccountNotFoundException;
import org.example.Demo.entity.User;
import org.example.Demo.enummerate.UserTypeEnum;
import org.example.Demo.server.UserServer;
import org.example.Demo.UserException.AddUserException;
import org.example.Demo.entity.newUser;
import org.example.Demo.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserImpl implements UserServer {
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
        newUser user = userMapper.getByMail(userSignInDTO.getMail());
        newUser classId=userMapper.getClassById(userSignInDTO.getClassId());
        if (user != null) {
            throw new AddUserException("用户已存在");
        }
        if (classId == null) {
            throw new ClassException("班级不存在");
        }
        newUser newUser =new newUser();
        BeanUtils.copyProperties(userSignInDTO,newUser);
        newUser.setPassword(DigestUtils.md5DigestAsHex(userSignInDTO.getPassword().getBytes()));
        newUser.setMobile(userSignInDTO.getMobile());
        newUser.setUserName(newUser.getName());
        newUser.setSexEnum(userSignInDTO.getSexEnum());
        newUser.setUserTypeEnum(userSignInDTO.getUserTypeEnum());
        newUser.setSexEnum(userSignInDTO.getSexEnum());
        newUser.setClassId(userSignInDTO.getClassId());
        newUser.setMail(userSignInDTO.getMail());
        try{
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
        String account = loginRequestUserDTO.getMail();  // 前端统一把账号放 mail 字段
        String inputPwdEncrypted = DigestUtils.md5DigestAsHex(loginRequestUserDTO.getPassword().getBytes());
        // 1. 根据邮箱或手机号查询用户
        if (account != null && account.matches("^1[3-9]\\d{9}$")) {
            // 是手机号 → 按 mobile 查询
           newuser = userMapper.selectByMobile(account);
        } else {
            // 不是手机号 → 按 mail 查询
            newuser = userMapper.selectByMail(account);
        }
        // 2. 验证用户是否存在
        if (newuser == null) {
            throw new RuntimeException("用户不存在");
        }

        // 3. 验证密码
        if (!inputPwdEncrypted.equals(newuser.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        BaseContext.setCurrentId(newuser.getId());
        // 4. 生成token并返回
        return jwtUtil.generateToken(newuser.getId());
    }



    /**
     * 修改密码
     * @param userRePasswordDTO
     */
    @Override
    @Transactional
    public void rePassword(UserRePasswordDTO userRePasswordDTO) {
        Long userId=BaseContext.getCurrentId();
        newUser newuser=userMapper.getUserById(userId);
        if (newuser == null) {
            throw new BaseException("用户不存在"); // 提前抛异常，避免空指针
        }
        // 2. 再校验旧密码是否正确
        String inputOldPwd=userRePasswordDTO.getOldPassword();
        String inputOldPwdEncrypt=DigestUtils.md5DigestAsHex(inputOldPwd.getBytes());
        if (!newuser.getPassword().equals(inputOldPwdEncrypt)) {
            throw new PasswordEditFailedException("旧密码错误");
        }
        String inputNewPwd=userRePasswordDTO.getNewPassword();
        String inputNewPwdEncrypt=DigestUtils.md5DigestAsHex(inputNewPwd.getBytes());
        if (newuser.getPassword().equals(inputNewPwdEncrypt)) {
            throw new PasswordEditFailedException("新密码不能与旧密码相同，请重新设置");
        }

        // 3. 最后更新新密码
        newuser.setPassword(inputNewPwdEncrypt);
        userMapper.update(newuser);
    }

    /**
     * 根据Id查询用户
     * @param id
     * @return
     */
    @Override
    public User getByID(Long id) {
        UserTypeEnum userTypeEnum = BaseContext.getCurrentPrimaryUserEnum();
        if (userTypeEnum == UserTypeEnum.ADMIN) {
            User user = userMapper.getById(id);
            if (user == null) {
                throw new AccountNotFoundException("用户不存在");
            }
            user.setPassword("******");
            return user;
        }
       throw new UserException("无权限查询");
    }
}
