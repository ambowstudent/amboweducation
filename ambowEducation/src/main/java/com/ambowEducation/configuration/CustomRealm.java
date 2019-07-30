package com.ambowEducation.configuration;

import com.ambowEducation.po.*;
import com.ambowEducation.service.StudentService;
import com.ambowEducation.service.TeacherService;
import com.ambowEducation.service.TutorService;
import com.ambowEducation.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService service;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TutorService tutorService;

    @Autowired
    private TeacherService teacherService;



    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User userNew =(User) principalCollection.getPrimaryPrincipal();
        User user = service.findAllUserAndRoleAndPer(userNew.getUsername());
        List<String> stringRole=new ArrayList<>();
        List<String> stringPermission=new ArrayList<>();
        for(Role role:user.getRoles()){
            if(role!=null){
                stringRole.add(role.getName());
            }
        }
        System.out.println(stringRole);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(stringPermission);
        info.addRoles(stringRole);
        return info;
    }

    //登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    String username=(String) authenticationToken.getPrincipal();
        User user = service.findAllUserAndRoleAndPer(username);
        if(user==null||user.getPassword()==null||"".equals(user.getPassword())){
            return null;
        }
        //查询到之后再去查询权限。存到主体中
        for(Role role:user.getRoles()){
            if("student".equals(role.getName())){
                Student student = studentService.findBySno(user.getUsername());
                user.setStudent(student);
            }else if("tutor".equals(role.getName())){
                Tutor tutor = tutorService.queryTutorByEmpNo(user.getUsername());
                user.setTutor(tutor);
            }else if("teacher".equals(role.getName())){
                TechnicalTeacher technicalTeacher = teacherService.selectTeacherByEmpNo(user.getUsername());
                user.setTechnicalTeacher(technicalTeacher);
            }
            break;
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
