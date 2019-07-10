package com.ambowEducation.service;

import com.ambowEducation.dto.StudentGradeDto;

/**
 * 技术老师管理学生成绩的业务接口
 */
public interface StudentCourseGradeService {
    /**
     * 添加学生成绩
     */
    void insertStudentCourse(StudentGradeDto studentGradeDto);
}
