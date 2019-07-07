package com.ambowEducation.po;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReduceHours implements Serializable{

//  学时ID
  private Integer id;
//  扣分声明
  private String name;
//  扣分项所占学时
  private Integer classHour;

}
