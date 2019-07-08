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
public class Position implements Serializable{

//  职位id
  private Integer id;
//  职位名称
  private String position;
//  工资
  private BigDecimal salary;
//  公司名称
  private String companyName;
//  位置
  private String location;
//  详细信息
  private String detail;
//  发布时间
  private Date createtime;

  private String tuEmpNo;

//  学业导师
  private Tutor tutor;
//  学生
  private List<Student> students;
}
