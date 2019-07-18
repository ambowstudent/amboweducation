package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.StudentException;
import com.ambowEducation.dao.StudentMapper;
import com.ambowEducation.po.Student;
import com.ambowEducation.service.StudentService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

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
        return studentMapper.findStudentBySnoOnlyStudent(sno);
    }

    @Override
    public int updStudentPhoto(MultipartFile multipartFile, int id) throws Exception {
        int flag = 0;
        if (multipartFile != null){
            String fileName = multipartFile.getOriginalFilename();
            System.out.println(fileName + "---------" + id);
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            if (multipartFile.getSize() > 10000000){
                throw new StudentException(3, "图片不能大于10M");
            }
            if (suffix.equalsIgnoreCase(".png") || suffix.equalsIgnoreCase(".jpg")){
                String uuid = UUID.randomUUID().toString();
                FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File("G:\\学习资料\\" + uuid + suffix));
                flag = studentMapper.updStudentPhoto(uuid + suffix, id);
            }else {
                throw new StudentException(1, "上传格式错误");
            }
        }else {
            throw new StudentException(2, "上传文件为空");
        }
        return flag;
    }
}
