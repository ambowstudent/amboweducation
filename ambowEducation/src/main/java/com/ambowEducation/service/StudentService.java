package com.ambowEducation.service;

import com.ambowEducation.po.Student;
import com.ambowEducation.po.StudentCourseGrade;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface StudentService {

    //通过学生id查询学生
    public Student findById(int sid);

    //查询单个学生信息通过sno
    public Student findBySno(String sno);

    //学生修改自己的图片
    public int updStudentPhoto(MultipartFile multipartFile, int id, HttpServletRequest request) throws Exception;

}
