package org.example.Demo.controller.Semester;

import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.DTO.Semester.AddSemesterDTO;
import org.example.Demo.DTO.Semester.ListSemesterDTO;
import org.example.Demo.DTO.Semester.UpdateSemesterDTO;
import org.example.Demo.UserException.AddWarehouseException;
import org.example.Demo.VO.Semester.semesterListAllVO;
import org.example.Demo.server.SemesterServer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/semester/basics")
@Slf4j
@Tag(name = "学期相关接口")
@CrossOrigin
@RequiredArgsConstructor
public class SemesterController {
    private final SemesterServer semesterServer;
    /**
     * 新增学期
     * @param addSemesterDTO
     * @return
     */
    @PostMapping("/addSemester")
    @Operation(summary = "新增学期")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result addWarehouse(@RequestBody AddSemesterDTO addSemesterDTO){
        if (addSemesterDTO.getSemesterName()!=null && addSemesterDTO.getEndTime()!=null&&addSemesterDTO.getStartTime()!=null){
            semesterServer.addSemester(addSemesterDTO);
            return Result.success("新增成功",addSemesterDTO);
        }else{
            throw new AddWarehouseException("信息不完整");
        }
    }
    /**
     * 列出所有学期信息
     */
    @PostMapping("/listSemester")
    @Operation(summary = "列出所有学期")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<semesterListAllVO>> listClass(@RequestBody ListSemesterDTO listSemesterDTO) {
        log.info("列出所有学期信息");
        {
            PageResult<semesterListAllVO> pageResult = semesterServer.listSemester(listSemesterDTO);
            return Result.success("查询成功", pageResult);
        }
    }
    /**
     * 对学期进行修改
     *
     * @param updateSemesterDTO
     * @return
     */
    @PostMapping("/updateSemester")
    @Operation(summary = "对学期进行修改")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result updateSemester(@RequestBody UpdateSemesterDTO updateSemesterDTO) {
        semesterServer.updateSemester(updateSemesterDTO);
        return Result.success("修改成功", updateSemesterDTO);
    }
    /**
     * 删除学期
     *
     * @Param id
     * @Return
     */
    @PostMapping("/deleteSemester/{id}")
    @Operation(summary = "删除学期")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result deleteSemester(@PathVariable @Parameter(description = "要删除的学期的id") Long id) {
        Result result = new Result<>();
        try {
            semesterServer.deleteSemester(id);
            return Result.success("删除成功");
        } catch (Exception e) {
            return Result.error("删除失败，可能是id不存在");
        }
    }
}
