package com.ambowEducation.dao;


import com.ambowEducation.po.StudentCourseGrade;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentCourseGradeMapper {
    /**
     * 添加成绩
     */
    @Insert("insert into t_student_course_grade values(default,#{sId},#{crId},#{grade})")
    int  insertStudentGrade(StudentCourseGrade studentCourseGrade);
    /**
     * 修改成绩
     */
    @Update("update t_student_course_grade set grade=#{grade}  where s_id=#{sId} and cr_id=#{crId}")
    int  updateStudentGrade(StudentCourseGrade studentCourseGrade);
    /**
     *学生查询自己的全部成绩
     */
    @Select("select t_scg.id,t_scg.grade,t_scg.cr_id cid " +
            "from t_course t_c left join t_student_course_grade t_scg on t_c.id=t_scg.cr_id  and t_scg.s_id=#{studentId}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "cid",property = "course",many = @Many(select = "com.ambowEducation.dao.CourseMapper.findByCourseId"))
    })
    List<StudentCourseGrade> findOneStudentAllGrade(int studentId);
    /**
     * 学生查询自己的某个成绩
     */
    @Select("select t_scg.id,t_scg.grade " +
            "from t_course t_c left join t_student_course_grade t_scg on t_c.id=t_scg.cr_id  where t_scg.s_id=#{studentId} and t_scg.cr_id=#{courseId}")
    StudentCourseGrade findOneStudentOneGrade(@Param("studentId") int studentId,@Param("courseId") int courseId);
    /**
     *老师根据课程名的模糊查询某个学生的成绩,使用xml查询
     */

    List<StudentCourseGrade> findLikeCourseNameAndStudentId(@Param("courseName")String courseName,@Param("studentId") int studentId);
    /**
     * 老师根据课程名模糊查询所有学生成绩
     */
    List<StudentCourseGrade> findAllLikeCourseName(@Param("courseName") String courseName);
    /**
     * 老师根据学生学号，姓名，班级，学校，课程名
     */
    List<StudentCourseGrade> findAllByManyCondition(@Param("sNo") String sNo,@Param("studentName")String studentName,@Param("school")String school,@Param("courseName")String courseName);
}