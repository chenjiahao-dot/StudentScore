package org.example.Demo.Server;

import org.example.Demo.DTO.UserDTO.LoginRequestUserDTO;
import org.example.Demo.DTO.UserDTO.UpdateUserStatus;
import org.example.Demo.DTO.UserDTO.UserPageQueryDTO;
import org.example.Demo.DTO.UserDTO.UserRePasswordDTO;
import org.example.Demo.DTO.WarehouseDTO.UserSignInDTO;
import org.example.Demo.Result.PageResult;
import org.example.Demo.Result.Result;
import org.example.Demo.VO.UserVO.UserMessageVO;

public interface UserService {
    void signIn(UserSignInDTO userSignInDTO);


    String login(LoginRequestUserDTO loginRequestUserDTO);

    PageResult<UserMessageVO> pageUserMessage(UserPageQueryDTO pageQueryDTO);

    void rePassword(UserRePasswordDTO userRePasswordDTO);

    Result updateUserStatus(UpdateUserStatus updateUserStatus);
}
