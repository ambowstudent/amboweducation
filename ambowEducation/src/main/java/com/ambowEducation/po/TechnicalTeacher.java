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

  private Integer id;
  private String empNo;
  private String name;
  private String sex;
  private String skills;
  private Integer workingSeniority;

  private List<Clazz> clazzs;
}
