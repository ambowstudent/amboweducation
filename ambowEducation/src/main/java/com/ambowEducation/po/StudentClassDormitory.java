package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentClassDormitory implements Serializable{

  private long id;
  private long sId;
  private String sName;
  private long cId;
  private String cName;
  private long dId;
  private String dNumber;

}
