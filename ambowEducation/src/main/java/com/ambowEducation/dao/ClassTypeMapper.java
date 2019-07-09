package com.ambowEducation.dao;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
//班级类型
public interface ClassTypeMapper {
/*    //调整学生班级
    @Update("update t_student_class_dormitory set s_id=#{arg0},s_name=#{arg1},c_id=#{arg2},c_name=#{arg3} where id=#{arg4}")
    public int changeStudentClass(int sid, String sname, int cid, String cname, int id);

//    查询所有班级类型
    @Select("select * from t_class_type")
    public List<ClassType> selectList();

//    根据ID查询某个班级类型
    @Select("select * from t_class_type where id = #{arg0 }")
    public ClassType select(int cTypeId);

//    添加班级类型
    @Insert("insert into t_class_type values(null,#{name })")
    public int insert(ClassType ct);

//    修改班级类型
    @Update("update t_class_type set name = #{name }where id = #{id }")
    public int update(ClassType ct);

//    删除班级类型
    @Delete("delete from t_class_type where id = #{arg0 }")
    public int delete(int cTypeId);*/

}