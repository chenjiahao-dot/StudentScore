package org.example.Demo.server;

import com.common.Result.PageResult;
import com.common.Result.Result;
import org.example.Demo.DTO.Score.AddScoreDTO;
import org.example.Demo.DTO.Score.ScorePageQueryDTO;
import org.example.Demo.DTO.Score.UpdateStudentScoreDTO;
import org.example.Demo.VO.Score.scoreVO;

public interface ScoreServer {
    void addScore(AddScoreDTO addClassDTO);

    PageResult<scoreVO> pageScore(ScorePageQueryDTO scorePageQueryDTO);

    void updatestudentScore(UpdateStudentScoreDTO updateStudentScoreDTO);

    Result deleteStudentScore(Long id);
}
