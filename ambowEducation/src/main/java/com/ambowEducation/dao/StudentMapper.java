package com.ambowEducation.dao;



import com.ambowEducation.dto.StudentBaseInfoDto;
import com.ambowEducation.dto.StudentFirstWorkDto;
import com.ambowEducation.dto.StudentHoursDto;
import com.ambowEducation.po.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

public interface StudentMapper {



    public int insertStudents(List<StudentBaseInfoDto> list);//批量增加学生信息

    @Update("update t_student set class_hour=class_hour+#{classHour} where s_no=#{sNo}")
    public int updateStudentHoursAdd(StudentHoursDto student);//奖励学时

    @Update("update t_student set class_hour=class_hour-#{classHour} where s_no=#{sNo}")
    public int updateStudentHoursReduce(StudentHoursDto student);//扣学时

    @Update("update t_student set interview_history=#{interviewHistory},first_employment=#{firstEmployment}," +
            "first_salary=#{firstSalary} where s_no=#{sNo}")
    public int updateStudentFirstWork(StudentFirstWorkDto student);//添加学生第一次的就业信息

    @Select("select interview_history from t_student where s_no=#{sNo}")
    public String queryInterviewHistoryBySNo(String sNo);//查询学生曾经面试的公司

    @Delete("delete from t_student where s_no=#{sNo}")
    public int deleteStudentBySNO(String sNo);//通过学生的学号来删除一条学生信息

    //查询学生所有信息
    public List<Student> findAll();

    //查询单个学生信息
    public List<Student> findBySno(String sno);

    //查询学业导师带的学生
    @Select("select s.* from " +
            "t_student s,t_student_class_dormitory scd,t_tutor tu,t_clazz c " +
            "where s.id=scd.s_id and scd.c_id=c.id and c.tu_id=tu.id and tu.emp_no=#{tuNo}")
    public List<Student> findByTuEmpNo(String tuNo);


}