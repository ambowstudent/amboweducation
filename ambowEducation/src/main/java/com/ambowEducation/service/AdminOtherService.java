package com.ambowEducation.service;

import com.ambowEducation.dto.ClassroomDto;
import com.ambowEducation.dto.ReduceHoursDto;
import com.ambowEducation.po.Classroom;
import com.ambowEducation.po.ReduceHours;

import java.util.List;

public interface AdminOtherService {

    /**
     * 扣除学时情况
     * @param reduceHoursDto
     */
    //    添加扣除学时项
    void insertReduceHours(ReduceHoursDto reduceHoursDto);
//    修改扣除学时项
    void updateReduceHours(ReduceHoursDto reduceHoursDto);
//    删除扣除学时项
    void deleteReduceHours(ReduceHoursDto reduceHoursDto);
//    查询扣除学时项
    List<ReduceHours> selectReduceHours(String name);
//    通过ID查询扣除学时项
    ReduceHours selectReduceHoursById(ReduceHoursDto reduceHoursDto);

    /**
     * 教室资源
     * @param classroomDto
     */
//    添加新教室
    void insertClassroom(ClassroomDto classroomDto);
//    修改教室信息
    void updateClassroom(ClassroomDto classroomDto);
//    删除教室信息
    void deleteClassroom(ClassroomDto classroomDto);
//    查询教室信息
    List<Classroom> selectClassroom(String roomNumber);
//    通过ID查询教室信息
    Classroom selectClassroomById(ClassroomDto classroomDto);

}
