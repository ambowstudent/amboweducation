package com.ambowEducation.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
//  用户名
  @JsonIgnore
  private String username;
//  密码
  private String password;
//  创建时间
  private Date createtime;

//  角色
  private List<Role> roles;
  //学生
  private Student student;
  //技术老师
  private TechnicalTeacher technicalTeacher;
  //班主任
  private Tutor tutor;
}
