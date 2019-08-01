package com.ambowEducation.service;


import com.ambowEducation.dto.*;
import com.ambowEducation.po.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//学业导师
public interface TutorService {

    //通过导师编号 查询导师
    public Tutor queryTutorByEmpNo(String EmpNo);


    /**
     * 学生管理
     */
    //批量添加学生
    public void addStudents(List<StudentBaseInfoDto> list) throws Exception;

    //根据学生的sNo删除学生
    public void deleteStudent(String sNo) throws Exception;

    //条件查询
    public List<Student> queryStudentBysNo(String key, Integer tuId);

    //修改学生信息
    public void updateStudent(UpdateStudentInfoDto student) throws Exception;

    //查看学生的信息  用于修改学生信息
    public UpdateStudentInfoDto queryStudentBySId(Integer sId) throws Exception;

    //根据学号查询学生信息
    public Student queryStudentBysNo(String sNo) throws Exception;

    //查看所有学生信息
    public List<Student> queryAllStudent(Integer tuId) throws Exception;

    //给学生添加宿舍
    public void addDormitory(List<StudentDormitoryDto> list) throws Exception;

    public void addClazz(List<StudentClassDto> list) throws Exception;

    //查看就业追踪
    public List<Work> queryWorks(Integer sId) throws Exception;
    //添加就业追踪
    public void addWork(Work work) throws Exception;

    /**
     *招聘信息管理
     */
    //查看学业导师自己发布的全部招聘信息
    public List<Position> queryAllPositions(String tuEmpNo) throws Exception;

    //添加招聘信息
    public void addPosition(Position p) throws Exception;

    //根据id查询某条招聘信息
    public Position queryPositionById(Integer id) throws Exception;

    //修改某条招聘信息
    public void updatePosition(Position p) throws Exception;

    //招聘结束
    public void overPosition(Integer pId) throws Exception;

    //删除某条招聘信息
    public void deletePosition(Integer id) throws Exception;

    //查看已经报名的学生
    public List<Student> queryStudentSignup(Integer id) throws Exception;

    //根据公司名称，地点，职位  查询
    public List<Position> queryPositionsByKey(String key);

    public List<Position> queryPositionsByKeyAndTuEmpNo(String key, String tuEmpNo);
    /**
     * 学时管理
     */
    //查看所有学生的学时信息
    public List<StudentsHoursInfoDto> queryStudentsHoursInfo(Integer tuId) throws Exception;
    //老师查看自己添加的学时历史
    public List<HoursHistoryDto> queryHourHistory(Integer tuId,String key) throws Exception;
    //奖励/扣除 学时
    public void updateClassHours(History history) throws Exception;

    public List<StudentsHoursInfoDto> queryStudentsHoursInfoByKey(Integer id, String key);

    //将报名信息导出
    public String downloadSignupInfo(int pId,HttpServletResponse response, HttpServletRequest request);

    List<ReduceHours> getReduceHours();
}
