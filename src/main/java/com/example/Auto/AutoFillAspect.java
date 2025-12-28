package com.example.Auto;


import com.example.Context.BaseContext;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.Demo.Enummerate.PrimaryRoleEnum;
import org.example.Demo.annotation.AutoFill;
import org.example.Demo.enumeration.OperationType;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 公共字段赋值值切面类
 *
 * @author Illya
 */

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    /**
     * 切入点
     * 切入所有Mapper中的新增和更新操作
     */
    @Pointcut("execution(* org.example.Demo.mapper.*.*(..)) && @annotation(org.example.Demo.annotation.AutoFill)")
    public void autoFillPointCut() {
    }

    /**
     * 进行公共字段赋值
     *
     * @param joinPoint
     * @throws ReflectiveOperationException
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) throws ReflectiveOperationException {
        log.info("开始公共字段填充");
        //方法签名对象
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //获得方法上的注解对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        //获取数据库操作
        OperationType operationType = autoFill.value();
        Object[] args = joinPoint.getArgs();
        if (args[0] == null) {
            return;
        }
        Object object = args[0];
        Field[] fields = object.getClass().getDeclaredFields();
        //获取当前时间
        Date date = new Date();
        //获取修改者ID
        Long currentId = BaseContext.getCurrentId();
        String name = BaseContext.getCurrentName();
        PrimaryRoleEnum createPrimaryRoleEnum = BaseContext.getCurrentPrimaryRoleEnum();
        //判断数据库操作
        if (operationType == OperationType.INSERT || operationType == OperationType.UPDATE) {
            for (Field field : fields) {
                String fieldName = field.getName();
                if ("updateUser".equals(fieldName)) {
                    Method setUpdateUser = object.getClass().getDeclaredMethod("setUpdateUser", Long.class);
                    setUpdateUser.invoke(object, currentId);
                }

                if ("updateTime".equals(fieldName)) {
                    Method setUpdateTime = object.getClass().getDeclaredMethod("setUpdateTime", Date.class);
                    setUpdateTime.invoke(object, date);
                }

                if ("updateUserName".equals(fieldName)) {
                    Method setUpdateUserName = object.getClass().getDeclaredMethod("setUpdateUserName", String.class);
                    setUpdateUserName.invoke(object, name);
                }
                if (operationType == OperationType.INSERT) {
                    if ("createUser".equals(fieldName)) {
                        Method setCreateUser = object.getClass().getDeclaredMethod("setCreateUser", Long.class);
                        setCreateUser.invoke(object, currentId);
                    }

                    if ("createTime".equals(fieldName)) {
                        Method setCreateTime = object.getClass().getDeclaredMethod("setCreateTime", Date.class);
                        setCreateTime.invoke(object, date);
                    }

                    if ("createUserName".equals(fieldName)) {
                        Method setCreateUserName = object.getClass().getDeclaredMethod("setCreateUserName", String.class);
                        setCreateUserName.invoke(object, name);
                    }

                    if ("createUserPrimaryRole".equals(fieldName)) {
                        Method setCreateUserName = object.getClass().getDeclaredMethod("setCreateUserPrimaryRole", PrimaryRoleEnum.class);
                        setCreateUserName.invoke(object, createPrimaryRoleEnum);
                    }
                }
            }
        }
    }
}
