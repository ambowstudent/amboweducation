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

  private Integer id;
  private Integer ctId;
  private Integer teId;
  private Integer tuId;
  private Integer roomId;
  private String name;

  private List<Student> students;
  private ClassType cType;
  private TechnicalTeacher teacher;
  private Tutor tutor;
  private Classroom classroom;
}