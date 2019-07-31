package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.StudentException;
import com.ambowEducation.dao.StudentMapper;
import com.ambowEducation.po.Student;
import com.ambowEducation.service.StudentService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public int updStudentPhoto(MultipartFile multipartFile, int id, HttpServletRequest request) throws Exception {
        int flag = 0;
        //判断文件是否为空
        if (multipartFile != null){
            String fileName = multipartFile.getOriginalFilename();
            System.out.println(fileName + "---------" + id);
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            //判断文件大小
            if (multipartFile.getSize() > 10000000){
                throw new StudentException(3, "图片不能大于10M");
            }
            //判断文件格式
            if (suffix.equalsIgnoreCase(".png") || suffix.equalsIgnoreCase(".jpg")){
                //如果是windows系统
                if (System.getProperties().getProperty("os.name").toLowerCase().startsWith("win")){
                    File dir = new File("E:\\studnet\\" + id);
                    //如果不存在就创建一个
                    if (!dir.exists()){
                        dir.mkdirs();
                    }
                    String uuid = UUID.randomUUID().toString();
                    FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(dir + "\\" + uuid + suffix));
                    flag = studentMapper.updStudentPhoto(dir + "\\" + uuid + suffix, id);
                }else { //如果是Linux系统
                    String path = "/usr/local/static/student/head_img";
                    File dir = new File(path + "/" + id);
                    //如果不存在就创建一个
                    if (!dir.exists()){
                        dir.mkdirs();
                    }
                    String uuid = UUID.randomUUID().toString();
                    FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), new File(dir + "/" + uuid + suffix));
                    flag = studentMapper.updStudentPhoto(dir + "/" + uuid + suffix, id);
                }
            }else { //如果文件格式错误
                throw new StudentException(1, "上传格式错误");
            }
        }else { //如果文件为空
            throw new StudentException(2, "上传文件为空");
        }
        return flag;
    }
}
