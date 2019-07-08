package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom implements Serializable{

//  教室id（与业务无关）
  private Integer id;
//  教师门牌号
  private String roomNumber;
//  容量
  private Integer roomCapacity;

//  班级
  private Clazz clazz;
}
