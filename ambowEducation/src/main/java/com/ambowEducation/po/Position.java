package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position implements Serializable{

  private long id;
  private String position;
  private double salary;
  private String companyName;
  private String location;
  private String detail;
  private java.sql.Date createtime;

}
