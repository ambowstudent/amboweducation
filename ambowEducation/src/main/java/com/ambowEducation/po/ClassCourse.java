package com.ambowEducation.po;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassCourse {
    private Integer id;
    private Integer cId;
    private List<Integer> crIds;
}
