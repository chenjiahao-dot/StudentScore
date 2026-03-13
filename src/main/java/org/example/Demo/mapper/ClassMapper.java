package org.example.Demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.example.Demo.entity.Class.Class;

public interface ClassMapper {
    @Insert("insert into classes (id,class_name,grade)VALUES (#{id},#{className},#{grade} )")
    void insertClass(Class clazz);
}
