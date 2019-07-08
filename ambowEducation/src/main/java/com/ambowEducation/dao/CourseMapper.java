package com.ambowEducation.dao;


import com.ambowEducation.po.Course;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CourseMapper {

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