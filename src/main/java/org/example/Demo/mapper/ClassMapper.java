package org.example.Demo.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.example.Demo.DTO.Class.ListClassDTO;
import org.example.Demo.VO.Class.classListAllVO;
import org.example.Demo.entity.Class;

public interface ClassMapper {
    /**
     * 添加班级
     * @param clazz
     */
    @Insert("insert into classes (id,class_name,grade)VALUES (#{id},#{className},#{grade} )")
    void insertClass(Class clazz);

    /**
     * 查询班级是否存在
     * @param className
     * @return
     */
    @Select("select class_name from classes where class_name=#{className} ")
    Class getByClass(String className);

    /**
     * 查询所有班级信息
     * @param listClassDTO
     * @return
     */
    @Select("select * from classes ")
    Page<classListAllVO> pageQueryclass(ListClassDTO listClassDTO);

    /**
     * 统计指定id的班级数量
     * @param aLong
     * @return
     */
    @Select("select count(*) from classes where id=#{id}")
    int selectClassesId(Long aLong);
    /**
     * 批量删除班级
     * @param aLong
     */
    @Delete("delete from classes where id=#{id}")
    void deleteClassesId(Long aLong);

    /**
     * 修改班级
     * @param clazz
     */
    void updateClass(Class clazz);
}
