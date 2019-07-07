package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Work implements Serializable{

//  就业信息id
  private Integer id;
//  学生ID
  private Integer sId;
//  公司名称
  private String companyName;
//  薪资
  private BigDecimal salary;
//  工作类型（做什么样的工作）
  private String type;

  private Student student;

}
