package com.ambowEducation.controller;

import com.ambowEducation.Exception.StudentGradeException;
import com.ambowEducation.dao.CourseMapper;
import com.ambowEducation.dto.StudentGradeDto;
import com.ambowEducation.po.Course;
import com.ambowEducation.po.Student;
import com.ambowEducation.po.StudentCourseGrade;
import com.ambowEducation.po.User;
import com.ambowEducation.service.StudentCourseGradeService;
import com.ambowEducation.utils.JsonData;
import com.ambowEducation.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/technical_teacher")
public class TechnicalTeacherController {

    @Autowired
    private StudentCourseGradeService studentCourseGradeService;

    @Autowired
    private CourseMapper courseMapper;

    //添加成绩
    @PostMapping("insert_grade")
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
    @PostMapping("modify_grade")
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

    @GetMapping("get_grade_info")
    public JsonData getGradeInfo(@RequestBody  StudentGradeDto studentGradeDto,@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        try {
            PageHelper.startPage(pageNum, PageUtil.PAGE_SIZE_GRADE);
            List<StudentCourseGrade> studentCourseGrades = studentCourseGradeService.findAllByManyCondition(studentGradeDto);
            PageInfo<StudentCourseGrade> pageInfo=new PageInfo<>(studentCourseGrades);
            return JsonData.buildSuccess(pageInfo);
        } catch (Exception e){
            e.printStackTrace();
            return JsonData.buildSuccess(e.getMessage());
        }
    }
    //老师带领下查看学生就业率
    @GetMapping("/get_student_pre_work")
    public JsonData getStudentPreWork(){
        //从session获取技术老师的id
        User user= (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        try {
            List<Map<String, Object>> studentWorkRateOfEmployment = studentCourseGradeService.findStudentWorkRateOfEmployment(user.getTechnicalTeacher().getId());
            return JsonData.buildSuccess(studentWorkRateOfEmployment);
        }catch (StudentGradeException e){
            return JsonData.buildError(e.getMessage());
        }catch (Exception e) {
         return JsonData.buildError(e.getMessage());
        }
    }
    //查询学生的基本信息
    @GetMapping("/get_basic_student")
    public JsonData getBasicStudent(@RequestParam("stuNo") String stuNo){
        try {
            Student student = studentCourseGradeService.findStudentByStudentNo(stuNo);
            return JsonData.buildSuccess(student);
        } catch (Exception e) {
            return JsonData.buildError(e.getMessage());
        }
    }

//获取所有课程
    @GetMapping("/get_all_course")
    public JsonData getAllCourse(){
        List<Course> allCourse = courseMapper.findAllCourse();
        return JsonData.buildSuccess(allCourse);
    }

}
