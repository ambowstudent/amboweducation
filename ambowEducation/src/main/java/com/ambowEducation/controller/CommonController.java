package com.ambowEducation.controller;

import com.ambowEducation.Exception.StudentGradeException;
import com.ambowEducation.enumStatus.RbacStatus;
import com.ambowEducation.po.User;
import com.ambowEducation.service.StudentCourseGradeService;
import com.ambowEducation.utils.JsonData;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/common")
public class CommonController {

    @Autowired
    private StudentCourseGradeService studentCourseGradeService;


    //查看所有学生就业率
    @GetMapping("get_all_student_per")
    public JsonData getAllStudentPreWork(){
        try {
            List<Map<String, Object>> percent = studentCourseGradeService.findAllStudentWorkPercent();
            return JsonData.buildSuccess(percent);
        }catch (StudentGradeException e){
            return JsonData.buildError(e.getMessage());
        }catch (Exception e) {
            return JsonData.buildError(e.getMessage());
        }
    }
    //查询近三年共工资
    @GetMapping("get_all_student_sal")
    public JsonData getThreeYearSal(){

        try {
            Map<String,List<Map<String,Object>>> maps = studentCourseGradeService.selectThreeYearSal();
            return JsonData.buildSuccess(maps);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError(e.getMessage());
        }
    }
    @GetMapping("logout")
    public JsonData logout(){
        User user= (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        if(user==null){
            return JsonData.buildError("退出失败");
        }
        SecurityUtils.getSubject().logout();
      return JsonData.buildSuccess(RbacStatus.SUCCESS_OUT.getMessage());
    }
}
