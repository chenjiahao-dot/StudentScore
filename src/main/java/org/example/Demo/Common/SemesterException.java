package org.example.Demo.Common;

public class SemesterException extends RuntimeException {
    public SemesterException(String msg){super(msg);}
    public SemesterException(){super("学期处理异常");}
}
