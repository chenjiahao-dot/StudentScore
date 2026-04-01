package org.example.Demo.server;

import com.common.Result.PageResult;
import com.common.Result.Result;
import org.example.Demo.DTO.Score.AddScoreDTO;
import org.example.Demo.DTO.Score.MyScorePateQueryDTO;
import org.example.Demo.DTO.Score.ScorePageQueryDTO;
import org.example.Demo.DTO.Score.UpdateStudentScoreDTO;
import org.example.Demo.VO.Score.MyScoreVO;
import org.example.Demo.VO.Score.ScoreVO;

public interface ScoreServer {
    void addScore(AddScoreDTO addClassDTO);

    PageResult<ScoreVO> pageScore(ScorePageQueryDTO scorePageQueryDTO);

    void updatestudentScore(UpdateStudentScoreDTO updateStudentScoreDTO);

    Result deleteStudentScore(Long id);

    ScoreVO getStudentScoreById(Long id);

    PageResult<MyScoreVO> pageMyScore(MyScorePateQueryDTO myScorePateQueryDTO);
}
