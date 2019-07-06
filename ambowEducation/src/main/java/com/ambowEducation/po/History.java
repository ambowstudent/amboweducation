package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class History implements Serializable{

  private Integer id;
  private Integer tuId;
  private String tuName;
  private Integer sId;
  private String sName;
  private String reason;

  private Student student;
}
