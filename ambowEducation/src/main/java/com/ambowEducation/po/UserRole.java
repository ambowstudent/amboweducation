package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable{

  private long id;
  private long userId;
  private long roleId;
  private String remark;

}
