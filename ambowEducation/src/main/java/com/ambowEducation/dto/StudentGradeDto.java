package com.ambowEducation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 这个类是用来封装技术老师添加学生成绩的dto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGradeDto {

    private String stuNo;  //学生学号
    private Integer crId;//课程id
    private Double grade; //学生成绩
    private Integer sId; //学生id

    private String school; //学校
    private String stuName; //学生名字
    private String courseName;//课程名字
    private int clazzId;//班级id



}
