package com.ambowEducation.dao;


import com.ambowEducation.po.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ClassMapper {

//    查询所有班级
    @Select("select * from t_clazz")
    public List<Clazz> selectClazzList();

//    根据ID查询某个班级
    @Select("select * from  t_clazz where id = #{arg0 }")
    public Clazz selectClazz(int ClazzId);

//    添加班级
    @Insert("insert into t_clazz values(null,#{ctId},#{teId},#{tuId},#{roomId},#{name})")
    public int insertClazz(Clazz c);

//    修改班级
    @Update("update t_clazz set ct_id=#{ctId},te_id=#{teId},tu_id=#{tuId},room_id=#{roomId},name=#{name} where id = #{id}")
    public int updateClazz(Clazz c);

//    删除班级
    @Delete("delete from t_clazz where id = #{arg0}")
    public int deleteClazz(int ClazzId);


}