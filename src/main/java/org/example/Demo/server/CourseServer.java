package org.example.Demo.server;

import com.common.Result.PageResult;
import com.common.Result.Result;
import org.example.Demo.DTO.Course.AddCourseDTO;
import org.example.Demo.DTO.Course.DeleteCourseDTO;
import org.example.Demo.DTO.Course.ListCourseDTO;
import org.example.Demo.DTO.Course.UpdateCourseDTO;
import org.example.Demo.VO.Course.courseListAllVO;

public interface CourseServer {
    void addCourse(AddCourseDTO addCourseDTO);

    PageResult<courseListAllVO> listCourse(ListCourseDTO listCourseDTO);

    Result deleteCourse(DeleteCourseDTO deleteCourseDTO);

    void updateCourse(UpdateCourseDTO updateCourseDTO);
}
