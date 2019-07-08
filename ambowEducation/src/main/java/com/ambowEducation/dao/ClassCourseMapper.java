package com.ambowEducation.dao;

import com.ambowEducation.po.ClassCourse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ClassCourseMapper {

    //当添加班级的时候，选择一些课程 动态添加到这张表中
    public int insertClassCourse(@Param("cId") Integer cId,
                                 @Param("crIds") List<Integer> crIds);

    @Select("select cr_id from t_class_course where c_id=#{id}")
    public List<Integer> queryCourseIdByClassId(Integer id);

}
