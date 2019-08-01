package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.TechnicalTeacherException;
import com.ambowEducation.Exception.TutorException;
import com.ambowEducation.dao.RoleMapper;
import com.ambowEducation.dao.TechnicalTeacherMapper;
import com.ambowEducation.dao.TutorMapper;
import com.ambowEducation.dao.UserMapper;
import com.ambowEducation.dto.TechnicalTeacherInfoDto;
import com.ambowEducation.dto.TutorInfoDto;
import com.ambowEducation.po.TechnicalTeacher;
import com.ambowEducation.po.Tutor;
import com.ambowEducation.po.User;
import com.ambowEducation.service.AdminTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AdminTeacherTeacherServiceImpl implements AdminTeacherService {

    @Autowired
    private TechnicalTeacherMapper teacherMapper;

    @Autowired
    private TutorMapper tutorMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

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
//        向技术老师表添加
        int result_insertTTeacher = teacherMapper.insertTechnicalTeacher(technicalTeacher);
//        向T_User表添加
//        创建User对象
        User user = new User();
        user.setUsername(teacherInfoDto.getEmpNo());
        user.setPassword("123456");
        user.setCreatetime(new Date());
        int result_TUser = userMapper.insertUser(user);
//        向T_User_Role表添加
        user = userMapper.findByName(user.getUsername());
        int reult_TURole = roleMapper.insertUserRole(user.getId(),3,"技术老师");
        if (result_insertTTeacher<1&&result_TUser<1&&reult_TURole<1){
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
//        查询要删除的老师
        TechnicalTeacher technicalTeacher = teacherMapper.selectTechnicalTeacher(teacher.getId());
//        查询要删除的角色
        User u = userMapper.findByName(technicalTeacher.getEmpNo());
//        删除该老师在role中的内容
        int result_deleteRole = roleMapper.deleteUserRole(u.getId());
//        删除该老师在User中的内容
        int result_deleteUser = userMapper.deleteUser(technicalTeacher.getEmpNo());
        int result = teacherMapper.deleteTechnicalTeacher(teacher.getId());
        if (result<1&&result_deleteUser<1&&result_deleteRole<1){
            throw new TechnicalTeacherException(-5,"删除失败");
        }
    }

    @Override
    public List<TechnicalTeacher> selectTechnicalTeacher(String empNo) {
//        return teacherMapper.selectTechnicalTeacherList();
        return teacherMapper.selectTechnicalTeacherListByKey(empNo);
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
//        向T_tutor表添加
        int result = tutorMapper.insertTutor(tutor);
        //        向T_User表添加
//        创建User对象
        User user = new User();
        user.setUsername(tutorInfoDto.getEmpNo());
        user.setPassword("123456");
        user.setCreatetime(new Date());
        int result_TUser = userMapper.insertUser(user);
//        向T_User_Role表添加
        user = userMapper.findByName(user.getUsername());
        int reult_TURole = roleMapper.insertUserRole(user.getId(),2,"班主任");
        if (result<1){
            throw new TechnicalTeacherException(-3,"添加失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateTutor(TutorInfoDto tutorInfoDto) {

        if (tutorInfoDto==null){
            throw new TechnicalTeacherException(-1,"对象不存在");
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
//        查询要删除的老师
        Tutor Teacher = tutorMapper.selectTutor(tutor.getId());
//        查询要删除的角色
        User u = userMapper.findByName(Teacher.getEmpNo());
//        删除该老师在role中的内容
        int result_deleteRole = roleMapper.deleteUserRole(u.getId());
//        删除该老师在User中的内容
        int result_deleteUser = userMapper.deleteUser(Teacher.getEmpNo());
        int result = tutorMapper.deleteTutor(tutor.getId());
        if (result<1&&result_deleteUser<1&&result_deleteRole<1){
            throw new TechnicalTeacherException(-5,"删除失败");
        }
    }

    @Override

    public List<Tutor> selectTutors(String empNo) {

//        return tutorMapper.selectTutorList();
        return tutorMapper.selectTutorListByKey(empNo);
    }

    @Override
    public Tutor selectTutorById(TutorInfoDto tutorInfoDto) {
        if (tutorInfoDto==null){
            throw new TutorException(-1,"对象不存在");
        }

        return tutorMapper.selectTutor(tutorInfoDto.getId());
    }
}
