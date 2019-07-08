package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dormitory implements Serializable{

//    宿舍id
    private Integer id;
//    25#1#604
    private String number;

//    学生
    private List<Student> students;
}