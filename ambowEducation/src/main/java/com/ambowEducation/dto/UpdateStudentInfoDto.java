package com.ambowEducation.dto;

import com.ambowEducation.po.Clazz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentInfoDto {
    private String sNo;
    private String name;
    private String school;
    private String firstEmployment;
    private BigDecimal firstSalary;
    private Integer status;
    private String dNumber;
    private String cName;

    private List<Clazz> allClazzes;
}
