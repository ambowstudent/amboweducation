package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.TechnicalTeacherException;
import com.ambowEducation.dao.TechnicalTeacherMapper;
import com.ambowEducation.po.TechnicalTeacher;
import com.ambowEducation.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TechnicalTeacherMapper technicalTeacherMapper;

    @Override
    public TechnicalTeacher selectTeacherByEmpNo(String empNo) {

        TechnicalTeacher technicalTeacher = technicalTeacherMapper.selectTeacherByEmpNo(empNo);
        if(technicalTeacher==null){
            throw new TechnicalTeacherException(-1,"该技术老师不存在");
        }
        return technicalTeacher;

    }
}
