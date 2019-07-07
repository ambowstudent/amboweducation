package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

//  User Id
  private Integer id;
  private String username;
  private String password;
  private Date createtime;

  private List<Role> roles;
}
