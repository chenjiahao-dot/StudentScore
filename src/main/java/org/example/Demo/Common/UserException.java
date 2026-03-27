package org.example.Demo.Common;

public class UserException extends RuntimeException {
    public UserException(String msg) {super(msg);}
    public UserException(){super("用户处理异常");}
}
