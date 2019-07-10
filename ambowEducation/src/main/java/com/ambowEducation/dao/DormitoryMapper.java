package com.ambowEducation.dao;

/**
 * 档案号，人名，宿舍号
 */

import com.ambowEducation.dto.StudentClassDormitory;
import com.ambowEducation.dto.StudentDormitoryDto;
import com.ambowEducation.po.Dormitory;
import org.apache.ibatis.annotations.*;

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

    //批量添加宿舍和班级
    //public int insStudentDormiitoryAndClazz(List<StudentClassDormitory> list);

    //批量添加宿舍
    public int insertStudentDormitory(List<StudentDormitoryDto> list);

    //学生换宿舍
    @Update("update t_student_dormitory set d_number=#{dNumber}, " +
            "s_name=#{sName}, school=#{school} where s_no=#{sNo}")
    public int updateDormitoryBySNo(StudentDormitoryDto dto);

}