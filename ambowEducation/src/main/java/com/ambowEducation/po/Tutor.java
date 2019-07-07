package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tutor implements Serializable{

//  导师id（与业务无关）
  private Integer id;
//  员工编号
  private String empNo;
//  员工姓名
  private String name;
//  员工性别
  private String sex;
//  工作年限
  private Integer workingSeniority;

  private List<Position> positions;
  private List<Clazz> clazzs;
}
