package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom implements Serializable{

  private Integer id;
  private String roomNumber;
  private Integer roomCapacity;

  private Clazz clazz;
}
