package com.ambowEducation.dao;


import com.ambowEducation.po.Classroom;
import com.ambowEducation.po.Dormitory;
import com.ambowEducation.po.Student;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DormitoryMapper {
    //调整学生宿舍
    @Update("update t_student_class_dormitory set s_id=#{arg0},s_name=#{arg1},d_id=#{arg2},d_number=#{arg3} where id=#{arg4}")
    public int changeStudentDormitory(int sid, String sname, int did, String dnumber, int id);


}