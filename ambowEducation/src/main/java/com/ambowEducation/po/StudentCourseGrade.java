package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseGrade implements Serializable{

  private Integer id;
  private Integer sId;
  private Integer crId;
  private double grade;

  private Student student;
  private Course course;

}
