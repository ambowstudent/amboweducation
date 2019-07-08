package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalTeacher implements Serializable{

//  老师id（与业务无关）
  private Integer id;
//  员工编号
  private String empNo;
//  老师姓名
  private String name;
//  老师性别
  private Integer sex;
//  擅长的技术
  private String skills;
//  工作年限
  private Integer workingSeniority;

//  班级
  private List<Clazz> clazzs;
}
