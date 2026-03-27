package org.example.Demo.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.example.Demo.DTO.Score.ScorePageQueryDTO;
import org.example.Demo.VO.Score.scoreVO;
import org.example.Demo.entity.score;
import org.example.Demo.enummerate.UserTypeEnum;
import org.example.Demo.enummerate.ExamTypeEnum;

public interface ScoreMapper {
    @Select("select exam_type from score where id=#{id}")
    score selectScoreById(Long id);
    @Insert("insert into score(id,student_id,course_id,score,exam_type,create_time)VALUES (#{id},#{studentId},#{courseId},#{score},#{examType},#{createTime} )")
    void insertScore(score score);
    @Select("select user_type_enum from user where id=#{id} ")
    UserTypeEnum selectUserType(String studentId);
    @Select("SELECT COUNT(*) FROM score WHERE student_id = #{studentId} AND course_id = #{courseId} AND exam_type = #{examType}")
    Integer selectExistScore(Long studentId, Long courseId, ExamTypeEnum examType);


    Page<scoreVO> pageScore(ScorePageQueryDTO scorePageQueryDTO);

    void updateStudentScore(score score);
    @Select("select score from score where student_id=#{studentId}")
    score selectScoreValue(String score);
    @Select("select id from score where id=#{id} ")
    boolean selectById(Long id);
    @Delete("delete from score where id=#{id}")
    void deleteById(Long id);
}
