package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable{

  private long id;
  private String sNo;
  private String name;
  private long sex;
  private String school;
  private String grade;
  private String nativePlace;
  private java.sql.Date birthday;
  private String phone;
  private String idNumber;
  private String interviewHistory;
  private String firstEmployment;
  private double firstSalary;
  private String employmentTracking;
  private String photo;
  private String resume;
  private long classHour;

}
