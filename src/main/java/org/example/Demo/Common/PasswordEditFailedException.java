package org.example.Demo.Common;



/**
 * 密码修改失败异常
 */
public class PasswordEditFailedException extends BaseException {

    public PasswordEditFailedException(String msg) {
        super(msg);
    }

    public PasswordEditFailedException() {
        super("密码修改失败");
    }
}
