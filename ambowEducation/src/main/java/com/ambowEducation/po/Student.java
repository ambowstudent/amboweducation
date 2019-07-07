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

//  学生id（与业务无关）
  private Integer id;
//  学生档案号
  private String sNo;
//  学生姓名
  private String name;
//  学生性别
  private Integer sex;
//  学生原学校
  private String school;
//  实训届，入学年级
  private String grade;
//  籍贯
  private String nativePlace;
//  出生日期
  private Date birthday;
//  联系方式
  private String phone;
//  身份证号
  private String idNumber;
//  曾经的面试历史
  private String interviewHistory;
//  首次就业单位
  private String firstEmployment;
//  首次就业薪资
  private BigDecimal firstSalary;
//  就业追踪
  private String employmentTracking;
//  照片(文件)
  private String photo;
//  简历(文件)
  private String resume;
  private Integer classHour;

  private Clazz clazz;
  private List<StudentCourseGrade> grades;
  private List<Work> works;
  private List<History> historys;
  private List<Position> positions;
}
