package org.example.Demo.Common;

public class CourseException extends RuntimeException {
    public CourseException(String msg) {super(msg);}
    public CourseException(){super("课程处理异常");}
}
