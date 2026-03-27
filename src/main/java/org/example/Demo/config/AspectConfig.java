package org.example.Demo.config;


import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Illya
 * 日志切面配置类
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class AspectConfig {
    @Around("!execution(* org.example.Demo.controller.User.UserController.login(..)) && @within(org.springframework.web.bind.annotation.RestController)" +
            "||@within(org.springframework.stereotype.Controller)")
    public Object after(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("-------------------------------------------------------------------------------------------------------");
        log.info("请求地址:{},", Optional.ofNullable(request.getRequestURI()).orElse(null));
        log.info("请求方式:{}", request.getMethod());
        log.info("请求类方法:{}", joinPoint.getSignature());
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed(joinPoint.getArgs());
        long end = System.currentTimeMillis();
        log.info("执行耗时:{}", end - start);
        return result;
    }


}
