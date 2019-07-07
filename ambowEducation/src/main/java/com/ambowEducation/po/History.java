package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;


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
//  学生
  private Student student;
//  修改时间
  private Date editTime;
//  奖扣 具体
  private String detail;

}
