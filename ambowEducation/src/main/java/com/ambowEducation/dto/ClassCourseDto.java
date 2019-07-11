package com.ambowEducation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassCourseDto {

    private Integer id; //班级ID
    private Integer teId;   //技术教师ID
    private Integer tuId;   //导师ID
    private Integer roomId; //教室ID
    private String name;    //班级名称
    private String[] crId;      //课程编号

    private String roomName;   //教室门牌号
    private String teaName;     //技术老师名字
    private String tuName;      //学业导师名字

}
