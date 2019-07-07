package com.ambowEducation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentBaseInfoDto {
    private Integer id;
    private String sNo;
    private String name;
    private Integer sex;
    private String school;
    private String grade;
    private String nativePlace;
    private Date birthday;
    private String phone;
    private String idNumber;
}
