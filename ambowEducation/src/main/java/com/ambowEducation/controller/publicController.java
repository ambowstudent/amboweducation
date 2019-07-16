package com.ambowEducation.controller;

import com.ambowEducation.dto.UserDto;
import com.ambowEducation.enumStatus.RbacStatus;
import com.ambowEducation.po.Role;
import com.ambowEducation.po.Student;
import com.ambowEducation.po.User;
import com.ambowEducation.service.StudentService;
import com.ambowEducation.service.TutorService;
import com.ambowEducation.service.UserService;
import com.ambowEducation.utils.JsonData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *  用于处理，登录，权限，等验证
 */
@RestController
@RequestMapping("/api/v1/pub")
public class publicController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TutorService tutorService;

    //没有登录
    @RequestMapping("need_login")
    public JsonData needLogin(){
        return JsonData.buildError(RbacStatus.NOT_LOGIN.getMessage(), RbacStatus.NOT_LOGIN.getCode());
    }
    //没有权限
    @RequestMapping("no_permission")
    public JsonData noPermission(){
        return JsonData.buildSuccess(RbacStatus.NOT_PERMIS.getMessage(), RbacStatus.NOT_PERMIS.getCode());
    }
    //登录
    @PostMapping("login")
    public JsonData login(@RequestBody()UserDto userDto, HttpServletRequest request, HttpServletResponse response){

        Map<String,Object> map = new HashMap<>();
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(userDto.getUsername(), userDto.getPassword());
        try{
            subject.login(token);
            //获取sessionid,传到前台
            Serializable sessionId = subject.getSession().getId();
            map.put("message", RbacStatus.SUCCESS_IN.getMessage());
            map.put("session_id", sessionId);
            map.put("code", RbacStatus.SUCCESS_IN.getCode());
            User user = userService.findByUsernameBasicInfo(userDto.getUsername());
            for(Role role:user.getRoles()){
                if("student".equals(role.getName())){
                    Student student = studentService.findBySno(userDto.getUsername());
                    user.setStudent(student);
                }else if("teacher".equals(role.getName())){

                }
            }
            request.getSession().setAttribute("user", userDto);
            return JsonData.buildSuccess(map);
        }catch (Exception e){
            map.put("message", RbacStatus.ERROR_IN.getMessage());
            map.put("code", RbacStatus.ERROR_IN.getCode());
            return JsonData.buildError(map);
        }
    }

}
