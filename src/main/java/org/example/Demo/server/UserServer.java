package org.example.Demo.server;

import org.example.Demo.DTO.User.LoginRequestUserDTO;
import org.example.Demo.DTO.User.UserRePasswordDTO;
import org.example.Demo.DTO.User.UserSignInDTO;
import org.example.Demo.entity.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserServer {
    void signIn(UserSignInDTO userSignInDTO, MultipartFile file)throws Exception;


    String login(LoginRequestUserDTO loginRequestUserDTO);

    void rePassword(UserRePasswordDTO userRePasswordDTO);


    User getByID(Long id);
}
