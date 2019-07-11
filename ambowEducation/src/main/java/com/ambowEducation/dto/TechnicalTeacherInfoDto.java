package com.ambowEducation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 这个类是用来封装管理员添加技术老师的dto
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TechnicalTeacherInfoDto {

    private Integer id; //技术教师ID
    private String empNo;   //技术教师编号
    private String name;    //技术教师姓名
    private Integer sex;     //技术教师性别
    private String skills;   //技术教师擅长的技术
    private Integer workingSeniority;    //技术教师工作年限

}
