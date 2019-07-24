package com.ambowEducation.utils;

import com.ambowEducation.po.User;

public class RealNameUtils {

    public static String getRealName(User user){
        if(user.getStudent()!=null){
            return user.getStudent().getName();
        }else if(user.getTechnicalTeacher()!=null){
            return user.getTechnicalTeacher().getName();
        }else if(user.getTutor()!=null){
            return user.getTutor().getName();
        }else {
            return "admin";
        }
    }
}
