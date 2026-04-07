package org.example.Demo.Common;



/**
 * @Author: Illya
 * @Date: 2024/5/16 下午2:06
 * 邮箱不存在异常
 */
public class EmailNotFoundException extends BaseException {
    public EmailNotFoundException() {
        super("邮箱不存在");
    }

    public EmailNotFoundException(String msg) {
        super(msg);
    }
}
