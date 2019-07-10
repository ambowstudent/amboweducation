package com.ambowEducation.service.impl;
import com.ambowEducation.Exception.StudentGradeException;
import com.ambowEducation.dao.StudentCourseGradeMapper;
import com.ambowEducation.dao.StudentMapper;
import com.ambowEducation.dto.StudentGradeDto;
import com.ambowEducation.po.Student;
import com.ambowEducation.po.StudentCourseGrade;
import com.ambowEducation.service.StudentCourseGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentCourseGradeServiceImpl implements StudentCourseGradeService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentCourseGradeMapper studentCourseGradeMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertStudentCourse(StudentGradeDto studentGradeDto) {
        if(studentGradeDto==null){
            //该对象不存在直接抛异常
            throw new StudentGradeException(-1,"对象不存在");
        }
        //根据学生学号查询学生的id
        Student student = studentMapper.findStudentBySno(studentGradeDto.getStuNo());
        if(student==null){
            throw new StudentGradeException(-2,"学生不存在");
        }
        //调用mapper封装
        StudentCourseGrade studentCourseGrade=new StudentCourseGrade();
        studentCourseGrade.setSId(student.getId());
        studentCourseGrade.setCrId(studentGradeDto.getCourseId());
        studentCourseGrade.setGrade(studentGradeDto.getGrade());
        int i = studentCourseGradeMapper.insertStudentGrade(studentCourseGrade);
        if(i<1){
            throw new StudentGradeException(-3,"学生成绩添加失败");
        }
        System.out.println("学生成绩添加成功");
    }

    //学生查询自己的全部成绩
    @Override
    public List<StudentCourseGrade> findMyGrade(int studentId) {
        return studentCourseGradeMapper.findOneStudentAllGrade(studentId);
    }

    //学生查询自己的某个成绩
    @Override
    public StudentCourseGrade findMyGradeForCourse(int sid, int courseId) {
        return studentCourseGradeMapper.findOneStudentOneGrade(sid, courseId);
    }
}
