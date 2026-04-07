package com.common.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import org.example.Demo.VO.Score.ScoreExcelVO;
import org.example.Demo.entity.Score;
import org.example.Demo.entity.ScoreExcel;
import org.example.Demo.mapper.ScoreMapper;
import org.springframework.beans.BeanUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 必须是public类，类名必须和Controller里的一致
public class ScoreImportListener extends AnalysisEventListener<ScoreExcelVO> {

    private final ScoreMapper scoreMapper;
    private static final int BATCH_SIZE = 100;
    private final List<ScoreExcel> batchList = new ArrayList<>();

    // 必须有这个带参构造器，用来手动传入Mapper
    public ScoreImportListener(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    @Override
    public void invoke(ScoreExcelVO excelVO, AnalysisContext context) {
        ScoreExcel score = new ScoreExcel();
        score.setStudentId(excelVO.getStudentId());
        score.setCourseId(excelVO.getCourseId());
        score.setScore(excelVO.getScore());
        score.setExamType(excelVO.getExamType());
        score.setSemesterId(excelVO.getSemesterId());
        score.setCreateTime(new Date());
        batchList.add(score);
        if (batchList.size() >= BATCH_SIZE) {
            scoreMapper.batchInsertScore(batchList);
            batchList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!batchList.isEmpty()) {
            scoreMapper.batchInsertScore(batchList);
            batchList.clear();
        }
    }
}