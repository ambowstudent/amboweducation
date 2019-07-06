package com.ambowEducation.student.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class History implements Serializable{

  private long id;
  private long tuId;
  private String tuName;
  private long sId;
  private String sName;
  private String reason;

}
