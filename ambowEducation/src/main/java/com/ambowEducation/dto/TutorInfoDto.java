package com.ambowEducation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 这个类是用来封装管理员添加导师的dto
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TutorInfoDto {

    private Integer id; //ID
    private String empNo;   //导师编号
    private String name;    //导师姓名
    private String sex;     //导师性别
    private String time;    //导师工作年限

}
