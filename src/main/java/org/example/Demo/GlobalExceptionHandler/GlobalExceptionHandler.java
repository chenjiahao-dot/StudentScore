package org.example.Demo.GlobalExceptionHandler;

import com.common.Result.Result;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.Common.ScoreException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public Result handleBaseException(BaseException e) {
        return Result.success(e.getMessage());
    }

    // 捕获越界异常，也返回友好提示
    @ExceptionHandler(StringIndexOutOfBoundsException.class)
    public Result handleStringIndexOutOfBoundsException(StringIndexOutOfBoundsException e) {
        return Result.success("班级名称长度不足，无法截取年级");
    }
    @ExceptionHandler(Exception.class)
    public Result handlerAll(Exception e) {
        return Result.success("系统异常：" + e.getMessage());
    }


}
