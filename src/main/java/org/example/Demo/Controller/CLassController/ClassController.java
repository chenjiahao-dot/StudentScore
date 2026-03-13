package org.example.Demo.Controller.CLassController;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.DTO.ClassDTO.AddClassDTO;
import org.example.Demo.Result.Result;
import org.example.Demo.Server.ClassServer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/class")
@Slf4j
@Tag(name = "班级相关接口")
@CrossOrigin
@RequiredArgsConstructor
public class ClassController {
    private final ClassServer classServer;
    /**
     * 新增仓库
     *
     * @param addClassDTO
     * @return
     */
    @PostMapping("/addClass")
    @Operation(summary = "新增班级")
    @ApiOperationSupport(author = "陈嘉豪")
    public Result addWarehouse(@RequestBody AddClassDTO addClassDTO) {
        if (addClassDTO.getClassName() != null && addClassDTO.getGrade() != null) {
            classServer.addClass(addClassDTO);
            return Result.success("新增成功", addClassDTO);
        } else {
            throw new BaseException("信息不完整");
        }
    }
}
