package org.example.Demo.server.Impl;

import com.common.Result.PageResult;
import com.common.Result.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.Common.ClassException;
import org.example.Demo.DTO.Class.AddClassDTO;
import org.example.Demo.DTO.Class.DeleteClassesDTO;
import org.example.Demo.DTO.Class.ListClassDTO;
import org.example.Demo.VO.Class.classListAllVO;
import org.example.Demo.server.ClassServer;
import org.example.Demo.entity.Class;
import org.example.Demo.mapper.ClassMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassImpl implements ClassServer {
    private final ClassMapper classMapper;

    /**
     * 新增班级
     * @param addClassDTO
     */
    @Override
    public void addClass(AddClassDTO addClassDTO) {
        String className = addClassDTO.getClassName();
        String grade = addClassDTO.getGrade();
        Class clazz = classMapper.getByClass(addClassDTO.getClassName());
        if (clazz != null) {
            throw new ClassException("班级已存在");
        }
        if (className.length()<2){
            throw new ClassException("班级名称不能小于2位");
        }
        // 3. 校验 grade 等于 className 前两位
        String prefix = className.substring(0, 2);
        if (!prefix.equals(grade)) {
            throw new ClassException("年级必须与班级名称前两位一致！");
        }
        clazz = new Class();
        BeanUtils.copyProperties(addClassDTO, clazz);
        classMapper.insertClass(clazz);
    }
    /**
     * 查询所有班级信息
     */
    @Override
    public PageResult<classListAllVO> listClass(ListClassDTO listClassDTO) {
        PageHelper.startPage(listClassDTO.getPage(), listClassDTO.getPageSize());
        Page<classListAllVO> page = classMapper.pageQueryclass(listClassDTO);
        long total = page.getTotal();
        List<classListAllVO> roles = page.getResult();
        return new PageResult<>(total, roles);
    }

    /**
     * 批量删除班级
     * @param deleteClassesDTO
     * @return
     */
    @Override
    public Result deleteClasses(DeleteClassesDTO deleteClassesDTO) {
        Result result = new Result();
        List<Long> id = deleteClassesDTO.getId();
        for (int i = 0; i < id.size(); i++) {
            if (classMapper.selectClassesId(id.get(i)) != 0) {
                classMapper.deleteClassesId(id.get(i));
                result.setMsg("删除成功");
                result.setCode(1);
            } else {
                result.setMsg("删除失败");
                result.setCode(0);
            }
        }
        return result;
    }



}
