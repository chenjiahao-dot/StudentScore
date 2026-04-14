package org.example.Demo.controller.Semester;

import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.DTO.Semester.ListSemesterDTO;
import org.example.Demo.VO.Semester.semesterListAllVO;
import org.example.Demo.server.SemesterService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/semester/basics")
@Slf4j
@Tag(name = "学期管理")
@CrossOrigin
@RequiredArgsConstructor
public class SemesterController {
    private final SemesterService semesterService;
    /**
     * 分页查询所有学期
     */
    @PostMapping("/pageSemester")
    @Operation(summary = "分页查询所有学期")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result<PageResult<semesterListAllVO>> listClass(@RequestBody ListSemesterDTO listSemesterDTO) {
        log.info("分页查询所有学期");
        {
            PageResult<semesterListAllVO> pageResult = semesterService.listSemester(listSemesterDTO);
            return Result.success("查询成功", pageResult);
        }
    }
}
