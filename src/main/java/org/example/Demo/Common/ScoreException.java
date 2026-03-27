package org.example.Demo.Common;

public class ScoreException extends RuntimeException {
    public ScoreException(String msg){super(msg);}
    public ScoreException(){super("成绩处理异常");}
}
