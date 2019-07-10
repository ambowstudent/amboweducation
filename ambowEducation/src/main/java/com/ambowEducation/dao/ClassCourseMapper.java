package com.ambowEducation.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClassCourseMapper {

    //当添加班级的时候，选择一些课程 添加到这张表中
    public int insertClassCourse(@Param("cId") Integer cId,
                                 @Param("crIds") List<Integer> crIds);

    //通过班级id查询课程的id  然后通过课程id查询课程名称
    @Select("select cr_id from t_class_course where c_id=#{id}")
    public List<Integer> queryCourseIdByClassId(Integer id);

    @Delete("delete from t_class_course where c_id=#{cId}")
    public int deleteClassCourse(Integer cId);




}
