package org.example.Demo.controller.CLass;

import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.DTO.Class.*;
import org.example.Demo.VO.Class.ClassListAllVO;
import org.example.Demo.VO.Class.PageTeacherCLassStudentVO;
import org.example.Demo.server.ClassService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/class")
@Slf4j
@Tag(name = "班级管理")
@CrossOrigin
@RequiredArgsConstructor
public class ClassSpecialController {
    private final ClassService classService;
    /**
     * 新增班级
     *
     * @param addClassDTO
     * @return
     */
    @PostMapping("/addClass")
    @Operation(summary = "新增班级")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result addWarehouse(@RequestBody AddClassDTO addClassDTO) {
        //同时校验：className 和 grade 都不能是 null，也不能是空字符串/空白
        if (StringUtils.hasText(addClassDTO.getClassName())
                && StringUtils.hasText(addClassDTO.getGrade())) {
            classService.addClass(addClassDTO);
            return Result.success("新增成功", addClassDTO);
        } else {
            throw new BaseException("信息不完整或为空");
        }
    }
    /**
     * 列出所有班级信息
     */
    @PostMapping("/pageClass")
    @Operation(summary = "分页查询所有班级")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<ClassListAllVO>> listClass(@RequestBody ListClassDTO listClassDTO) {
        log.info("列出所有班级信息");
        {
            //列出所有班级
            PageResult<ClassListAllVO> pageResult = classService.listClass(listClassDTO);
            return Result.success("查询成功", pageResult);
        }
    }
    /**
     * 批量删除仓库数量为0的仓库
     *
     * @param deleteClassesDTO
     * @return
     */
    @PostMapping("/deleteClass")
    @Operation(summary = "批量删除班级")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result deleteClasses(@RequestBody DeleteClassesDTO deleteClassesDTO) {
        Result result = new Result<>();
        try {
            return classService.deleteClasses(deleteClassesDTO);

        } catch (Exception e) {
            return Result.error("删除失败，可能是班级不存在");
        }
    }
    /**
     * 对班级进行修改
     *
     * @param updateClassDTO
     * @return
     */
    @PostMapping("/updateClass")
    @Operation(summary = "对班级进行修改")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result updateClass(@RequestBody UpdateClassDTO updateClassDTO) {
        classService.updateClass(updateClassDTO);
        return Result.success("修改成功", updateClassDTO);
    }
    /**
     * 分页查询老师班级学生
     * @param pageTeacherClassStudentDTO
     * @return
     */
    @PostMapping("/pageTeacherClassStudent")
    @Operation(summary = "分页查询老师班级学生")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<PageTeacherCLassStudentVO>> pageClassScore(@RequestBody PageTeacherClassStudentDTO pageTeacherClassStudentDTO) {
        log.info("开始分页查询班级学生成绩");
        try {
            PageResult<PageTeacherCLassStudentVO> pageResult = classService.pageTeacherClassStudent(pageTeacherClassStudentDTO);
            return Result.success("查询成功", pageResult);
        } catch (BaseException e) {
            return Result.error(e.getMessage());
        }
    }
}
