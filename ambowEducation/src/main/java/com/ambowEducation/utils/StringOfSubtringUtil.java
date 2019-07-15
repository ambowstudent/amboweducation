package com.ambowEducation.utils;

//截取字符串的工具类
public class StringOfSubtringUtil {

    public static String customSubString(String perString){
        if(perString.length()<=4){
            return perString;
        }else{
            return perString.substring(0, 4);
        }
    }
}
