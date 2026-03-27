package org.example.Demo.server.Impl;

import com.common.Context.BaseContext;
import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.ScoreException;
import org.example.Demo.DTO.Score.AddScoreDTO;
import org.example.Demo.DTO.Score.ScorePageQueryDTO;
import org.example.Demo.DTO.Score.UpdateStudentScoreDTO;
import org.example.Demo.UserException.AddUserException;
import org.example.Demo.VO.Score.scoreVO;
import org.example.Demo.entity.score;
import org.example.Demo.enummerate.UserTypeEnum;
import org.example.Demo.enummerate.ExamTypeEnum;
import org.example.Demo.mapper.ScoreMapper;
import org.example.Demo.server.ScoreServer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScoreImpl implements ScoreServer {
    private final ScoreMapper scoreMapper;

    /**
     * 录入学生成绩
     * @param addScoreDTO
     */
    @Override
    public void addScore(AddScoreDTO addScoreDTO) {
        UserTypeEnum userTypeEnum= BaseContext.getCurrentPrimaryUserEnum();
        UserTypeEnum userTypeEnumId=scoreMapper.selectUserType(addScoreDTO.getStudentId());
        Long studentId = Long.valueOf(addScoreDTO.getStudentId());
        Long courseId = Long.valueOf(addScoreDTO.getCourseId());
        ExamTypeEnum examType = addScoreDTO.getExamType();

        // 判断这个学生 + 这门课 + 这个考试类型（期中/期末）是否已经存在
        Integer count = scoreMapper.selectExistScore(studentId, courseId, examType);
        if (count > 0) {
            throw new AddUserException("该学生此课程的" + (examType == ExamTypeEnum.MIDTERM ? "期中" : "期末") + "成绩已存在，无法重复添加");
        }
        if (userTypeEnumId == null) {
            throw new AddUserException("该用户不存在");
        }
        if (userTypeEnumId != UserTypeEnum.STUDENT) {
            throw new AddUserException("该用户不是学生");
        }

        if (userTypeEnum==UserTypeEnum.ADMIN||userTypeEnum==UserTypeEnum.TEACHER) {
          score score =new score();
          score.setCreateTime(new Date());
          BeanUtils.copyProperties(addScoreDTO,score);
          scoreMapper.insertScore(score);
        }else{
            throw new ScoreException("无权限");
        }
    }

    /**
     * 分页查询所有学生成绩
     * @param scorePageQueryDTO
     * @return
     */
    @Override
    @Transactional
    public PageResult<scoreVO> pageScore(ScorePageQueryDTO scorePageQueryDTO) {
        PageHelper.startPage(scorePageQueryDTO.getPage(), scorePageQueryDTO.getPageSize());
        Page<scoreVO> page = scoreMapper.pageScore(scorePageQueryDTO);
        long total = page.getTotal();
        List<scoreVO> roles = page.getResult();
        return new PageResult<>(total, roles);
    }

    /**
     * 修改学生成绩
     * @param updateStudentScoreDTO
     */
    @Override
    public void updatestudentScore(UpdateStudentScoreDTO updateStudentScoreDTO) {
        UserTypeEnum userTypeEnum= BaseContext.getCurrentPrimaryUserEnum();

        if (userTypeEnum!=UserTypeEnum.ADMIN && userTypeEnum!=UserTypeEnum.TEACHER) {
            throw new ScoreException("无权限修改成绩");
        }
        //判断修改的分数是否为负数或大于100
        int scoreValue = Integer.parseInt(updateStudentScoreDTO.getScore());
        if (scoreValue < 0 || scoreValue>100) {
            throw new ScoreException("成绩必须在0~100之间");
        }
        score score = new score();
        BeanUtils.copyProperties(updateStudentScoreDTO,score);
        scoreMapper.updateStudentScore(score);
    }

    /**
     * 删除学生成绩
     * @param id
     * @return
     */
    @Override
    public Result deleteStudentScore(Long id) {
        Result result = new Result<>();
        if (scoreMapper.selectById(id)) {
            scoreMapper.deleteById(id);
            result.setMsg("删除成功");
            return result;
        }
        return result;
    }

}
