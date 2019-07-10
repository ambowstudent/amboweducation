package com.ambowEducation.service;

import com.ambowEducation.dto.StudentGradeDto;
import com.ambowEducation.po.StudentCourseGrade;

import java.util.List;

/**
 * 技术老师管理学生成绩的业务接口
 */
public interface StudentCourseGradeService {
    /**
     * 添加学生成绩
     */
    void insertStudentCourse(StudentGradeDto studentGradeDto);

    //学生查询自己的全部成绩
    public List<StudentCourseGrade> findMyGrade(int studentId);

    //学生查询自己的某个成绩
    public StudentCourseGrade findMyGradeForCourse(int sid, int courseId);
}
