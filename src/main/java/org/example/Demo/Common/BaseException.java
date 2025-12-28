package org.example.Demo.Common;

/**
 * 业务异常
 */
public class BaseException extends RuntimeException {

    public BaseException() {
        super("业务处理异常");
    }

    public BaseException(String msg) {
        super(msg);
    }

}
