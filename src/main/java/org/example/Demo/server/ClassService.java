package org.example.Demo.server;

import com.common.Result.PageResult;
import com.common.Result.Result;
import org.example.Demo.DTO.Class.*;
import org.example.Demo.VO.Class.ClassListAllVO;
import org.example.Demo.VO.Class.PageTeacherCLassStudentVO;

public interface ClassService {
    void addClass(AddClassDTO addClassDTO);

    PageResult<ClassListAllVO> listClass(ListClassDTO listClassDTO);

    Result deleteClasses(DeleteClassesDTO deleteClassesDTO);


    void updateClass(UpdateClassDTO updateClassDTO);

    PageResult<PageTeacherCLassStudentVO> pageTeacherClassStudent(PageTeacherClassStudentDTO pageTeacherClassStudentDTO);
}
