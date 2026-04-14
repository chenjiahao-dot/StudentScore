package org.example.Demo.controller.Semester;

import com.common.Result.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.DTO.Semester.AddSemesterDTO;
import org.example.Demo.DTO.Semester.UpdateSemesterDTO;
import org.example.Demo.UserException.AddWarehouseException;
import org.example.Demo.server.SemesterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/semester")
@Slf4j
@Tag(name = "学期管理")
@CrossOrigin
@RequiredArgsConstructor
public class SemesterSpecialController {
    private final SemesterService semesterService;
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
            semesterService.addSemester(addSemesterDTO);
            return Result.success("新增成功",addSemesterDTO);
        }else{
            throw new AddWarehouseException("信息不完整");
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
        semesterService.updateSemester(updateSemesterDTO);
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
            semesterService.deleteSemester(id);
            return Result.success("删除成功");
        } catch (Exception e) {
            return Result.error("删除失败，可能是id不存在");
        }
    }
}
