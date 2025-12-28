package com.example.Util;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.Context.BaseContext;
import com.example.Exception.SortFieldException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.example.Demo.Common.BaseException;
import org.example.Demo.DTO.RoleDTO.RolePageQueryDTO;
import org.example.Demo.Enummerate.DirectionEnum;
import org.example.Demo.Enummerate.UniqueErrorEnum;
import org.example.Demo.OrderTypeEnum.OrderTypeEnum;
import org.example.Demo.UserException.UserDirectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.google.common.base.CaseFormat;

import javax.management.relation.Role;

/**
 * @Author: Illya
 * @Date: 2024/5/7 下午1:08
 */
public class PublicUtil {

    private static final Logger log = LoggerFactory.getLogger(PublicUtil.class);

    /**
     * 列表去重(使用hashset特性去除列表中重复元素)
     *
     * @param list 需要去重的列表
     * @return list 去重完成的列表
     */
    public static List removeListDuplicates(List list) {
        //通过列表转hashset再转列表实现去重
        HashSet map = new HashSet<>(list);
        list.clear();
        list.addAll(map);
        return list;
    }

    /**
     * 判断对象是否有空值属性
     *
     * @param object 需要判断的对象
     * @return Boolean  判断结果:true or false
     */
    public static boolean isAllFieldNull(Object object) {
        //通过反射查看是否有空值
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (field.get(object) == null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new BaseException("反射错误");
            }
        }
        return true;
    }

    /**
     * 判断属性名是否存在类中
     *
     * @param object    需要判断的类
     * @param filedName 需要判断的属性名
     * @return 判断结果:true or false
     */
    public static Boolean filedNameInObject(Object object, String filedName) {
        //通过反射判断属性名是否存在类中
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals(filedName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 基于PageHelper分页插件实现分页查询自定义工具
     *
     * @param table     传入一个需要排序的实体类对象
     * @param sortField 传入一个需要排序的字段
     * @param orderType 传入一个需要的排序方式
     *                  <p>
     *                  无返回值,设置PageHelper自定义排序方式(多表可用,多表时排序字段为table的sortField字段)
     */
    public static void getPageHelper(Object table, String sortField, OrderTypeEnum orderType) {
        getPageHelper(table, sortField, orderType, null, null);
    }

    public static void getPageHelper(Object table, String sortField, OrderTypeEnum orderType, String defaultSortField, OrderTypeEnum defaultOrderType) {
        //判断排序字段是否为数据表中字段
        if (sortField != null) {
            if (!filedNameInObject(table, sortField)) {
                throw new SortFieldException("不支持的排序字段");
            }
        }
        if (defaultSortField == null) {
            defaultSortField = "id";
        }
        if (defaultOrderType == null) {
            defaultOrderType = OrderTypeEnum.DESC;
        }
        //设置表名
        String tableName = (CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, table.getClass().getSimpleName())) + ".";
        //设置默认排序为id
        PageMethod.orderBy(tableName + defaultSortField + " " + defaultOrderType);
        //判断是否自定义排序字段
        if (sortField == null) {
            sortField = defaultSortField;
        }
        //判断是否自定义排序方式
        if (orderType == null) {
            orderType = OrderTypeEnum.DESC;
        }
        //设置排序
        PageMethod.orderBy(tableName + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, sortField)
                + " " + orderType);
    }

    /**
     * 获取分页查询方向列表
     *
     * @param directionEnum DTO中传入的方向
     * @param all           方向为空时是否返回全部,true:如果传入方向为空则查询所有数据false:传入方向为空时返回所有当前用户方向数据
     * @return 转换成分页查询所需使用的方向列表
     */
    public static List<DirectionEnum> getPageQueryDirectionEnumList(DirectionEnum directionEnum, Boolean all) {
        List<DirectionEnum> directionEnumList;
        if (all) {
            directionEnumList = null;
        } else {
            directionEnumList = BaseContext.getCurrentDirectionList();
//            if (!directionEnumList.contains(DirectionEnum.BASICS)) {
//                directionEnumList.add(DirectionEnum.BASICS);
//            }
        }
        if (directionEnum != null) {
            if (directionEnumList == null || directionEnumList.contains(directionEnum)) {
                directionEnumList = new ArrayList<>() {{
                    add(directionEnum);
                }};
            } else {
                throw new UserDirectionException("方向不符合");
            }
        }
        return directionEnumList;
    }

    /**
     * JSON转换方向列表
     *
     * @param directionString 方向列表JSON字符串
     * @return 方向列表
     */
    public static List<DirectionEnum> directionStringToEnumList(String directionString) {
        return JSON.parseArray(directionString, DirectionEnum.class);
    }

    /**
     * 方向列表转JSON
     *
     * @param directionEnumList 方向列表
     * @return 方向列表JSON字符串
     */
    public static String directionEnumToString(List<DirectionEnum> directionEnumList) {
        return JSON.toJSONString(directionEnumList);
    }

    /**
     * Excel导入用户异常处理
     *
     * @param e         异常信息
     * @param errorList 错误存储列表
     * @param errorData 错误数据
     */
    public static void excelInputUserErrorDispose(Exception e, List<String> errorList, String errorData) {
        Throwable eCause = e.getCause();
        String errorMessage = e.getMessage();
        log.error("发生异常:", e);
        //判断错误是否为唯一键或唯一组合键错误
        if (eCause != null) {
            errorMessage = eCause.getMessage();
            //判断错误是否为唯一键或唯一组合键错误
            if (errorMessage != null && errorMessage.contains(UniqueErrorEnum.UNIQUE_ERROR)) {
                String[] s = errorMessage.split("'");
                errorList.add(errorData + "新增失败,原因:" + UniqueErrorEnum.getUniqueErrorEnumByKey(s[s.length - 1]).getMessage());
                return;
            }
        }
        errorList.add(errorData + "新增失败,原因:" + errorMessage);
    }

    public static Boolean conformToDirection(DirectionEnum directionEnum) {
        List<DirectionEnum> currentDirectionList = BaseContext.getCurrentDirectionList();
        return currentDirectionList.contains(directionEnum);
    }

}
