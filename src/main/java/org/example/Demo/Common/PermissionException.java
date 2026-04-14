package org.example.Demo.Common;

public class PermissionException extends RuntimeException {
    public PermissionException(String msg) {
        super(msg);
    }
    public PermissionException() {
        super("权限处理异常");
    }
}
