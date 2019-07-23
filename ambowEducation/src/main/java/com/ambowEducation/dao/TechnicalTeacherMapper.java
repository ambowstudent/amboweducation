package com.ambowEducation.dao;


import com.ambowEducation.po.TechnicalTeacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TechnicalTeacherMapper {

    //    查询所有技术老师
    @Select("select * from t_technical_teacher")
    public List<TechnicalTeacher> selectTechnicalTeacherList();

    //    根据ID查询单个技术老师
    @Select("select * from t_technical_teacher where id = #{arg0 }")
    public TechnicalTeacher selectTechnicalTeacher(int technicalTeacherId);

    //    根据关键字进行查询，关键字empNo
    @Select("select * from t_technical_teacher where emp_no like  concat('%' ,#{arg0 },'%')")
    public List<TechnicalTeacher> selectTechnicalTeacherListByKey(String empNo);

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

    //孙 添加的
    //根据技术老师id查询技术老师管理所有学生个数

    @Select("select count(*) from t_technical_teacher tech left join t_clazz  clazz\n" +
            "on tech.id=clazz.te_id left join t_student s on clazz.id=s.c_id\n" +
            "where tech.id=#{techId}")
     int findTechnicalTeacherInStudentCount(@Param("techId") int techId);

}