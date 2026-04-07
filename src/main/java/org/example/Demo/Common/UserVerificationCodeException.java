package org.example.Demo.Common;



/**
 * @Author: Illya
 * @Date: 2024/5/16 下午2:04
 * 验证码相关异常
 */
public class UserVerificationCodeException extends BaseException {
    public UserVerificationCodeException() {
        super("验证码验证失败");
    }

    public UserVerificationCodeException(String msg) {
        super(msg);
    }
}
