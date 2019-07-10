package com.ambowEducation.service.impl;

import com.ambowEducation.dao.StudentMapper;
import com.ambowEducation.po.Student;
import com.ambowEducation.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Student findById(int sid) {
        return studentMapper.queryStudentById(sid);
    }

    @Override
    public Student findBySno(String sno) {
        return studentMapper.findStudentBySno(sno);
    }
}
