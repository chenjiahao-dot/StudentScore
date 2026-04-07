package org.example.Demo.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.example.Demo.DTO.Score.PageQueryClassStudentScoreDTO;
import org.example.Demo.DTO.Score.ScoreExcelDTO;
import org.example.Demo.DTO.Score.ScorePageQueryDTO;
import org.example.Demo.VO.Score.ClassAvgVO;
import org.example.Demo.VO.Score.ClassStudentScoreVO;
import org.example.Demo.VO.Score.MyScoreVO;
import org.example.Demo.VO.Score.ScoreVO;
import org.example.Demo.entity.Score;
import org.example.Demo.entity.ScoreExcel;
import org.example.Demo.enummerate.UserTypeEnum;
import org.example.Demo.enummerate.ExamTypeEnum;

import java.util.List;

public interface ScoreMapper {
    @Select("select exam_type from score where id=#{id}")
    Score selectScoreById(Long id);
    @Insert("insert into score(id,student_id,course_id,score,exam_type,semester_id,create_time)VALUES (#{id},#{studentId},#{courseId},#{score},#{examType},#{semesterId},#{createTime} )")
    void insertScore(Score score);
    @Select("select user_type_enum from user where id=#{id} ")
    UserTypeEnum selectUserType(String studentId);
    @Select("SELECT COUNT(*) FROM score WHERE student_id = #{studentId} AND course_id = #{courseId} AND exam_type = #{examType}")
    Integer selectExistScore(Long studentId, Long courseId, ExamTypeEnum examType);


    Page<ScoreVO> pageScore(ScorePageQueryDTO scorePageQueryDTO);

    void updateStudentScore(Score score);
    @Select("select score from score where student_id=#{studentId}")
    Score selectScoreValue(String score);
    @Select("select id from score where id=#{id} ")
    boolean selectById(Long id);
    @Delete("delete from score where id=#{id}")
    void deleteById(Long id);
    @Select("select id from semester where id=#{id}")
    Long selectSemesterById(Long semesterId);
    @Select("select * from score")
    List<Score> selectAllScore();
    @Select("select name from user where id=#{studentId} ")
    String selectStudentName(String studentId);

    List<ScoreExcelDTO> selectScoreForExcel();
    @Select("select * from score where id=#{id} ")
    ScoreVO selectScore(Long id);
    @Select("select count(*) from score where id=#{id} ")
    int countWarehouseGoods(Long id);

    @Select("select class_id FROM user WHERE id = #{studentId}")
    Long selectClassIdByStudentId(Long studentId);

    Integer selectTeacherCourseClass(Long userId, Long courseId, Long classId);
    @Select("SELECT * FROM score WHERE id = #{id}")
    Score selectStudentById(Long id);


    int checkTeacherCourseClass(Long userId, String courseId, Long classId);
    /**
     * 分页查询我的成绩
     * @param userId
     * @return
     */
    List<MyScoreVO> pageMyScore(Long userId);

    /**
     * 判断学生是否有成绩
     * @param userId
     * @return
     */
    @Select("select id from score where id=#{userId} ")
    Long selectMyScoreById(Long userId);


    /**
     * 分页查询班级学生成绩
      * @param userId
     * @return
     */
    List<ClassStudentScoreVO> pageCLassStudentScore(Long userId);

    /**
     * 批量插入成绩
     * @param batchList
     */
    void batchInsertScore(List<ScoreExcel> batchList);

    /**
     * 查询班级平均分
     * @return
     */
    List<ClassAvgVO> getClassAverageScore();
}
