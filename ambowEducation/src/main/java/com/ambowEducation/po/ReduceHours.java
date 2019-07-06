package com.ambowEducation.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReduceHours implements Serializable{

  private Integer id;
  private String name;
  private Integer classHour;

}
