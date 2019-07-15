package com.ambowEducation.dao;

import com.ambowEducation.po.Classroom;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//教师资源
public interface ClassroomMapper {

    //
    @Select("select * from t_classroom")
    List<Classroom> selectList();

    @Select("select * from t_classroom where room_number = #{arg0 }")
    Classroom selectByRoomNumber(String roomNunber);

    @Insert("insert into t_classroom values(null,#{roomNumber },#{roomCapacity})")
    int insert(Classroom classroom);

    @Update("update t_classroom set room_number = #{roomNumber },room_capacity = #{roomCapacity} where id = #{id}")
    int update(Classroom classroom);

    @Delete("delete from t_classroom where id = #{arg0}")
    int delete(int id);

    @Select("select * from t_classroom where id = #{arg0}")
    Classroom selectById(int id);

}