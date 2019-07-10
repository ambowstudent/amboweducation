package com.ambowEducation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoursHistoryDto {
    private String sName;
    private String school;
    private String cName;
    private String detail;
    private int hours;
    private Date editDate;
    private String reason;
}
