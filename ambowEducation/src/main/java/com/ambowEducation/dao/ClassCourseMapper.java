package com.ambowEducation.dao;

import com.ambowEducation.po.ClassCourse;
import org.apache.ibatis.annotations.Insert;

public interface ClassCourseMapper {

    //当添加班级的时候，选择一些课程 动态添加到这张表中
    public int insertClassCourse(ClassCourse cc);

}
