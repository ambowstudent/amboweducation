package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalTeacher implements Serializable{

  private long id;
  private String empNo;
  private String name;
  private String sex;
  private String skills;
  private long workingSeniority;

}
