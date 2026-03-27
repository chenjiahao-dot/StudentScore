package org.example.Demo.server.Impl;

import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.ClassException;
import org.example.Demo.Common.CourseException;
import org.example.Demo.DTO.Course.AddCourseDTO;
import org.example.Demo.DTO.Course.DeleteCourseDTO;
import org.example.Demo.DTO.Course.ListCourseDTO;
import org.example.Demo.DTO.Course.UpdateCourseDTO;
import org.example.Demo.VO.Course.courseListAllVO;
import org.example.Demo.entity.course;
import org.example.Demo.mapper.CourseMapper;
import org.example.Demo.server.CourseServer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourseImpl implements CourseServer {
    private final CourseMapper courseMapper;

    /**
     * 添加课程
     * @param addCourseDTO
     */
    @Override
    @Transactional
    public void addCourse(AddCourseDTO addCourseDTO) {
        course course = courseMapper.selectCourseById(addCourseDTO.getCourseName());
        if (course != null) {
            throw new ClassException("课程已存在");
        }
        course = new course();
        BeanUtils.copyProperties(addCourseDTO, course);
        courseMapper.insertCourse(course);
    }

    /**
     * 分页查询课程
     * @param listCourseDTO
     * @return
     */
    @Override
    public PageResult<courseListAllVO> listCourse(ListCourseDTO listCourseDTO) {
        PageHelper.startPage(listCourseDTO.getPage(), listCourseDTO.getPageSize());
        Page<courseListAllVO> page = courseMapper.pageQuerycourse(listCourseDTO);
        long total = page.getTotal();
        List<courseListAllVO> roles = page.getResult();
        return new PageResult<>(total, roles);
    }

    /**
     * 批量删除课程
     * @param deleteCourseDTO
     * @return
     */
    @Override
    public Result deleteCourse(DeleteCourseDTO deleteCourseDTO) {
        Result result = new Result();
        List<Long> id = deleteCourseDTO.getId();
        for (int i = 0; i < id.size(); i++) {
            if (courseMapper.selectCourseId(id.get(i)) != 0) {
                courseMapper.deleteCourseId(id.get(i));
                result.setMsg("删除成功");
                result.setCode(1);
            } else {
                result.setMsg("删除失败");
                result.setCode(0);
            }
        }
        return result;
    }

    /**
     * 修改课程信息
     * @param updateCourseDTO
     */
    @Override
    public void updateCourse(UpdateCourseDTO updateCourseDTO) {
        String teacher=updateCourseDTO.getTeacher();
        String credit=updateCourseDTO.getCredit();
        //判断teacher和credit是否位空字段
        if (teacher == null || teacher.isBlank()
                || credit == null || credit.isBlank()) {
            throw new CourseException("请填写完整信息");
        }

        //credit必须是数字
        try {
            Integer.parseInt(credit);
        } catch (NumberFormatException e) {
            throw new CourseException("学分必须是数字");
        }
        course course = new course();
        BeanUtils.copyProperties(updateCourseDTO, course);
        courseMapper.updateCourse(course);
    }
}
