package org.example.Demo.server;

import org.example.Demo.DTO.User.LoginRequestUserDTO;
import org.example.Demo.DTO.User.UserRePasswordDTO;
import org.example.Demo.DTO.User.UserSignInDTO;
import org.example.Demo.entity.User;

public interface UserServer {
    void signIn(UserSignInDTO userSignInDTO);


    String login(LoginRequestUserDTO loginRequestUserDTO);

    void rePassword(UserRePasswordDTO userRePasswordDTO);


    User getByID(Long id);
}
