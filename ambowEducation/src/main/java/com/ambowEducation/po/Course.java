package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable{

//    课程id
    private Integer id;
//    课程名称
    private String name;

    private StudentCourseGrade grade;
    private List<ClassType> cTypes;
}