package com.ambowEducation.dao;

import com.ambowEducation.po.Classroom;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//教室资源
public interface ClassroomMapper {

    //查看所有所有教室
    @Select("select * from t_classroom")
    List<Classroom> selectList();
//    根据教室号查询教室
    @Select("select * from t_classroom where room_number = #{arg0 }")
    Classroom selectByRoomNumber(String roomNunber);
//    添加教室
    @Insert("insert into t_classroom values(null,#{roomNumber },#{roomCapacity})")
    int insert(Classroom classroom);
//    修改教室
    @Update("update t_classroom set room_number = #{roomNumber },room_capacity = #{roomCapacity} where id = #{id}")
    int update(Classroom classroom);
//    删除教室
    @Delete("delete from t_classroom where id = #{arg0}")
    int delete(int id);
//    根据教室id查询教室
    @Select("select * from t_classroom where id = #{arg0}")
    Classroom selectById(int id);

}