package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseGrade implements Serializable{

  private long id;
  private long sId;
  private long crId;
  private double grade;

}
