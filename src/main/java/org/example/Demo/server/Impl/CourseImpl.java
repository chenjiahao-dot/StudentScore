package org.example.Demo.server.Impl;

import com.common.Context.BaseContext;
import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.ClassException;
import org.example.Demo.Common.CourseException;
import org.example.Demo.DTO.Course.AddCourseDTO;
import org.example.Demo.DTO.Course.DeleteCourseDTO;
import org.example.Demo.DTO.Course.ListCourseDTO;
import org.example.Demo.DTO.Course.UpdateCourseDTO;
import org.example.Demo.VO.Course.courseListAllVO;
import org.example.Demo.entity.Course;
import org.example.Demo.enummerate.OrderTypeEnum;
import org.example.Demo.enummerate.UserTypeEnum;
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
        Course course = courseMapper.selectCourseById(addCourseDTO.getCourseName());
        Course teacher = courseMapper.selectTeacherById(addCourseDTO.getTeacherName());
        if (course != null) {
            throw new ClassException("课程已存在");
        } else if (teacher==null) {
            throw new ClassException("老师不存在，无法添加课程！");
        }
        course = new Course();
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
        UserTypeEnum userTypeEnum=BaseContext.getCurrentPrimaryUserEnum();
        if (userTypeEnum==UserTypeEnum.ADMIN) {
            PageHelper.startPage(listCourseDTO.getPage(), listCourseDTO.getPageSize());
            String sortField = "id";
            String sortOrder = "desc";
            // 如果前端传了字段 → 替换
            if (StrUtil.isNotBlank(listCourseDTO.getSortField())) {
                sortField = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, listCourseDTO.getSortField());
            }
            // 如果前端传了 ASC → 改成正序
            if (listCourseDTO.getOrderType() == OrderTypeEnum.ASC) {
                sortOrder = "asc";
            }
            PageHelper.orderBy(sortField + " " + sortOrder);
            List<courseListAllVO> list = courseMapper.pageQueryCourse(listCourseDTO);
            Page<courseListAllVO> page = (Page<courseListAllVO>) list;

            return new PageResult<>(page.getTotal(), page.getResult());
        }else{
            throw new CourseException("无权限查询课程，请找管理员！");
        }

    }

    /**
     * 批量删除课程
     * @param deleteCourseDTO
     * @return
     */
    @Override
    public Result deleteCourse(DeleteCourseDTO deleteCourseDTO) {
        UserTypeEnum userTypeEnum= BaseContext.getCurrentPrimaryUserEnum();
        if (userTypeEnum.equals(UserTypeEnum.ADMIN)) {
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
        }else{
            throw new CourseException("无权限删除课程，请找管理员！");
        }

    }

    /**
     * 修改课程信息
     * @param updateCourseDTO
     */
    @Override
    public void updateCourse(UpdateCourseDTO updateCourseDTO) {
        UserTypeEnum userTypeEnum= BaseContext.getCurrentPrimaryUserEnum();
        if (userTypeEnum.equals(UserTypeEnum.ADMIN)) {
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
            Course course = new Course();
            BeanUtils.copyProperties(updateCourseDTO, course);
            courseMapper.updateCourse(course);
        }else {
            throw new CourseException("无权限修改课程信息，请找管理员！");
        }
        }

}
