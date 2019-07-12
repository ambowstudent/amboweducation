package com.ambowEducation.dao;


import com.ambowEducation.po.TechnicalTeacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TechnicalTeacherMapper {

    //    查询所有技术老师
    @Select("select * from t_technical_teacher")
    public List<TechnicalTeacher> selectTechnicalTeacherList();

    //    根据ID查询单个技术老师
    @Select("select * from t_technical_teacher where id = #{arg0 }")
    public TechnicalTeacher selectTechnicalTeacher(int technicalTeacherId);

    //    添加技术老师
    @Insert("insert into t_technical_teacher values(null,#{empNo},#{name},#{sex},#{skills},#{workingSeniority})")
    public int insertTechnicalTeacher(TechnicalTeacher tea);

    //    修改技术老师
    @Update("update t_technical_teacher set name=#{name},sex=#{sex},skills=#{skills},working_seniority=#{workingSeniority} where id = #{id}")
    public int updateTechnicalTeacher(TechnicalTeacher tea);

    //    删除技术老师
    @Delete("delete from  t_technical_teacher where id = #{arg0 }")
    public int deleteTechnicalTeacher(int technicalTeacherId);

    //    获取最新添加的老师信息
    @Select("select * FROM t_technical_teacher ORDER BY id DESC LIMIT 0,1")
    public TechnicalTeacher selectLastestTeacher();

//    查询老师编号是否存在
    @Select("select * FROM t_technical_teacher where emp_no = #{arg0 }")
    public TechnicalTeacher selectTeacherByEmpNo(String emp_no);
}