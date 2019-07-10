package com.ambowEducation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentInfoDto {
    private String sNo;
    private String name;
    private String school;
    private String firstEmployment;
    private String firstSalary;
    private Integer status;
    private String dNumber;
    private String cName;
}
