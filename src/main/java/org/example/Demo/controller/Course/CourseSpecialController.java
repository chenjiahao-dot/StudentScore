package org.example.Demo.controller.Course;

import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.DTO.Course.AddCourseDTO;
import org.example.Demo.DTO.Course.DeleteCourseDTO;
import org.example.Demo.DTO.Course.ListCourseDTO;
import org.example.Demo.DTO.Course.UpdateCourseDTO;
import org.example.Demo.DTO.Score.PageQueryClassStudentScoreDTO;
import org.example.Demo.VO.Course.CourseVO;
import org.example.Demo.VO.Course.CourseListAllVO;
import org.example.Demo.VO.Score.ClassStudentScoreVO;
import org.example.Demo.server.CourseService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@Slf4j
@Tag(name = "课程管理")
@CrossOrigin
@RequiredArgsConstructor
public class CourseSpecialController {
    private final CourseService courseService;
    /**
     * 新增课程
     *
     * @param addCourseDTO
     * @return
     */
    @PostMapping("/addCourse")
    @Operation(summary = "新增课程")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result addCourse(@RequestBody AddCourseDTO addCourseDTO) {
        if (StringUtils.hasText(addCourseDTO.getCourseName())
                && StringUtils.hasText(addCourseDTO.getTeacherName())) {
            courseService.addCourse(addCourseDTO);
            return Result.success("新增成功", addCourseDTO);
        } else {
            throw new BaseException("信息不完整或为空");
        }
    }
    /**
     * 分页查询所有课程信息
     */
    @PostMapping("/pageCourse")
    @Operation(summary = "分页查询所有课程")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<CourseListAllVO>> listClass(@RequestBody ListCourseDTO listCourseDTO) {
        log.info("列出所有课程信息");
        {
            //分页查询所有班级
            PageResult<CourseListAllVO> pageResult = courseService.listCourse(listCourseDTO);
            return Result.success("查询成功", pageResult);
        }
    }
    /**
     * 批量删除课程
     *
     * @param deleteCourseDTO
     * @return
     */
    @PostMapping("/deleteCourse")
    @Operation(summary = "批量删除课程")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result deleteCourse(@RequestBody DeleteCourseDTO deleteCourseDTO) {
        Result result = new Result<>();
        try {
            return courseService.deleteCourse(deleteCourseDTO);

        } catch (Exception e) {
            return Result.error("删除失败，可能是课程不存在");
        }
    }
    /**
     * 对课程进行修改
     *
     * @param updateCourseDTO
     * @return
     */
    @PostMapping("/updateCourse")
    @Operation(summary = "对课程进行修改")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result updateWarehouse(@RequestBody UpdateCourseDTO updateCourseDTO) {
        courseService.updateCourse(updateCourseDTO);
        return Result.success("修改成功", updateCourseDTO);
    }

    /**
     * 分页查询自己任教的课程
     * @return
     */
    @GetMapping("/getMyCourses")
    @Operation(summary = "查询自己任教的课程")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<List<CourseVO>> getMyCourses() {
        return Result.success("查询成功",courseService.getMyTeachCourses());
    }

}
