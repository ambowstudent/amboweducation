package com.ambowEducation.dao;


import com.ambowEducation.po.StudentCourseGrade;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentCourseGradeMapper {

    /**
     * 添加成绩
     */
    @Insert("insert into t_student_course_grade values(default,#{sId},#{crId},#{grade})")
    void  insertStudentGrade(StudentCourseGrade studentCourseGrade);
    /**
     * 修改成绩
     */
    @Update("update t_student_course_grade set grade=#{grade}  where s_id=#{sId} and cr_id=#{crId}")
    void  updateStudentGrade(StudentCourseGrade studentCourseGrade);
    /**
     *查询某个学生全部成绩
     */
    @Select("select t_scg.id,t_scg.grade,t_scg.cr_id cid " +
            "from t_course t_c left join t_student_course_grade t_scg on t_c.id=t_scg.cr_id  and t_scg.s_id=#{studentId}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "cid",property = "course",many = @Many(select = "com.ambowEducation.dao.CourseMapper.findByCourseId"))
    })
    List<StudentCourseGrade> findOneStudentAllGrade(int studentId);
    /**
     * 查询某个学生的某个成绩
     */
    @Select("select t_scg.id,t_scg.grade " +
            "from t_course t_c left join t_student_course_grade t_scg on t_c.id=t_scg.cr_id  where t_scg.s_id=#{studentId} and t_scg.cr_id=#{courseId}")
    StudentCourseGrade findOneStudentOneGrade(@Param("studentId") int studentId,@Param("courseId") int courseId);
}