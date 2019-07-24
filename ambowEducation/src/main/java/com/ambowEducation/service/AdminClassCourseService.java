package com.ambowEducation.service;

import com.ambowEducation.dto.ClassCourseDto;
import com.ambowEducation.dto.CourseDto;
import com.ambowEducation.dto.ReduceHoursDto;
import com.ambowEducation.po.Clazz;
import com.ambowEducation.po.Course;

import java.util.List;

/**
 *   管理员--信息管理业务接口
 */
public interface AdminClassCourseService {

//    添加班级
    void insertClazz(ClassCourseDto classCourseDto);
//    修改班级
    void updateClazz(ClassCourseDto classCourseDto);
//    删除班级
    void deleteClazz(ClassCourseDto classCourseDto);
//    查询班级
    List<ClassCourseDto> selectClazz(String name);
//    查询班级简化版
    List<Clazz> selectClazzSmart(String name);
//    通过ID查询班级
    Clazz selectClazzById(ClassCourseDto classCourseDto);
//    查询该班级已选的课程
    String[] selectClazzCourseById(Integer id);

//    添加课程
    void insertCourse(CourseDto courseDto);
//    修改课程
    void updateCourse(CourseDto courseDto);
//    删除课程
    void deleteCourse(CourseDto courseDto);
//    查询课程
    List<Course> selectCourse(String name);
//    通过ID查询课程
    Course selectCourseById(CourseDto courseDto);

}
