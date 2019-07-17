package com.ambowEducation.controller;

import com.ambowEducation.Exception.StudentGradeException;
import com.ambowEducation.dto.StudentGradeDto;
import com.ambowEducation.service.StudentCourseGradeService;
import com.ambowEducation.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/technical_teacher")
@CrossOrigin(origins="*",maxAge = 3600)
public class TechnicalTeacherController {

    @Autowired
    private StudentCourseGradeService studentCourseGradeService;

    //添加成绩
    @PostMapping("/insert_grade")
    public JsonData insertStudentGrade(@RequestBody StudentGradeDto studentGradeDto){
        try {
            studentCourseGradeService.insertStudentCourse(studentGradeDto);
            return JsonData.buildSuccess("学生成绩添加成功");
        }catch (StudentGradeException e){
            return JsonData.buildError(e.getMessage());
        } catch (Exception e) {
            return JsonData.buildError(e.getMessage());
        }
    }
    //修改学生成绩
    @PostMapping("/modify_grade")
    public JsonData updateStudentGrade(@RequestBody StudentGradeDto studentGradeDto){

        try {
            studentCourseGradeService.modifyStudentCourseByStuId(studentGradeDto);
            return JsonData.buildSuccess("学生成绩修改成功");
        }catch (StudentGradeException e){
            return JsonData.buildError(e.getMessage());
        } catch (Exception e) {
            return JsonData.buildError(e.getMessage());
        }
    }
    //根据学号跟课程id查询学生信息以及分数

    @GetMapping("/get_grade_info")
    public JsonData getGradeInfo(StudentGradeDto studentGradeDto){
        try {
            return JsonData.buildSuccess(studentCourseGradeService.findAllByManyCondition(studentGradeDto));
        } catch (Exception e){
            return JsonData.buildSuccess(e.getMessage());
        }
    }
    //查看学生就业率
    @GetMapping("get_student_pre_work")
    public JsonData getStudentPreWork(int teachId){
        //从session获取技术老师的id
        try {
            List<Map<String, Object>> studentWorkRateOfEmployment = studentCourseGradeService.findStudentWorkRateOfEmployment(teachId);
            return JsonData.buildSuccess(studentWorkRateOfEmployment);
        }catch (StudentGradeException e){
            return JsonData.buildError(e.getMessage());
        }catch (Exception e) {
         return JsonData.buildError(e.getMessage());
        }
    }
}
