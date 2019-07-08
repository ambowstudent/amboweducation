package com.ambowEducation.dao;


import com.ambowEducation.po.Dormitory;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

import com.ambowEducation.po.Classroom;
import com.ambowEducation.po.Dormitory;
import com.ambowEducation.po.Student;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DormitoryMapper {
    //调整学生宿舍
    @Update("update t_student_class_dormitory set s_id=#{arg0},s_name=#{arg1},d_id=#{arg2},d_number=#{arg3} where id=#{arg4}")
    public int changeStudentDormitory(int sid, String sname, int did, String dnumber, int id);



//    查询所有宿舍
    @Select("select * from t_dormitory")
    public List<Dormitory> selectList();

//    根据ID查询单个宿舍
    @Select("select * from t_dormitory where id = #{id }")
    public Dormitory select(int did);

//    添加宿舍
    @Insert("insert into t_dormitory values(null,#{number })")
    public int insert(Dormitory dormitory);

//    修改宿舍
    @Update("update t_dormitory set number = #{number} where id = #{id }")
    public int update(Dormitory dormitory);

//    删除宿舍
    @Delete("delete from t_dormitory where id = #{arg0 }")
    public int delete(int did);

}