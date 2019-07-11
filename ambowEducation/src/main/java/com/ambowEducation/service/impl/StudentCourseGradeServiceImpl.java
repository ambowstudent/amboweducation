package com.ambowEducation.service.impl;
import com.ambowEducation.Exception.StudentGradeException;
import com.ambowEducation.dao.StudentCourseGradeMapper;
import com.ambowEducation.dao.StudentMapper;
import com.ambowEducation.dao.WorkMapper;
import com.ambowEducation.dto.StudentGradeDto;
import com.ambowEducation.po.Student;
import com.ambowEducation.po.StudentCourseGrade;
import com.ambowEducation.service.StudentCourseGradeService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class StudentCourseGradeServiceImpl implements StudentCourseGradeService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentCourseGradeMapper studentCourseGradeMapper;

    @Autowired
    private WorkMapper workMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertStudentCourse(StudentGradeDto studentGradeDto) throws Exception {
        if(studentGradeDto==null||studentGradeDto.getStuNo()==null){
            //该对象不存在直接抛异常
            throw new StudentGradeException(-1,"对象不存在");
        }
        //根据学生学号查询学生的id
        Student student = studentMapper.findStudentBySnoOnlyStudent(studentGradeDto.getStuNo());
        if(student==null){
            throw new StudentGradeException(-2,"学生不存在");
        }
        //调用mapper封装
        StudentCourseGrade studentCourseGrade=new StudentCourseGrade();
        try {
            PropertyUtils.copyProperties(studentCourseGrade,studentGradeDto);
            studentCourseGrade.setSId(student.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = studentCourseGradeMapper.insertStudentGrade(studentCourseGrade);
        if(i<1){
            throw new StudentGradeException(-3,"学生成绩添加失败");
        }
    }

    @Override
    public Student findStudentByStudentNo(String stuNo) {

        Student student = studentMapper.findStudentBySnoOnlyStudent(stuNo);
        if(student==null){
            throw new StudentGradeException(-2, "学生不存在");
        }
        return student;
    }

    @Override
    public void modifyStudentCourseByStuId(StudentGradeDto studentGradeDto) throws Exception{
        if(studentGradeDto==null&&studentGradeDto.getSId()==null
        ){
            throw new StudentGradeException(-1, "对象不存在");
        }
        StudentCourseGrade courseGrade=new StudentCourseGrade();
        try {
            PropertyUtils.copyProperties(courseGrade,studentGradeDto );
        } catch (Exception e) {
            e.printStackTrace();
        }

        int i = studentCourseGradeMapper.updateStudentGrade(courseGrade);
        if(i<1){
            throw new StudentGradeException(-4, "成绩更新失败");
        }
        System.out.println("成绩更新成功");
    }

    @Override
    public List<StudentCourseGrade> findAllByManyCondition(StudentGradeDto studentGradeDto) throws Exception{
        List<StudentCourseGrade> studentCourseGrades = studentCourseGradeMapper.findAllByManyCondition(studentGradeDto.getStuNo(), studentGradeDto.getStuName(), studentGradeDto.getSchool(), studentGradeDto.getCourseName());
        return studentCourseGrades;
    }

    @Override
    public List<Map<String,Object>> findStudentWorkRateOfEmployment() throws Exception{

        //查询出所有的类型的人数
        List<Map<String, Object>> maps = workMapper.selectEveryTypeCount();

        //求出总条数
        int count = workMapper.selectAllCount();

        for (Map map:
             maps) {
            long num = (long) map.get("num");
            String pre=new Double(num)/count+"";
            map.put("num", pre.substring(0, 4));
        }
        return maps;
    }


}
