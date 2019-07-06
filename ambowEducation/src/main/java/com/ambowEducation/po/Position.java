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

  private Integer id;
  private String position;
  private BigDecimal salary;
  private String companyName;
  private String location;
  private String detail;
  private Date createtime;

  private Tutor tutor;
  private List<Student> students;
}
