package com.ambowEducation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDormitoryDto {
    private Integer id;
    private String sNo;
    private String sName;
    private String school;
    private String dNumber;
}
