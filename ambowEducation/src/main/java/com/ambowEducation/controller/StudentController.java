package com.ambowEducation.controller;

import com.ambowEducation.Exception.SignupPositionException;
import com.ambowEducation.Exception.StudentException;
import com.ambowEducation.po.History;
import com.ambowEducation.po.Position;
import com.ambowEducation.po.StudentCourseGrade;
import com.ambowEducation.po.User;
import com.ambowEducation.service.*;
import com.ambowEducation.utils.JsonData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
@CrossOrigin(origins="*",maxAge = 3600)
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
    public JsonData findStuHistory(HttpSession session, @RequestParam(defaultValue = "") String key, @RequestParam(defaultValue = "1") int pageNum){
        User user= (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        PageHelper.startPage(pageNum, 2);
        List<History> list = historyService.findMyHistory(key, user.getStudent().getSNo());
        PageInfo<History> pageInfo = new PageInfo<>(list);
        return JsonData.buildSuccess(pageInfo);
    }

    //查询成绩表t_student_course_grade 根据学生id
    @GetMapping("/findStudentGrade")
    public JsonData findStudentGrade(HttpSession session, @RequestParam(defaultValue = "1") int pageNum){
            PageHelper.startPage(pageNum, 2);
            User user= (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            List<StudentCourseGrade> list = studentCourseGradeService.findMyGrade(user.getStudent().getId());
            PageInfo<StudentCourseGrade> pageInfo = new PageInfo<>(list);
            return JsonData.buildSuccess(pageInfo);
    }

    //涉及到课程表t_course 查询课程名称 id+关键字（课程名）查询
    @GetMapping("/findStudentGradeByCid")
    public JsonData findStudentGradeByCid(HttpSession session, @RequestParam("c_id") int cId){
        User user= (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        StudentCourseGrade studentCourseGrade = studentCourseGradeService.findMyGradeByKey(user.getStudent().getId(), cId);
        return JsonData.buildSuccess(studentCourseGrade);
    }

    //招聘信息表 t_position 查询所有招聘信息
    @GetMapping("/findAllPosition")
    public JsonData findAllPosition(@RequestParam(defaultValue = "1") int pageNum){
        PageHelper.startPage(pageNum, 2);
        List<Position> list = positionService.findAll();
        PageInfo<Position> pageInfo = new PageInfo<>(list);
        return JsonData.buildSuccess(pageInfo);
    }

    //学生通过position,location,company_name查询招聘信息
    @GetMapping("/findPositionByKey")
    public JsonData findPositionByKey(@RequestParam("key") String key, @RequestParam(defaultValue = "1") int pageNum){
        PageHelper.startPage(pageNum, 2);
        List<Position> list = positionService.findAllByLike(key);
        PageInfo<Position> pageInfo = new PageInfo<>(list);
        return JsonData.buildSuccess(pageInfo);
    }

    //学生报名对应的职位
    @PostMapping("/studentSignupPosition")
    public JsonData studentSignupPosition(HttpSession session, @RequestParam("p_id") int pId){
        User user= (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();

        try {
            signupPositionService.IsHasPosition(user.getStudent().getId(), pId);
            return JsonData.buildSuccess("已报名");
        } catch (SignupPositionException e) {
            return JsonData.buildError(e.getMessage());
        } catch (Exception e){
            return JsonData.buildError(e.getMessage());
        }
    }

    //学生查看个人信息
    @GetMapping("/findMyInfo")
    public JsonData findMyInfo(HttpSession session){
        User user= (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        return JsonData.buildSuccess(studentService.findBySno(user.getStudent().getSNo()));
    }

    //学生修改自己的图片
    @PostMapping("/uploadStudent")
    public JsonData uploadStudent(HttpSession session, @RequestParam("file") MultipartFile multipartFile, HttpServletRequest request){
        User user= (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        System.out.println(user);
        System.out.println(user.getStudent());
        int id=user.getStudent().getId();
       // System.out.println(id);
        try {
            studentService.updStudentPhoto(multipartFile, id, request);
            return JsonData.buildSuccess("上传成功");
        } catch (StudentException e) {
            return JsonData.buildError(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
            return JsonData.buildError(e.getMessage());
        }
    }

}
