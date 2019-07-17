package com.ambowEducation.utils;

import com.ambowEducation.Exception.TutorException;
import com.ambowEducation.po.Tutor;

public class FileSuffixUtil {


    public static boolean checkFile(String fileName) throws Exception{
        boolean flag=false;
        String[] split = fileName.split("\\.");
        if(split.length!=2){
            throw new TutorException(-1,"文件路径解析失败");
        }
        if("xls".equals(split[1]) || "xlsx".equals(split[1])){
            flag=true;
        }
        return flag;
    }

}
