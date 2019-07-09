package com.ambowEducation.dao;


import com.ambowEducation.po.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CourseMapper {

//    查询所有课程
    @Select("select * from t_course")
    public List<Course> selectList();

//    根据ID查询单个课程
    @Select("select * from t_course where id = #{id }")
    public Course select(int cid);

//    添加课程
    @Insert("insert into t_course values(null,#{name })")
    public int insert(Course course);

//    修改课程
    @Update("update t_course set name=#{name} where id = #{id }")
    public int update(Course course);

//    删除课程
    @Delete("delete from t_course where id = #{arg0 }")
    public int delete(int cid);


    //以下两个与成绩表关联
    /**
     * 根据课程id，查询课程信息
     */
    @Select("select * from t_course where id=#{courseId}")
    Course findByCourseId(@Param("courseId") int courseId);
    /**
     * 查询所有课程
     */
    @Select("select * from t_course")
    List<Course> findAllCourse();

}