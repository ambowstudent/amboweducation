package com.ambowEducation.controller;

import com.ambowEducation.dto.UserDto;
import com.ambowEducation.enumStatus.RbacStatus;
import com.ambowEducation.service.UserService;
import com.ambowEducation.utils.JsonData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *  用于处理，登录，权限，等验证
 */

@RestController
@RequestMapping("/api/pub")
public class publicController {

    @Autowired
    private UserService userService;


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
            return JsonData.buildSuccess(map);
        }catch (Exception e){
            map.clear();
            e.printStackTrace();
            map.put("message", RbacStatus.ERROR_IN.getMessage());
            map.put("code", RbacStatus.ERROR_IN.getCode());
            return JsonData.buildError(map);
        }
    }

}
