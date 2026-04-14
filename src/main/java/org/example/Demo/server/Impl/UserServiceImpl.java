package org.example.Demo.server.Impl;

import com.alibaba.fastjson.JSON;
import com.common.Context.BaseContext;
import com.common.Result.PageResult;
import com.common.Result.Result;
import com.common.Util.JwtUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.google.common.base.CaseFormat;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.Id;
import org.example.Demo.Common.BaseException;
import org.example.Demo.Common.ClassException;
import org.example.Demo.Common.PasswordEditFailedException;
import org.example.Demo.Common.UserException;
import org.example.Demo.DTO.User.*;
import org.example.Demo.UserException.AccountNotFoundException;
import org.example.Demo.VO.Semester.semesterListAllVO;
import org.example.Demo.VO.User.UserVO;
import org.example.Demo.entity.User;
import org.example.Demo.enummerate.OrderTypeEnum;
import org.example.Demo.enummerate.UserTypeEnum;
import org.example.Demo.server.UserService;
import org.example.Demo.UserException.AddUserException;
import org.example.Demo.entity.newUser;
import org.example.Demo.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    @Resource
    private JwtUtil jwtUtil;
    @Value("${file.upload-path}")
    private String uploadPath;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用户注册
     * @param userSignInDTO
     */
    @Override
    public void signIn(UserSignInDTO userSignInDTO, MultipartFile file) throws  Exception {
        newUser user = userMapper.getByMail(userSignInDTO.getMail());
        newUser classId = userMapper.getClassById(userSignInDTO.getClassId());
        if (user != null) {
            throw new AddUserException("用户已存在");
        }
        if (classId == null) {
            throw new ClassException("班级不存在");
        }
        String avatarFileName;
        if (file != null && !file.isEmpty()) {
            // 3.1 获取文件后缀
            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

            // 3.2 生成唯一文件名（UUID，避免重名覆盖）
            avatarFileName = UUID.randomUUID() + suffix;

            // 3.3 确保目录存在，不存在则创建
            File folder = new File(uploadPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            file.transferTo(new File(uploadPath + avatarFileName));
        }else {
            avatarFileName = "default.png";
        }

            newUser newUser = new newUser();
            BeanUtils.copyProperties(userSignInDTO, newUser);
            newUser.setPassword(DigestUtils.md5DigestAsHex(userSignInDTO.getPassword().getBytes()));
            newUser.setMobile(userSignInDTO.getMobile());
            newUser.setUserName(newUser.getName());
            newUser.setImageAddress(avatarFileName);
            newUser.setSexEnum(userSignInDTO.getSexEnum());
            newUser.setUserTypeEnum(userSignInDTO.getUserTypeEnum());
            newUser.setSexEnum(userSignInDTO.getSexEnum());
            newUser.setClassId(userSignInDTO.getClassId());
            newUser.setMail(userSignInDTO.getMail());
            try {
                userMapper.insertUser(newUser);
            } catch (Exception e) {
                throw new AddUserException("新增用户失败，原因:" + e.getMessage());
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
        String account = loginRequestUserDTO.getMail();
        String inputPwdEncrypted = DigestUtils.md5DigestAsHex(loginRequestUserDTO.getPassword().getBytes());
        Boolean rememberMe = loginRequestUserDTO.getRememberMe();
        if (rememberMe == null) {
            rememberMe = false;
        }
        // 1. 根据邮箱或手机号查询用户
        if (account != null && account.matches("^1[3-9]\\d{9}$")) {
            newuser = userMapper.selectByMobile(account);
        } else {
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

        String token;
        long expireHours;
        if (rememberMe) {
            // 勾选记住密码 → 调用【新方法】，传7天
            expireHours = 7 * 24;
            token = jwtUtil.generateToken(newuser.getId(), expireHours);
        } else {
            // 不勾选 → 调用【原方法】，走默认2小时
            expireHours = 2; // 对应你原tokenExpiration的2小时
            token = jwtUtil.generateToken(newuser.getId());
        }


        String redisKey = "login:token:" + newuser.getId();

        String userJson = JSON.toJSONString(newuser);

        String role=newuser.getUserTypeEnum().toString();

        stringRedisTemplate.opsForValue().set(redisKey, userJson, expireHours, TimeUnit.HOURS);
        // 存储token与用户ID的映射，用于登出/校验
        stringRedisTemplate.opsForValue().set("login:token:" + token, String.valueOf(newuser.getId()), 2, TimeUnit.HOURS);
        // 从用户对象中获取角色
        stringRedisTemplate.opsForValue().set("login:role:"+token, role,2, TimeUnit.HOURS);

//        List<Long> roleIds = userRoleMapper.selectRoleIdsByUserId(newuser.getId());
//        // 3.2 根据角色ID查询权限ID
//        List<Long> permIds = rolePermissionMapper.selectPermIdsByRoleIds(roleIds);
//        // 3.3 根据权限ID查询权限URL列表
//        List<String> permUrls = permissionMapper.selectUrlsByIds(permIds);


        // 5. 返回token
        return token;

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

    /**
     * 分页查询所有用户
     * @param pageQueryDTO
     * @return
     */
    @Override
    public PageResult<UserVO> pageUser(UserPageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        String sortField = "name";
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
        List<UserVO> list = userMapper.pageUser(pageQueryDTO);
        Page<UserVO> page = (Page<UserVO>) list;

        return new PageResult<>(page.getTotal(), page.getResult());
    }

    /**
     * 添加用户角色
     * @param addUserRoleDTO
     * @return
     */
    @Override
    public Result AddRole(AddUserRoleDTO addUserRoleDTO) {
        Result result = new Result<>();
        List<Long> userList = new ArrayList<>();
        List<Long> userIdList = userMapper.getRole(addUserRoleDTO.getRoleId());
        for (Long i : addUserRoleDTO.getUserIdList()) {
            if (!userIdList.contains(i)) {
                userList.add(i);
            }
        }
        if (!userList.isEmpty()) {
            addUserRoleDTO.setUserIdList(userList);
            userMapper.AddRole(addUserRoleDTO);
            result.setMsg("添加" + userList + "成功,id如下");
            result.setCode(1);
            List<Long> IdByUserRoles = userMapper.getIdByRolePermissions(addUserRoleDTO);
            result.setData(IdByUserRoles);
        } else {
            result.setMsg("添加失败,重复权限id如下");
            addUserRoleDTO.getUserIdList().retainAll(userIdList);
            result.setData(addUserRoleDTO.getUserIdList());
        }
        return result;
    }
}
