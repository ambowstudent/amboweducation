package com.ambowEducation.dao;


import com.ambowEducation.po.Student;

import java.util.List;

public interface StudentMapper {
    //查询学生所有信息
    public List<Student> findAll();

    //查询单个学生信息
    public List<Student> findById(int sid);
}