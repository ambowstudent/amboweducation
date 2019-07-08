package com.ambowEducation.dao;


import com.ambowEducation.po.Tutor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TutorMapper {


//    查询所有学业导师
    @Select("select * from t_tutor")
    public List<Tutor> selectTutorList();

//    根据ID查询单个学业导师
    @Select("select * from t_tutor where id = #{arg0}")
    public Tutor selectTutor(int tutorId);

//    添加新的学业导师
    @Insert("insert into t_tutor values(null,#{empNo},#{name},#{sex},#{workingSeniority})")
    public int insertTutor(Tutor tutor);

//    修改学业导师
    @Update("update t_tutor set emp_no = #{empNo},name=#{name},sex=#{sex},working_seniority=#{workingSeniority} where id = #{id}")
    public int updateTutor(Tutor tutor);

//    删除学业导师
    @Delete("delete from t_tutor where id = #{arg0}")
    public int deleteTutor(int tutorId);

}