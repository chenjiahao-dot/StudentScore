package org.example.Demo.server;

import com.common.Result.PageResult;
import com.common.Result.Result;
import org.example.Demo.DTO.SemesterController.AddSemesterDTO;
import org.example.Demo.DTO.SemesterController.ListSemesterDTO;
import org.example.Demo.DTO.SemesterController.UpdateSemesterDTO;
import org.example.Demo.VO.Semester.semesterListAllVO;

public interface SemesterServer {
    void addSemester(AddSemesterDTO addSemesterDTO);

    PageResult<semesterListAllVO> listSemester(ListSemesterDTO listSemesterDTO);

    void updateSemester(UpdateSemesterDTO updateSemesterDTO);

    Result deleteSemester(Long id);
}
