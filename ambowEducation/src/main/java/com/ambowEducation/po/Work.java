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

  private Integer id;
  private Integer sId;
  private String companyName;
  private BigDecimal salary;
  private String type;

  private Student student;

}
