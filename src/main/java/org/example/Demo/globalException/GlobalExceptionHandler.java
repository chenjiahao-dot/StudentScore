package org.example.Demo.globalException;


import com.common.Result.Result;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Illya
 * @Date: 2024/5/3 上午8:13
 * 全局异常处理
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
        // 处理业务异常
        public Map<String, String> handleRuntimeException(RuntimeException e) {
            Map<String, String> result = new HashMap<>();
            result.put("code", "400");
            result.put("message", e.getMessage());
            return result;
        }

        // 处理参数校验异常（如密码为空、格式错误）
        @ExceptionHandler(org.springframework.validation.BindException.class)
        public Map<String, String> handleBindException(org.springframework.validation.BindException e) {
            Map<String, String> result = new HashMap<>();
            result.put("code", "400");
            result.put("message", e.getBindingResult().getFieldError().getDefaultMessage());
            return result;
        }
//        @ExceptionHandler(BaseException.class)
//        public Result handleBaseException(BaseException e) {
//        return Result.success(e.getMessage());
//    }
//
//        // 捕获越界异常，也返回友好提示
//        @ExceptionHandler(StringIndexOutOfBoundsException.class)
//        public Result handleStringIndexOutOfBoundsException(StringIndexOutOfBoundsException e) {
//        return Result.success("班级名称长度不足，无法截取年级");
//        }
//        @ExceptionHandler(Exception.class)
//        public Result handlerAll(Exception e) {
//        return Result.success("系统异常：" + e.getMessage());
//    }
}
