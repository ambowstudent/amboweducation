package com.ambowEducation.service;

import com.ambowEducation.dto.StudentGradeDto;
import com.ambowEducation.po.Student;
import com.ambowEducation.po.StudentCourseGrade;

import java.util.List;
import java.util.Map;

/**
 * 技术老师管理学生成绩的业务接口
 */
public interface StudentCourseGradeService {

    /**
     * 添加学生成绩
     */
    void insertStudentCourse(StudentGradeDto studentGradeDto) throws Exception;
    /**
     * 根据学生学号，查询学生信息
     */
    Student findStudentByStudentNo(String stuNo)throws Exception;
    /**
     * 修改学生成绩
     */
    void modifyStudentCourseByStuId(StudentGradeDto studentGradeDto)throws Exception;
    /**
     * 根据多个条件查询、学校、id、课程名、姓名
     */
    List<StudentCourseGrade> findAllByManyCondition(String sNo, String studentName, String school, String courseName, Integer clazzId)throws Exception;
    /**
     * 查询学生就业情况，根据工作类型分组
     */
    Map<String,Integer> findStudentWorkRateOfEmployment(int teachId)throws Exception;

    //根据学号查询学生的考试成绩
     List<StudentCourseGrade> findMyGrade(int sId);

    //根据学号查询学生的考试成绩（通过课程成绩）
     StudentCourseGrade findMyGradeByKey(int sid, int cid);

     //查询所有学生的就业情况
    List<Map<String,Object >> findAllStudentWorkPercent() throws Exception;

    //查询近三年工资情况
    Map<String,List<Map<String,Object>>> selectThreeYearSal() throws Exception;


}
