package org.example.Demo.server;

import com.common.Result.PageResult;
import com.common.Result.Result;
import org.example.Demo.DTO.Score.*;
import org.example.Demo.VO.Score.ClassAvgVO;
import org.example.Demo.VO.Score.ClassStudentScoreVO;
import org.example.Demo.VO.Score.MyScoreVO;
import org.example.Demo.VO.Score.ScoreVO;

import java.util.List;

public interface ScoreServer {
    void addScore(AddScoreDTO addClassDTO);

    PageResult<ScoreVO> pageScore(ScorePageQueryDTO scorePageQueryDTO);

    void updatestudentScore(UpdateStudentScoreDTO updateStudentScoreDTO);

    Result deleteStudentScore(Long id);

    ScoreVO getStudentScoreById(Long id);

    PageResult<MyScoreVO> pageMyScore(MyScorePageQueryDTO myScorePageQueryDTO);

    PageResult<ClassStudentScoreVO> pageCLassStudentScore(PageQueryClassStudentScoreDTO pageQueryClassStudentScoreDTO);


    List<ClassAvgVO> getClassAvg();
}
