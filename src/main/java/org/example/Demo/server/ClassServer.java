package org.example.Demo.server;

import com.common.Result.PageResult;
import com.common.Result.Result;
import org.example.Demo.DTO.Class.AddClassDTO;
import org.example.Demo.DTO.Class.DeleteClassesDTO;
import org.example.Demo.DTO.Class.ListClassDTO;
import org.example.Demo.DTO.Class.UpdateClassDTO;
import org.example.Demo.VO.Class.classListAllVO;

public interface ClassServer {
    void addClass(AddClassDTO addClassDTO);

    PageResult<classListAllVO> listClass(ListClassDTO listClassDTO);

    Result deleteClasses(DeleteClassesDTO deleteClassesDTO);


    void updateClass(UpdateClassDTO updateClassDTO);
}
