package com.ambowEducation.dao;


import com.ambowEducation.po.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CourseMapper {

//    根据课程名查询一批课程id
    List<Integer> selectCourseIdListByCourseName(@Param("crIds") List<String> crIds);

//    根据课程id查询一批课程名
    List<String> selectCourseNameListByCourseId(@Param("crIds") List<Integer> crIds);

//    根据关键字 name 进行 查询合并
    @Select("select * from t_course where name like concat('%' ,#{arg0 },'%')")
    List<Course> selectListByKey(String name);

//    查询所有课程
    @Select("select * from t_course")
    public List<Course> selectList();

//    根据ID查询单个课程
    @Select("select * from t_course where id = #{id }")
    public Course select(int cid);

//    根据课程名查询课程
    @Select("select * from t_course where name = #{name }")
    public Course selectByName(String name);

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