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
  //  private String stuName; //学生姓名
    private Integer courseId; //课程id
    private Double grade; //学生成绩
}
