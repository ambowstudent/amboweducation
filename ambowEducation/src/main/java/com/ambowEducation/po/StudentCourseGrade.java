package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseGrade implements Serializable{

//  成绩id
  private Integer id;
//  学生id
  private Integer sId;
//  课程id
  private Integer crId;
//  成绩
  private double grade;

//  学生
  private Student student;
//  课程
  private Course course;

}
