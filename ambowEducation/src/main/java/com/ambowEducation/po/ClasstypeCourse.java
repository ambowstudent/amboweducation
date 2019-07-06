package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasstypeCourse implements Serializable{

  private long id;
  private long ctId;
  private long crId;

}
