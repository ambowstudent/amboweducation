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

  private List<Student> students;
  private ClassType cType;
  private TechnicalTeacher teacher;
  private Tutor tutor;
  private Classroom classroom;
}