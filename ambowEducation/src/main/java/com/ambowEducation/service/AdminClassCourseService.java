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
    List<Clazz> selectClazz();


    void insertCourse(CourseDto courseDto);

    void updateCourse(CourseDto courseDto);

    void deleteCourse(CourseDto courseDto);

    List<Course> selectCourse();


}
