package org.example.Demo.server;

import com.common.Result.PageResult;
import com.common.Result.Result;
import org.example.Demo.DTO.User.*;
import org.example.Demo.VO.User.UserVO;
import org.example.Demo.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void signIn(UserSignInDTO userSignInDTO, MultipartFile file)throws Exception;


    String login(LoginRequestUserDTO loginRequestUserDTO);

    void rePassword(UserRePasswordDTO userRePasswordDTO);


    User getByID(Long id);

    PageResult<UserVO> pageUser(UserPageQueryDTO pageQueryDTO);

    Result AddRole(AddUserRoleDTO addUserRoleDTO);
}
