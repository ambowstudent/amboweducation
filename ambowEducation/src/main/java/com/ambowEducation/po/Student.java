package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable{

  private Integer id;
  private String sNo;
  private String name;
  private Integer sex;
  private String school;
  private String grade;
  private String nativePlace;
  private Date birthday;
  private String phone;
  private String idNumber;
  private String interviewHistory;
  private String firstEmployment;
  private BigDecimal firstSalary;
  private String employmentTracking;
  private String photo;
  private String resume;
  private Integer classHour;

  private Clazz clazz;
  private List<StudentCourseGrade> grades;
  private List<Work> works;
  private List<History> historys;
  private List<Position> positions;
}
