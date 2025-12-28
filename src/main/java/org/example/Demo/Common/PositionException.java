package org.example.Demo.Common;

public class PositionException extends RuntimeException {
    public PositionException(String msg) {
        super(msg);
    }
    public PositionException() {
        super("岗位处理异常");
    }
}
