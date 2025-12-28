package com.example.Exception;


import org.example.Demo.Common.BaseException;

/**
 * @Author: Illya
 * @Date: 2024/5/16 下午3:22
 */
public class SortFieldException extends BaseException {
    public SortFieldException() {
        super("排序字段错误");
    }

    public SortFieldException(String msg) {
        super(msg);
    }
}
