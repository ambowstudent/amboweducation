package com.ambowEducation.controller;

import com.ambowEducation.Exception.SignupPositionException;
import com.ambowEducation.po.History;
import com.ambowEducation.po.Position;
import com.ambowEducation.po.StudentCourseGrade;
import com.ambowEducation.service.*;
import com.ambowEducation.utils.JsonData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Resource
    private StudentService studentService;
    @Resource
    private PositionService positionService;
    @Resource
    private HistoryService historyService;
    @Resource
    private StudentCourseGradeService studentCourseGradeService;
    @Resource
    private SignupPositionService signupPositionService;


    //历史表t_history 增、根据学生id查所有的记录(学生查自己的),id+关键字(原因)查询记录(学生查自己的)
    @GetMapping("/findStuHistory")
    public JsonData findStuHistory(@RequestParam("s_no") String sNo,@RequestParam(defaultValue = "") String key){
        return JsonData.buildSuccess(historyService.findMyHistory(key, sNo));
    }

    //成绩表t_student_course_grade 根据学生id
    @GetMapping("/findStudentGrade")
    public JsonData findStudentGrade(@RequestParam("s_id") int sId){
            List<StudentCourseGrade> list = studentCourseGradeService.findMyGrade(sId);
            return JsonData.buildSuccess(list);
    }

    //涉及到课程表t_course 查询课程名称 id+关键字（课程名）查询
    @GetMapping("/findStudentGradeByCid")
    public JsonData findStudentGradeByCid(@RequestParam("s_id") int sId, @RequestParam("c_id") int cId){
        StudentCourseGrade studentCourseGrade = studentCourseGradeService.findMyGradeByKey(sId, cId);
        return JsonData.buildSuccess(studentCourseGrade);
    }

    //招聘信息表 t_position 查询所有招聘信息
    @GetMapping("/findAllPosition")
    public JsonData findAllPosition(){
        List<Position> list = positionService.findAll();
        return JsonData.buildSuccess(list);
    }

    //学生通过position,location,company_name查询招聘信息
    @GetMapping("/findPositionByKey")
    public JsonData findPositionByKey(@RequestParam("key") String key){
        List<Position> list = positionService.findAllByLike(key);
        return JsonData.buildSuccess(list);
    }

    //学生报名对应的职位
    @PostMapping("/studentSignupPosition")
    public JsonData studentSignupPosition(@RequestParam("s_id") int sId, @RequestParam("p_id") int pId){
        try {
            signupPositionService.IsHasPosition(sId, pId);
            return JsonData.buildSuccess("已报名");
        } catch (SignupPositionException e) {
            return JsonData.buildError(e.getMessage());
        } catch (Exception e){
            return JsonData.buildError(e.getMessage());
        }
    }

    //学生查看个人信息
    @GetMapping("/findMyInfo")
    public JsonData findMyInfo(@RequestParam("s_no") String sNo){
        return JsonData.buildSuccess(studentService.findBySno(sNo));
    }

}
