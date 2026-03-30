package org.example.Demo.mapper;

import com.common.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.example.Demo.DTO.SemesterController.ListSemesterDTO;
import org.example.Demo.VO.Semester.semesterListAllVO;
import org.example.Demo.annotation.AutoFill;
import org.example.Demo.entity.Semester;

public interface SemesterMapper {
    @Insert("insert into semester(semester_name,start_time,end_time,create_time)VALUES (#{semesterName},#{startTime},#{endTime},#{createTime})")
    void insertSemester(Semester semester);

    Page<semesterListAllVO> pageSemester(ListSemesterDTO listSemesterDTO);
    /**
     * 对学期进行修改
     *
     * @param semester
     */
    @AutoFill(OperationType.UPDATE)
    void updateSemester(Semester semester);

    /**
     * 查询要删除的id是否存在
     * @param id
     * @return
     */
    @Select("select id from semester where id=#{id}")
    boolean selectSemeterById(Long id);

    /**
     * 删除学期
     * @param id
     */
    @Delete("delete from semester where id=#{id} ")
    void deleteSemesterById(Long id);
}
