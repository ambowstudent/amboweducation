package com.ambowEducation.service;

import com.ambowEducation.po.TechnicalTeacher;

public interface TeacherService {

    //根据技术老师的emp_no查询老师信息

    TechnicalTeacher selectTeacherByEmpNo(String empNo);
}
