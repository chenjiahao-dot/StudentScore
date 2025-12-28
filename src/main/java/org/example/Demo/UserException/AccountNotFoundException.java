package org.example.Demo.UserException;


import org.example.Demo.Common.BaseException;

/**
 * @Author: Illya
 * 账号不存在异常
 */
public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException() {
        super("账号不存在");
    }

    public AccountNotFoundException(String msg) {
        super(msg);
    }

}
