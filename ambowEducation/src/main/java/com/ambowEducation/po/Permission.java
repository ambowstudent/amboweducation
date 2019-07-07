package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements Serializable{

//  id
  private Integer id;
//  模块操作
  private String name;
//  权限描述
  private String description;

}
