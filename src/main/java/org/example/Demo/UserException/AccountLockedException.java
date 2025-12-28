package org.example.Demo.UserException;


import org.example.Demo.Common.BaseException;

/**
 * 账号被锁定异常
 *
 * @author Illya
 */
public class AccountLockedException extends BaseException {

    public AccountLockedException() {
        super("账号被锁定");
    }

    public AccountLockedException(String msg) {
        super(msg);
    }

}
