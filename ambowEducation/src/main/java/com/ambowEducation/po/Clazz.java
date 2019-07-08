package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clazz implements Serializable{

//  班级id（与业务无关）
  private Integer id;
//  班级类型
  private Integer ctId;
//  班级技术老师
  private Integer teId;
//  班级学业导师
  private Integer tuId;
//  教室
  private Integer roomId;
//  班级名称
  private String name;

//  学生
  private List<Student> students;
//  技术教师
  private TechnicalTeacher teacher;
//  学业导师
  private Tutor tutor;
//  班级教室
  private Classroom classroom;
}