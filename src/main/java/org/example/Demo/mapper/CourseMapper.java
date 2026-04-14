package org.example.Demo.mapper;

import com.common.enumeration.OperationType;
import com.github.pagehelper.Page;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.example.Demo.DTO.Course.ListCourseDTO;
import org.example.Demo.VO.Course.CourseVO;
import org.example.Demo.VO.Course.CourseListAllVO;
import org.example.Demo.annotation.AutoFill;
import org.example.Demo.entity.Course;

import java.util.List;

public interface CourseMapper {
    /**
     * 查询课程是否存在
     * @param courseName
     * @return
     */
    @Select("select course_name from course where course_name=#{courseName} ")
    Course selectCourseById(String courseName);

    /**
     * 添加课程
     * @param course
     */
    @Insert("insert into course(course_name,teacher_name)VALUES (#{courseName},#{teacherName} )")
    void insertCourse(Course course);

    /**
     * 分页查询所有班级
     * @param listCourseDTO
     * @return
     */
    @Select("select * from course")
    Page<CourseListAllVO> pageQueryCourse(ListCourseDTO listCourseDTO);

    /**
     * 统计指定id的课程数量
     * @param aLong
     * @return
     */
    @Select("select count(*) from course where id=#{id}")
    int selectCourseId(Long aLong);

    /**
     * 批量删除课程
     * @param aLong
     */
    @Delete("delete from course where id=#{id}")
    void deleteCourseId(Long aLong);
    /**
     * 对课程进行修改
     *
     * @param course
     */
    @AutoFill(OperationType.UPDATE)
    void updateCourse(Course course);

    /**
     * 查询老师是否存在才能添加课程
     * @param teacher
     * @return
     */
    @Select("select name from user where name=#{teacherName} ")
    Course selectTeacherById(String teacher);

    /**
     * 查询自己任教的课程
     * @param userId
     * @return
     */
    List<CourseVO> getMyTeachCourses(@Param("userId") Long userId);

    List<CourseVO> getAllCourses(Long userId);
}
