package com.ambowEducation.controller;

import com.ambowEducation.Exception.StudentGradeException;
import com.ambowEducation.dto.StudentGradeDto;
import com.ambowEducation.service.StudentCourseGradeService;
import com.ambowEducation.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/technical_teacher")
public class TechnicalTeacherController {

    @Autowired
    private StudentCourseGradeService studentCourseGradeService;

    //添加成绩
    @PostMapping("/insert_grade")
    public JsonData insertStudentGrade(@RequestBody StudentGradeDto studentGradeDto){
        System.out.println(studentGradeDto);
        try {
            studentCourseGradeService.insertStudentCourse(studentGradeDto);
            return JsonData.buildSuccess("学生成绩添加成功");
        }catch (StudentGradeException e){
            return JsonData.buildError(e.getMessage());
        } catch (Exception e) {
            return JsonData.buildError(e.getMessage());
        }
    }
}
