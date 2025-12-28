package org.example.Demo.UserException;


import org.example.Demo.Common.BaseException;

/**
 * 密码错误异常
 *
 * @author Illya
 */
public class PasswordErrorException extends BaseException {

    public PasswordErrorException() {
        super("密码错误");
    }

    public PasswordErrorException(String msg) {
        super(msg);
    }

}
