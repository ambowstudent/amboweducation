package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.AdminClassCourseException;
import com.ambowEducation.dao.*;
import com.ambowEducation.dto.*;
import com.ambowEducation.po.*;
import com.ambowEducation.service.AdminClassCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AdminClassCourseServiceImpl implements AdminClassCourseService {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ClassCourseMapper classCourseMapper;

    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    private TechnicalTeacherMapper teacherMapper;

    @Autowired
    private TutorMapper tutorMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertClazz(ClassCourseDto classCourseDto) {
        if(classCourseDto==null){
            throw new AdminClassCourseException(-1,"对象不存在");
        }
        Clazz c1 = classMapper.selectByClassname(classCourseDto.getName());
        Classroom cr = classroomMapper.selectByRoomNumber(classCourseDto.getRoomName());
        if(c1!=null){
            throw new AdminClassCourseException(-2,"此班名已被使用");
        }
        if(cr==null){
            throw new AdminClassCourseException(-6,"该教室不存在");
        }
        Clazz c3 = classMapper.selectByRoomId(cr.getId());
        if(c3!=null){
            throw new AdminClassCourseException(-6,"该教室已被使用");
        }
        Clazz clazz = new Clazz();
        List cour_list = Arrays.asList(classCourseDto.getCrId());
        classCourseDto.setRoomId(cr.getId());
        BeanUtils.copyProperties(classCourseDto,clazz);
        int result_cla = classMapper.insertClazz(clazz);
        Clazz c2 = classMapper.selectByClassname(clazz.getName());
        int result_clacou = classCourseMapper.insertClassCourse(c2.getId(),cour_list);
        if(result_cla<1||result_clacou<1){
            throw new AdminClassCourseException(-3,"添加失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateClazz(ClassCourseDto classCourseDto) {
        if(classCourseDto==null){
            throw new AdminClassCourseException(-1,"对象不存在");
        }
//        查看 新班名 的班级信息，看看是否存在
        Clazz c1 = classMapper.selectByClassname(classCourseDto.getName());
//        查看 新教室 的教室信息，看看是否存在
        Classroom cr = classroomMapper.selectByRoomNumber(classCourseDto.getRoomName());
//        如果 新班级存在且不是 原来的班级 则不可改
        if(c1!=null&&!c1.getId().equals(classCourseDto.getId())){
            throw new AdminClassCourseException(-2,"此班名已被使用");
        }
        if(cr==null){
            throw new AdminClassCourseException(-6,"该教室不存在");
        }
//        根据 教室 查班级 获取班级信息
        Clazz c3 = classMapper.selectByRoomId(cr.getId());
        //        如果 新班级存在且不是 原来的班级 则不可改
        if(c3!=null&&!c3.getId().equals(classCourseDto.getId())){
            throw new AdminClassCourseException(-6,"该教室已被使用");
        }
        Clazz clazz = new Clazz();
//        获取教室Id并赋值
        classCourseDto.setRoomId(cr.getId());
        BeanUtils.copyProperties(classCourseDto,clazz);
//        修改班级信息
        int result_cla = classMapper.updateClazz(clazz);
//        修改班级课程信息
        List<String> cour_list_new = null;
        if (classCourseDto.getCrId()!=null)
            cour_list_new = Arrays.asList(classCourseDto.getCrId());
        List cour_list_old = classCourseMapper.queryCourseIdByClassId(classCourseDto.getId());
        //list进行排序
        List cour_new = new ArrayList();
        for (String str:cour_list_new) {
            cour_new.add(new Integer(str));
        }
        //将两个list中的元素升序排列
        Collections.sort(cour_list_old);
        Collections.sort(cour_new);
        //比较课表是否发生变化 true 是 ， false 否
        boolean changeif = !cour_new.equals(cour_list_old);
//        throw new AdminClassCourseException(-10,"修改失败");
//        如果发生了变化则对班级课程表进行操作
        if (changeif) {
            int result_clacouDelete = classCourseMapper.deleteClassCourse(classCourseDto.getId());
            int result_clacouInsert = classCourseMapper.insertClassCourse(classCourseDto.getId(), cour_new);
//        判断对班级课程的修改情况
            if (result_clacouDelete<1&&result_clacouInsert<1){
                throw new AdminClassCourseException(-4,"修改失败");
            }
        }
//        判断对班级信息的修改情况
        if(result_cla<1){
            throw new AdminClassCourseException(-4,"修改失败");
        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteClazz(ClassCourseDto classCourseDto) {
        if(classCourseDto==null){
            throw new AdminClassCourseException(-1,"对象不存在");
        }
        Clazz clazz = new Clazz();
        BeanUtils.copyProperties(classCourseDto,clazz);
//        获取这个班里是否还有学生，如果有，不能删除班级，抛出异常。
        List<Integer> stuNumInClass = classMapper.queryStudentByClassId(classCourseDto.getId());
        System.out.println(stuNumInClass);
        if (stuNumInClass!=null&&stuNumInClass.size()>0){
            throw new AdminClassCourseException(-8,"该班级内还有学生没有被安置，不能删除此班级");
        }
//        删除班级--班级表
        int result1 = classMapper.deleteClazz(classCourseDto.getId());
//        删除 该班级对应的课程
        int result2 = classCourseMapper.deleteClassCourse(classCourseDto.getId());
        if(result1<1||result2<1){
            throw new AdminClassCourseException(-5,"删除失败");
        }
    }

    @Override
    public List<ClassCourseDto> selectClazz(String name) {

//        return classMapper.selectClazzList();
//        获取初始的班级列表
        List<Clazz> list_init = classMapper.selectClazzListByKey(name);
        List<ClassCourseDto> list_final = new ArrayList<>();
//        将班级列表的内容转化[将ID转换成名字]
        for (Clazz clazz:list_init) {
            ClassCourseDto classCourseDto = new ClassCourseDto();
            TechnicalTeacherInfoDto technicalTeacherInfoDto = new TechnicalTeacherInfoDto();
            TutorInfoDto tutorInfoDto = new TutorInfoDto();
            ClassroomDto classroomDto = new ClassroomDto();

//            根据班级里的各个ID 分别获取各个对象
            technicalTeacherInfoDto.setId(clazz.getTeId());
            tutorInfoDto.setId(clazz.getTuId());
            classroomDto.setId(clazz.getRoomId());
            TechnicalTeacher technicalTeacher = teacherMapper.selectTechnicalTeacher(clazz.getTeId());
            Tutor tutor = tutorMapper.selectTutor(clazz.getTuId());
            Classroom classroom = classroomMapper.selectById(clazz.getRoomId());
//            获取该班级已选课程
            String[] crids = this.selectClazzCourseById(clazz.getId());
//            封装classCourseDto 进行前台展示
            if (clazz!=null) {
                classCourseDto.setId(clazz.getId());
                classCourseDto.setName(clazz.getName());
            }
            if(classroom!=null) {
                classCourseDto.setRoomName(classroom.getRoomNumber());
            }
            if(technicalTeacher!=null) {
                classCourseDto.setTeaName(technicalTeacher.getName());
            }
            if (tutor!=null){
                classCourseDto.setTuName(tutor.getName());
            }
            classCourseDto.setCrId(crids);
            list_final.add(classCourseDto);
        }

        return list_final;
    }

    @Override
    public List<Clazz> selectClazzSmart(String name) {
        return classMapper.selectClazzListByKey(name);
    }

    @Override
    public Clazz selectClazzById(ClassCourseDto classCourseDto) {
        if (classCourseDto==null){
            throw new AdminClassCourseException(-1,"对象不存在");
        }
        return classMapper.selectClazz(classCourseDto.getId());
    }

    @Override
    public String[] selectClazzCourseById(Integer id) {
        List<Integer> list = classCourseMapper.queryCourseIdByClassId(id);
        String[] crIds = {};
        if (list!=null) {
            crIds = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                crIds[i] = courseMapper.select(list.get(i)).getName();
            }
        }
        return crIds;
    }

    //

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertCourse(CourseDto courseDto) {
        if (courseDto==null){
            throw new AdminClassCourseException(-1,"对象不存在");
        }
//        根据课程名查询是否存在该课程
        Course cou = courseMapper.selectByName(courseDto.getName());
        if(cou!=null){
            throw new AdminClassCourseException(-2,"该课程已存在");
        }
        Course course = new Course();
        BeanUtils.copyProperties(courseDto,course);
        int result = courseMapper.insert(course);
        if (result<1){
            throw new AdminClassCourseException(-3,"添加失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateCourse(CourseDto courseDto) {
        if (courseDto==null){
            throw new AdminClassCourseException(-1,"对象不存在");
        }
//        根据 新课程名 查询课程信息 ，查看是否存在
        Course cou = courseMapper.selectByName(courseDto.getName());
//        如果能查课程且此课程不是原来的课程，则不可修改
        if(cou!=null&&!cou.getId().equals(courseDto.getId())){
            throw new AdminClassCourseException(-2,"该课程已存在");
        }
        Course course = new Course();
        BeanUtils.copyProperties(courseDto,course);
        int result = courseMapper.update(course);
        if (result<1){
            throw new AdminClassCourseException(-4,"修改失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteCourse(CourseDto courseDto) {
        if (courseDto==null){
            throw new AdminClassCourseException(-1,"对象不存在");
        }
        Course course = new Course();
        BeanUtils.copyProperties(courseDto,course);
        int result = courseMapper.delete(course.getId());
        if (result<1){
            throw new AdminClassCourseException(-3,"删除失败");
        }
    }

    @Override
    public List<Course> selectCourse(String name) {

//        return courseMapper.findAllCourse();
        return courseMapper.selectListByKey(name);
    }

    @Override
    public Course selectCourseById(CourseDto courseDto) {
        if (courseDto==null){
            throw new AdminClassCourseException(-1,"对象不存在");
        }
        return courseMapper.findByCourseId(courseDto.getId());
    }
}
