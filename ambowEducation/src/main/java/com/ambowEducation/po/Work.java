package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Work implements Serializable{

  private long id;
  private long sId;
  private String companyName;
  private double salary;
  private String type;

}
