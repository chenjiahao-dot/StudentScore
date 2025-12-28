package org.example.Demo.Common;

public class AddUserRoleException extends RuntimeException {
    public AddUserRoleException(String msg) {
        super(msg);
    }
    public AddUserRoleException() {
        super("用户角色添加失败");
    }
}
