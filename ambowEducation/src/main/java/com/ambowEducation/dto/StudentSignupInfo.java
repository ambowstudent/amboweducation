package com.ambowEducation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSignupInfo {
    private String name;
    private String phone;
    private String sex;
    private String location;
    private String school;
    private String position;
    private String companyName;
}
