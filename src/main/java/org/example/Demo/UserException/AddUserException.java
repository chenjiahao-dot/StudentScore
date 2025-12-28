package org.example.Demo.UserException;


import org.example.Demo.Common.BaseException;

/**
 * 新增用户异常
 *
 * @author Illya
 */
public class AddUserException extends BaseException {
    public AddUserException() {
        super("新增用户异常");
    }

    public AddUserException(String msg) {
        super(msg);
    }
}
