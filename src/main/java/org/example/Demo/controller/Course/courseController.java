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
import org.example.Demo.VO.Course.courseListAllVO;
import org.example.Demo.server.CourseServer;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course/basics")
@Slf4j
@Tag(name = "课程相关接口")
@CrossOrigin
@RequiredArgsConstructor
public class courseController {
    private final CourseServer courseServer;
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
            courseServer.addCourse(addCourseDTO);
            return Result.success("新增成功", addCourseDTO);
        } else {
            throw new BaseException("信息不完整或为空");
        }
    }
    /**
     * 列出所有课程信息
     */
    @PostMapping("/listCourse")
    @Operation(summary = "列出所有课程")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<courseListAllVO>> listClass(@RequestBody ListCourseDTO  listCourseDTO) {
        log.info("列出所有课程信息");
        {
            //列出所有班级
            PageResult<courseListAllVO> pageResult = courseServer.listCourse(listCourseDTO);
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
    public Result deleteCourse(@RequestBody DeleteCourseDTO  deleteCourseDTO) {
        Result result = new Result<>();
        try {
            return courseServer.deleteCourse(deleteCourseDTO);

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
        courseServer.updateCourse(updateCourseDTO);
        return Result.success("修改成功", updateCourseDTO);
    }
}
