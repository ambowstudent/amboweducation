package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.TechnicalTeacherException;
import com.ambowEducation.Exception.TutorException;
import com.ambowEducation.dao.TechnicalTeacherMapper;
import com.ambowEducation.dao.TutorMapper;
import com.ambowEducation.dto.TechnicalTeacherInfoDto;
import com.ambowEducation.dto.TutorInfoDto;
import com.ambowEducation.po.TechnicalTeacher;
import com.ambowEducation.po.Tutor;
import com.ambowEducation.service.AdminTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminTeacherTeacherServiceImpl implements AdminTeacherService {

    @Autowired
    private TechnicalTeacherMapper teacherMapper;

    @Autowired
    private TutorMapper tutorMapper;

    /**
     * 技术老师
     * @param teacherInfoDto
     */

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertTechinicalTeacher(TechnicalTeacherInfoDto teacherInfoDto) {
//        获取 TechnicalTeacherInfoDto 对象查看是否获取到参数
        if(teacherInfoDto==null){
            throw new TutorException(-1,"我把对象弄丢了");
        }
//        查看 技术教师编号 EmpNo 是否存在 避免重复
        TechnicalTeacher teacher = teacherMapper.selectTeacherByEmpNo(teacherInfoDto.getEmpNo());
        if(teacher!=null){
            throw new TutorException(-2,"此教师编号已被使用");
        }
//        创建实体类并将Dto类中的参数传递过去
        TechnicalTeacher technicalTeacher = new TechnicalTeacher();
        BeanUtils.copyProperties(teacherInfoDto,technicalTeacher);
        int result = teacherMapper.insertTechnicalTeacher(technicalTeacher);
        if (result<1){
            throw new TechnicalTeacherException(-3,"添加失败");
        }
        System.out.println("技术老师添加成功");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateTechnicalTeacher(TechnicalTeacherInfoDto teacherInfoDto) {

        if(teacherInfoDto==null){
            throw new TutorException(-1,"对象不存在");
        }

        TechnicalTeacher teacher = new TechnicalTeacher();
        BeanUtils.copyProperties(teacherInfoDto,teacher);

        int result = teacherMapper.updateTechnicalTeacher(teacher);
        if (result<1){
            throw new TechnicalTeacherException(-4,"修改失败");
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteTechnicalTeacher(TechnicalTeacherInfoDto teacherInfoDto) {
        if(teacherInfoDto==null){
            throw new TutorException(-1,"对象不存在");
        }

        TechnicalTeacher teacher = new TechnicalTeacher();
        BeanUtils.copyProperties(teacherInfoDto,teacher);

        int result = teacherMapper.deleteTechnicalTeacher(teacher.getId());
        if (result<1){
            throw new TechnicalTeacherException(-5,"删除失败");
        }
    }

    @Override
    public List<TechnicalTeacher> selectTechnicalTeacher() {
        return teacherMapper.selectTechnicalTeacherList();
    }

    @Override
    public TechnicalTeacher selectTechnicalTeacherById(TechnicalTeacherInfoDto teacherInfoDto) {
        if(teacherInfoDto==null){
            throw new TechnicalTeacherException(-1,"对象不存在");
        }
        return teacherMapper.selectTechnicalTeacher(teacherInfoDto.getId());
    }

    /**
     * 学业导师
     * @param tutorInfoDto
     */

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertTutor(TutorInfoDto tutorInfoDto) {

        if(tutorInfoDto==null){
            throw new TechnicalTeacherException(-1,"对象不存在");
        }

        Tutor tu = tutorMapper.selectTutorByEmpNo(tutorInfoDto.getEmpNo());
        if(tu!=null){
            throw new TechnicalTeacherException(-2,"此导师编号已被使用");
        }

        Tutor tutor = new Tutor();
        BeanUtils.copyProperties(tutorInfoDto,tutor);

        int result = tutorMapper.insertTutor(tutor);
        if (result<1){
            throw new TechnicalTeacherException(-3,"添加失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateTutor(TutorInfoDto tutorInfoDto) {

        if (tutorInfoDto==null){
            throw new TechnicalTeacherException(-1,"你对象怎么没带来？");
        }

        Tutor tutor = new Tutor();
        BeanUtils.copyProperties(tutorInfoDto,tutor);

        int result = tutorMapper.updateTutor(tutor);
        if (result<1){
            throw new TechnicalTeacherException(-4,"修改失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteTutor(TutorInfoDto tutorInfoDto) {

        if (tutorInfoDto==null){
            throw new TechnicalTeacherException(-1,"对象不存在");
        }

        Tutor tutor = new Tutor();
        BeanUtils.copyProperties(tutorInfoDto,tutor);

        int result = tutorMapper.deleteTutor(tutor.getId());
        if (result<1){
            throw new TechnicalTeacherException(-5,"删除失败");
        }
    }

    @Override

    public List<Tutor> selectTutors() {

        return tutorMapper.selectTutorList();

    }

    @Override
    public Tutor selectTutorById(TutorInfoDto tutorInfoDto) {
        if (tutorInfoDto==null){
            throw new TutorException(-1,"对象不存在");
        }

        return tutorMapper.selectTutor(tutorInfoDto.getId());
    }
}
