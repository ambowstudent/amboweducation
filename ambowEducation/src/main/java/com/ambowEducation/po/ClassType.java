package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassType implements Serializable{

  private Integer id;
  private String name;

  private List<Clazz> clazzs;
  private List<Course> courses;

}
