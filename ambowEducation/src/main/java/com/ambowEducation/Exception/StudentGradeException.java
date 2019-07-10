package com.ambowEducation.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentGradeException extends RuntimeException{
    /*
        -1 对象不存在
        -2 学生不存在
        -3 成绩插入失败
        -4 成绩更新失败
         0 未知异常
     */


    private Integer code;
    private String message;

}
