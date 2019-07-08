package com.ambowEducation.dao;


import com.ambowEducation.po.ReduceHours;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ReduceHoursMapper {

//    查询所有扣学分情况
    @Select("select * from t_reduce_hours")
    public List<ReduceHours> selectList();

//    根据ID查询单个扣学分情况
    @Select("select * from t_reduce_hours where id = #{arg0}")
    public ReduceHours select(int rhid);

//    添加扣学分情况
    @Insert("insert into r_reduce_hours values(null,#{name},#{classHour})")
    public int insert(ReduceHours reduceHours);

//    修改扣学分情况
    @Update("update t_reduce_hours set name = #{name },class_hour = #{classHour} where id = #{id } ")
    public int update(ReduceHours reduceHours);

//    删除扣学分情况
    @Delete("delete from t_reduce_hour where id = #{arg0}")
    public int delete(int rhid);


}