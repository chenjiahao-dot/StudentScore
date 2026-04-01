package org.example.Demo.controller.Score;

import com.alibaba.excel.EasyExcel;
import com.common.Context.BaseContext;
import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.UserType;
import org.example.Demo.Common.BaseException;
import org.example.Demo.Common.ScoreException;
import org.example.Demo.DTO.Score.*;
import org.example.Demo.VO.Score.MyScoreVO;
import org.example.Demo.VO.Score.ScoreVO;
import org.example.Demo.enummerate.UserTypeEnum;
import org.example.Demo.mapper.ScoreMapper;
import org.example.Demo.server.ScoreServer;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/score/basics")
@Slf4j
@Tag(name = "成绩相关接口")
@CrossOrigin
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreServer scoreServer;
    private final ScoreMapper scoreMapper;
    /**
     * 录入成绩
     *
     * @param addClassDTO
     * @return
     */
    @PostMapping("/addScore")
    @Operation(summary = "录入成绩")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result addScore(@RequestBody AddScoreDTO addClassDTO) {
        //同时校验：Score 和 courseId和StudentId 都不能是 null，也不能是空字符串/空白
        if (StringUtils.hasText(addClassDTO.getScore())
                && StringUtils.hasText(addClassDTO.getCourseId()) && StringUtils.hasText(addClassDTO.getStudentId())
            && addClassDTO.getExamType()!=null){
            scoreServer.addScore(addClassDTO);
            return Result.success("新增成功", addClassDTO);
        } else {
            throw new BaseException("信息不完整或为空");
        }
    }
    /**
     * 分页查询所有学生成绩
     * @param scorePageQueryDTO
     * @return
     */
    @PostMapping("/PageStudentScore")
    @Operation(summary = "分页查询所有学生成绩")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<ScoreVO>> pageWarehouse(@RequestBody ScorePageQueryDTO scorePageQueryDTO){
        log.info("开始分页查询所有学生成绩");
        try{
            PageResult<ScoreVO> pageResult= scoreServer.pageScore(scorePageQueryDTO);
            return Result.success("查询成功",pageResult);
        }catch (BaseException e){
            return Result.error(e.getMessage());
        }
    }
    /**
     * 对学生成绩进行修改
     *
     * @param updateStudentScoreDTO
     * @return
     */
    @PostMapping("/updateStudentScore")
    @Operation(summary = "对学生成绩进行修改")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result updateWarehouse(@RequestBody UpdateStudentScoreDTO updateStudentScoreDTO) {
        scoreServer.updatestudentScore(updateStudentScoreDTO);
        return Result.success("修改成功", updateStudentScoreDTO);
    }
    /**
     * 删除学生成绩
     *
     * @Param id
     * @Return
     */
    @PostMapping("/deleteStudentScore/{id}")
    @Operation(summary = "删除学生成绩")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result deleteStudentScore(@PathVariable @Parameter(description = "要删除的学生的id") Long id) {
        Result result = new Result<>();
        try {
            scoreServer.deleteStudentScore(id);
            return Result.success("删除成功");
        } catch (Exception e) {
            return Result.error("删除失败，可能是id不存在");
        }
    }
    /**
     * 导出所有成绩数据到Excel
     * @param response HTTP响应对象，用于输出Excel流
     * @throws Exception 异常抛出（实际项目可自定义全局异常处理）
     */
    @GetMapping("/exportScore")
    @Operation(summary = "导出成绩")
    @ApiOperationSupport(author = "陈嘉豪")
    public void exportExcel(HttpServletResponse response) throws IOException {
        // 1. 直接查询 带姓名、课程名、学期名 的完整数据
        UserTypeEnum userTypeEnum= BaseContext.getCurrentPrimaryUserEnum();
        if (userTypeEnum==UserTypeEnum.ADMIN){
            List<ScoreExcelDTO> list = scoreMapper.selectScoreForExcel();

            // 2. 导出 Excel
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("学生成绩表", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            EasyExcel.write(response.getOutputStream(), ScoreExcelDTO.class)
                    .sheet("成绩数据")
                    .doWrite(list);
        }else{
            throw new ScoreException("无权导出数据，请找管理员！");
        }

    }
    /**
     * 查询学生成绩详情
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("getStudentScoreById/{id}")
    @Operation(summary = "查询学生成绩详情")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<ScoreVO> getWarehouseGoodsById(@PathVariable Long id) throws Exception {
        log.info("正在查询ID:{}的成绩详情", id);
        {
            //根据用户id去查找详情信息
            ScoreVO scoreVO= scoreServer.getStudentScoreById(id);
            return Result.success("查询成功", scoreVO);
        }
    }
    /**
     * 分页查询我的成绩
     * @param myScorePateQueryDTO
     * @return
     */
    @PostMapping("/PageMyScore")
    @Operation(summary = "分页查询我的成绩")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<MyScoreVO>> pageWarehouse(@RequestBody MyScorePateQueryDTO myScorePateQueryDTO){
        log.info("开始分页查询我的成绩");
        try{
            PageResult<MyScoreVO> pageResult= scoreServer.pageMyScore(myScorePateQueryDTO);
            return Result.success("查询成功",pageResult);
        }catch (BaseException e){
            return Result.error(e.getMessage());
        }
    }
}
