package com.ambowEducation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentFirstWorkDto {

    private Integer sId;
    private String sNo;
    private String firstEmployment;
    private BigDecimal firstSalary;
}
