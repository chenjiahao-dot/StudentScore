package com.common.Util;


import com.common.Context.BaseContext;
import com.common.Exception.SortFieldException;
import com.github.pagehelper.page.PageMethod;
import org.example.Demo.Common.BaseException;

import org.example.Demo.UserException.UserDirectionException;
import org.example.Demo.enummerate.OrderTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.google.common.base.CaseFormat;

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
        // 1. 处理默认值
        String finalSortField = "id"; // 你的默认排序字段
        OrderTypeEnum finalOrderType = OrderTypeEnum.DESC;

        if (sortField != null) {
            if (!filedNameInObject(table, sortField)) {
                throw new SortFieldException("不支持的排序字段");
            }
            finalSortField = sortField;
        }
        if (orderType != null) {
            finalOrderType = orderType;
        }

        // 2. 驼峰转下划线（和数据库字段对应）
        String dbSortField = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, finalSortField);

        // 3. 只调用一次 orderBy！去掉表名前缀！
        PageMethod.orderBy(dbSortField + " " + finalOrderType);
    }
}
