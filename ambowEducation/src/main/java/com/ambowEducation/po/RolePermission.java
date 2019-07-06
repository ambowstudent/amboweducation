package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission implements Serializable{

  private long id;
  private long permissionId;
  private long roleId;
  private String remark;

}
