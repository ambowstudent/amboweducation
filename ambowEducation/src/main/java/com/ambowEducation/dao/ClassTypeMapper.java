package com.ambowEducation.dao;


import org.apache.ibatis.annotations.Update;

public interface ClassTypeMapper {
    //调整学生班级
    @Update("update t_student_class_dormitory set s_id=#{arg0},s_name=#{arg1},c_id=#{arg2},c_name=#{arg3} where id=#{arg4}")
    public int changeStudentClass(int sid, String sname, int cid, String cname, int id);

}