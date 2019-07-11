package com.ambowEducation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentsHoursInfoDto {
    private Integer id;
    private String sNo;
    private String name;
    private String school;
    private Integer classHour;
    private String cName;
}
