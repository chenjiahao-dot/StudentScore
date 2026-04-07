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
import org.example.Demo.Common.SemesterException;
import org.example.Demo.DTO.Semester.AddSemesterDTO;
import org.example.Demo.DTO.Semester.ListSemesterDTO;
import org.example.Demo.DTO.Semester.UpdateSemesterDTO;
import org.example.Demo.VO.Semester.semesterListAllVO;
import org.example.Demo.entity.Semester;
import org.example.Demo.enummerate.OrderTypeEnum;
import org.example.Demo.enummerate.UserTypeEnum;
import org.example.Demo.mapper.SemesterMapper;
import org.example.Demo.server.SemesterServer;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SemesterImpl implements SemesterServer {
    private final SemesterMapper semesterMapper;

    /**
     * 新增学期
     * @param addSemesterDTO
     */
    @Override
    public void addSemester(AddSemesterDTO addSemesterDTO) {
        UserTypeEnum userTypeEnum= BaseContext.getCurrentPrimaryUserEnum();
        if (userTypeEnum!= UserTypeEnum.ADMIN&&userTypeEnum!=UserTypeEnum.TEACHER){
            throw new SemesterException("无权限添加");
        }
        if (addSemesterDTO.getStartTime().getTime()>addSemesterDTO.getEndTime().getTime()) {
            throw new SemesterException("结束时间不能大于开始时间");
        }
        Semester semester =new Semester();
        semester.setCreateTime(new Date());
        BeanUtils.copyProperties(addSemesterDTO,semester);
        semesterMapper.insertSemester(semester);
    }

    /**
     * 列出所有学期信息
     * @param listSemesterDTO
     * @return
     */
    @Override
    public PageResult<semesterListAllVO> listSemester(ListSemesterDTO listSemesterDTO) {
        PageHelper.startPage(listSemesterDTO.getPage(), listSemesterDTO.getPageSize());
        String sortField = "id";
        String sortOrder = "desc";
        // 如果前端传了字段 → 替换
        if (StrUtil.isNotBlank(listSemesterDTO.getSortField())) {
            sortField = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, listSemesterDTO.getSortField());
        }
        // 如果前端传了 ASC → 改成正序
        if (listSemesterDTO.getOrderType() == OrderTypeEnum.ASC) {
            sortOrder = "asc";
        }
        PageHelper.orderBy(sortField + " " + sortOrder);
        List<semesterListAllVO> list = semesterMapper.pageSemester(listSemesterDTO);
        Page<semesterListAllVO> page = (Page<semesterListAllVO>) list;

        return new PageResult<>(page.getTotal(), page.getResult());
    }

    /**
     * 修改学期信息
     * @param updateSemesterDTO
     */
    @Override
    public void updateSemester(UpdateSemesterDTO updateSemesterDTO) {
        Long id=updateSemesterDTO.getId();
        Date startTime=updateSemesterDTO.getStartTime();
        Date endTime=updateSemesterDTO.getEndTime();
        if(id==null||startTime==null||endTime==null){
            throw new SemesterException("请填写完整信息");
        }
        if(startTime.getTime()>endTime.getTime()){
            throw new SemesterException("结束时间不能大于开始时间");
        }
        Semester semester = new Semester();
        BeanUtils.copyProperties(updateSemesterDTO,semester);
        semesterMapper.updateSemester(semester);
    }

    /**
     * 删除学期
     * @param id
     * @return
     */
    @Override
    public Result deleteSemester(Long id) {
        Result result = new Result<>();
        if (semesterMapper.selectSemeterById(id)) {
            semesterMapper.deleteSemesterById(id);
            result.setMsg("删除成功");
            return result;
        }
        return result;
    }
}
