package com.ambowEducation.service;

import com.ambowEducation.dto.TechnicalTeacherInfoDto;
import com.ambowEducation.dto.TutorInfoDto;
import com.ambowEducation.po.TechnicalTeacher;
import com.ambowEducation.po.Tutor;

import java.util.List;

/**
 * 管理员的对于老师业务接口
 */

public interface AdminTeacherService {

    /**
     * 教师管理--技术老师
     */

//    添加技术老师
    void insertTechinicalTeacher(TechnicalTeacherInfoDto teacherInfoDto);

    void updateTechnicalTeacher(TechnicalTeacherInfoDto teacherInfoDto);

    void deleteTechnicalTeacher(TechnicalTeacherInfoDto teacherInfoDto);

    List<TechnicalTeacher> selectTechnicalTeacher();

    TechnicalTeacher selectTechnicalTeacherById(TechnicalTeacherInfoDto teacherInfoDto);

    /**
     * 教师管理--学业导师
     */

//    添加学业导师
    void insertTutor(TutorInfoDto tutorInfoDto);

    void updateTutor(TutorInfoDto tutorInfoDto);

    void deleteTutor(TutorInfoDto tutorInfoDto);

    List<Tutor> selectTutors();

    Tutor selectTutorById(TutorInfoDto tutorInfoDto);

}
