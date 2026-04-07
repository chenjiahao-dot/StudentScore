package org.example.Demo.Common;


/**
 * @Author: Illya
 * @Date: 2024/5/17 上午7:44
 * 手机不存在异常
 */
public class MobileNotFoundException extends BaseException {
    public MobileNotFoundException(String msg) {
        super(msg);
    }

    public MobileNotFoundException() {
        super("手机号不存在");
    }

}
