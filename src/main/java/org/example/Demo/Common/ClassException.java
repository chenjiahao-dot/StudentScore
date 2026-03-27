package org.example.Demo.Common;

public class ClassException extends RuntimeException {
    public ClassException(String msg) {super(msg);}
    public ClassException(){super("班级处理异常");}
}
