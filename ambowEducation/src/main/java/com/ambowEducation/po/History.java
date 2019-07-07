package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class History implements Serializable{

//  历史ID
  private Integer id;
//  导师ID
  private Integer tuId;
//  导师姓名
  private String tuName;
//  学生ID
  private Integer sId;
//  学生姓名
  private String sName;
//  扣分说明
  private String reason;

  private Student student;
}
