package com.ambowEducation.service;

import com.ambowEducation.dto.ClassroomDto;
import com.ambowEducation.dto.ReduceHoursDto;
import com.ambowEducation.po.Classroom;
import com.ambowEducation.po.ReduceHours;

import java.util.List;

public interface AdminOtherService {

    //    添加扣除学时项
    void insertReduceHours(ReduceHoursDto reduceHoursDto);

    void updateReduceHours(ReduceHoursDto reduceHoursDto);

    void deleteReduceHours(ReduceHoursDto reduceHoursDto);

    List<ReduceHours> selectReduceHours();


    void insertClassroom(ClassroomDto classroomDto);

    void updateClassroom(ClassroomDto classroomDto);

    void deleteClassroom(ClassroomDto classroomDto);

    List<Classroom> selectClassroom();

}
