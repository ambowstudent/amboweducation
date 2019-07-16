package com.test;

import com.ambowEducation.dao.WorkMapper;
import com.ambowEducation.dto.StudentGradeDto;
import com.ambowEducation.po.Student;
import com.ambowEducation.po.StudentCourseGrade;
import com.ambowEducation.service.StudentCourseGradeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class StudentCourseGradeServiceTest extends BaseTest {
    @Autowired
    private StudentCourseGradeService studentCourseGradeService;

    @Autowired
    private WorkMapper workMapper;

    @Test
    public void insertStudentCourse() throws Exception {
        StudentGradeDto studentGradeDto=new StudentGradeDto();
        studentGradeDto.setStuNo("201901005");
        studentGradeDto.setCrId(8);
        studentGradeDto.setGrade(99d);
        studentCourseGradeService.insertStudentCourse(studentGradeDto);
    }
    @Test
    public void findStudentByStudentNo() throws Exception {
        Student student = studentCourseGradeService.findStudentByStudentNo("201901003");
        System.out.println(student);
    }
    @Test
    public void modifyStudentCourseByStuId() throws Exception {
        StudentGradeDto studentGradeDto=new StudentGradeDto();
        studentGradeDto.setSId(6);
        studentGradeDto.setCrId(8);
        studentGradeDto.setGrade(59d);
        studentCourseGradeService.modifyStudentCourseByStuId(studentGradeDto);
    }
    @Test
    public void findAllByManyCondition() throws Exception {
        StudentGradeDto studentGradeDto=new StudentGradeDto();
        studentGradeDto.setStuNo("");
        studentGradeDto.setSchool("");
        studentGradeDto.setStuName("");
        studentGradeDto.setCourseName("");
        studentGradeDto.setClazzId(3);
        List<StudentCourseGrade> studentCourseGrades = studentCourseGradeService.findAllByManyCondition(studentGradeDto);
        for (StudentCourseGrade studentCourseGrade:
                studentCourseGrades) {
            System.out.println(studentCourseGrade);

        }

    }
    @Test
    public void selectEveryTypeCountByTechId(){
        List<Map<String, Object>> maps = workMapper.selectEveryTypeCountByTechId(4);
        System.out.println(maps );
    }



    @Test
    public void test() throws Exception {
        List<Map<String,Object>> employment = studentCourseGradeService.findStudentWorkRateOfEmployment(4);
        for (Map<String,Object> Work:
                employment) {
            System.out.println(Work);

        }
    }
    @Test
    public void test2(){
        int a=0x112;
        System.out.println(0x61/2);
    }
}
