package com.ambowEducation.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupPosition {
    private Integer id;
    private Integer pId;//职位id
    private Integer sId;//学生id

    private Student student;//学生
    private Position position;//职位

}
