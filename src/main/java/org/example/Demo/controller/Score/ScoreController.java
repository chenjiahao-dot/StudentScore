package org.example.Demo.controller.Score;

import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.DTO.Class.AddClassDTO;
import org.example.Demo.DTO.Score.AddScoreDTO;
import org.example.Demo.DTO.Score.ScorePageQueryDTO;
import org.example.Demo.DTO.Score.UpdateStudentScoreDTO;
import org.example.Demo.VO.Score.scoreVO;
import org.example.Demo.server.ClassServer;
import org.example.Demo.server.ScoreServer;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
@Slf4j
@Tag(name = "成绩相关接口")
@CrossOrigin
@RequiredArgsConstructor
public class ScoreController {
    private final ScoreServer scoreServer;
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
    public Result<PageResult<scoreVO>> pageWarehouse(@RequestBody ScorePageQueryDTO scorePageQueryDTO){
        log.info("开始分页查询所有学生成绩");
        try{
            PageResult<scoreVO> pageResult= scoreServer.pageScore(scorePageQueryDTO);
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
}
