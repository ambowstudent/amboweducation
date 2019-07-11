package com.ambowEducation.service;


import com.ambowEducation.dto.*;
import com.ambowEducation.po.History;
import com.ambowEducation.po.Position;
import com.ambowEducation.po.Student;
import com.ambowEducation.po.Work;

import java.util.List;

//学业导师
public interface TutorService {
    /**
     * 学生管理
     */
    //批量添加学生
    public void addStudents(List<StudentBaseInfoDto> list);

    //根据学生的sNo删除学生
    public void deleteStudent(String sNo);

    //修改学生信息
    public void updateStudent(UpdateStudentInfoDto student);

    //查看学生的信息
    public Student queryStudentBySId(Integer sId);

    //查看所有学生信息
    public List<Student> queryAllStudent(Integer tuId);

    //给学生添加宿舍
    public void addDormitory(List<StudentDormitoryDto> list);

    //查看就业追踪
    public List<Work> queryWorks(Integer sId);
    //添加就业追踪
    public void addWork(Work work);

    /**
     *招聘信息管理
     */
    //查看学业导师自己发布的全部招聘信息
    public List<Position> queryAllPositions(String tuEmpNo);

    //根据id查询某条招聘信息
    public Position queryPositionById(Integer id);

    //修改某条招聘信息
    public void updatePosition(Position p);

    //招聘结束
    public void overPosition(Integer pId);


    //查看已经报名的学生
    public List<Student> queryStudentSignup(Integer id);

    /**
     * 学时管理
     */
    //老师查看自己添加的学时历史
    public List<HoursHistoryDto> queryHourHistory(Integer tuId,String key);
    //奖励/扣除 学时
    public void updateClassHours(History history);
}
