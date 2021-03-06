package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassType implements Serializable{

//  班级id（与业务无关）
  private Integer id;
//  班级名称
  private String name;

//  班级
  private List<Clazz> clazzs;
//  课程
  private List<Course> courses;

}
