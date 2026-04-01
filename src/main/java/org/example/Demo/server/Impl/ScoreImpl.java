package org.example.Demo.server.Impl;

import com.common.Context.BaseContext;
import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.BaseException;
import org.example.Demo.Common.ScoreException;
import org.example.Demo.DTO.Score.AddScoreDTO;
import org.example.Demo.DTO.Score.MyScorePateQueryDTO;
import org.example.Demo.DTO.Score.ScorePageQueryDTO;
import org.example.Demo.DTO.Score.UpdateStudentScoreDTO;
import org.example.Demo.UserException.AddUserException;
import org.example.Demo.VO.Score.MyScoreVO;
import org.example.Demo.VO.Score.ScoreVO;
import org.example.Demo.entity.Course;
import org.example.Demo.entity.Score;
import org.example.Demo.enummerate.OrderTypeEnum;
import org.example.Demo.enummerate.UserTypeEnum;
import org.example.Demo.enummerate.ExamTypeEnum;
import org.example.Demo.mapper.ScoreMapper;
import org.example.Demo.server.ScoreServer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //获取当前用户ID
        Long userId = BaseContext.getCurrentId();
        //获取当前用户身份
        UserTypeEnum userTypeEnum= BaseContext.getCurrentPrimaryUserEnum();
        //判断学生是否存在
        UserTypeEnum userTypeEnumId=scoreMapper.selectUserType(addScoreDTO.getStudentId());
        //studentId（String 类型）转成 Long
        Long studentId = Long.valueOf(addScoreDTO.getStudentId());
        //courseId（String 类型）转成 Long
        Long courseId = Long.valueOf(addScoreDTO.getCourseId());
        //判断学期是否存在
        Long semesterId=scoreMapper.selectSemesterById(addScoreDTO.getSemesterId());

        ExamTypeEnum examType = addScoreDTO.getExamType();

        // 判断这个学生 + 这门课 + 这个考试类型（期中/期末）是否已经存在
        Integer count = scoreMapper.selectExistScore(studentId, courseId, examType);
        if (count > 0) {
            throw new AddUserException("该学生此课程的" + (examType == ExamTypeEnum.MIDTERM ? "期中" : "期末") + "成绩已存在，无法重复添加");
        }
        if (userTypeEnumId == null) {
            throw new AddUserException("该用户不存在，请重新选择");
        }
        if (semesterId == null) {
            throw new AddUserException("学期不存在，请重新选择");
        }
        if (userTypeEnumId != UserTypeEnum.STUDENT) {
            throw new AddUserException("该用户不是学生");
        }
        if (userTypeEnum==UserTypeEnum.TEACHER){
            Long classId=scoreMapper.selectClassIdByStudentId(studentId);
            int countId=scoreMapper.selectTeacherCourseClass(userId,courseId,classId);
            if (countId == 0) {
                throw new ScoreException("你无权录入该班级/课程");
            }
        }
        Score score = new Score();
        score.setCreateTime(new Date());
        BeanUtils.copyProperties(addScoreDTO, score);
        scoreMapper.insertScore(score);

    }

    /**
     * 分页查询所有学生成绩
     * @param scorePageQueryDTO
     * @return
     */
    @Override
    @Transactional
    public PageResult<ScoreVO> pageScore(ScorePageQueryDTO scorePageQueryDTO) {
        PageHelper.startPage(scorePageQueryDTO.getPage(), scorePageQueryDTO.getPageSize());
        String sortField = "id";
        String sortOrder = "asc";
        // 如果前端传了字段 → 替换
        if (StrUtil.isNotBlank(scorePageQueryDTO.getSortField())) {
            sortField = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, scorePageQueryDTO.getSortField());
        }
        // 如果前端传了 ASC → 改成正序
        if (scorePageQueryDTO.getOrderType() == OrderTypeEnum.DESC) {
            sortOrder = "desc";
        }
        PageHelper.orderBy(sortField + " " + sortOrder);
        List<ScoreVO> list = scoreMapper.pageScore(scorePageQueryDTO);
        Page<ScoreVO> page = (Page<ScoreVO>) list;

        return new PageResult<>(page.getTotal(), page.getResult());
    }

    /**
     * 修改学生成绩
     * @param updateStudentScoreDTO
     */
    @Override
    public void updatestudentScore(UpdateStudentScoreDTO updateStudentScoreDTO) {
        UserTypeEnum userTypeEnum= BaseContext.getCurrentPrimaryUserEnum();
        Long userId=BaseContext.getCurrentId();

        //判断修改的分数是否为负数或大于100
        int scoreValue = Integer.parseInt(updateStudentScoreDTO.getScore());
        if (scoreValue < 0 || scoreValue>100) {
            throw new ScoreException("成绩必须在0~100之间");
        }
        Score oldScore = scoreMapper.selectStudentById(updateStudentScoreDTO.getId());
        if (oldScore == null) {
            throw new RuntimeException("成绩不存在");
        }
        if (userTypeEnum == UserTypeEnum.TEACHER) {
            Long classId = scoreMapper.selectClassIdByStudentId(oldScore.getStudentId());
            int count = scoreMapper.checkTeacherCourseClass(userId, oldScore.getCourseId(), classId);
            if (count == 0) {
                throw new RuntimeException("你无权修改该班级/课程");
            }
        }else if (userTypeEnum!=UserTypeEnum.ADMIN ) {
            throw new ScoreException("无权限修改成绩");
        }
        Score score = new Score();
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

    /**
     * 根据学生ID查询成绩详情
     * @param id
     * @return
     */
    @Override
    public ScoreVO getStudentScoreById(Long id) {
        ScoreVO scoreVO = scoreMapper.selectScore(id);
        if (scoreMapper.countWarehouseGoods(id) == 0) {
            throw new BaseException("学生成绩详情不存在");
        }
        return scoreVO;
    }

    /**
     * 分页查询我的成绩
     * @param myScorePateQueryDTO
     * @return
     */
    @Override
    public PageResult<MyScoreVO> pageMyScore(MyScorePateQueryDTO myScorePateQueryDTO) {
        Long  userId = BaseContext.getCurrentId();
        UserTypeEnum userTypeEnum= BaseContext.getCurrentPrimaryUserEnum();
        Long scoreId=scoreMapper.selectMyScoreById(userId);
        if (userTypeEnum!=UserTypeEnum.STUDENT){
            throw new ScoreException("只有学生可以查询到自己的成绩");
        }
        if (scoreId==null){
            throw new ScoreException("该学生暂无成绩");
        }
        PageHelper.startPage(myScorePateQueryDTO.getPage(), myScorePateQueryDTO.getPageSize());
        String sortField = "id";
        String sortOrder = "asc";
        // 如果前端传了字段 → 替换
        if (StrUtil.isNotBlank(myScorePateQueryDTO.getSortField())) {
            sortField = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, myScorePateQueryDTO.getSortField());
        }
        // 如果前端传了 ASC → 改成正序
        if (myScorePateQueryDTO.getOrderType() == OrderTypeEnum.DESC) {
            sortOrder = "desc";
        }
        PageHelper.orderBy(sortField + " " + sortOrder);

        List<MyScoreVO> list = scoreMapper.pageMyScore(userId);
        Page<MyScoreVO> page = (Page<MyScoreVO>) list;

        return new PageResult<>(page.getTotal(), page.getResult());
    }

}
