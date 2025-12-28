package org.example.Demo.UserException;


import org.example.Demo.Common.BaseException;

/**
 * @Author: Illya
 * @Date: 2024/5/14 上午10:08
 * 用户方向错误异常
 */
public class UserDirectionException extends BaseException {

    public UserDirectionException() {
        super("方向不符合");
    }

    public UserDirectionException(String msg) {
        super(msg);
    }
}
