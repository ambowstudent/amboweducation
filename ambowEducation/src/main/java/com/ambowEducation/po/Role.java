package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable{

//  User Id
  private Integer id;
//  角色名称
  private String name;
//  描述
  private String description;

//  权限
  private List<Permission> permissions;
}
