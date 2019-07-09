package com.ambowEducation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentClassDormitory {
    private int id;
    private int sId;
    private String sName;
    private int cId;
    private String cName;
    private int dId;
    private String dNumber;
}
