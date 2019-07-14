package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.AdminClassCourseException;
import com.ambowEducation.dao.*;
import com.ambowEducation.dto.ClassCourseDto;
import com.ambowEducation.dto.CourseDto;
import com.ambowEducation.po.Classroom;
import com.ambowEducation.po.Clazz;
import com.ambowEducation.po.Course;
import com.ambowEducation.service.AdminClassCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
        int result_cla = classMapper.updateClazz(clazz);
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
        int result1 = classMapper.deleteClazz(classCourseDto.getId());
        int result2 = classCourseMapper.deleteClassCourse(classCourseDto.getId());
        if(result1<1||result2<1){
            throw new AdminClassCourseException(-5,"删除失败");
        }
    }

    @Override
    public List<Clazz> selectClazz() {
        return classMapper.selectClazzList();
    }

    @Override
    public Clazz selectClazzById(ClassCourseDto classCourseDto) {
        if (classCourseDto==null){
            throw new AdminClassCourseException(-1,"对象不存在");
        }
        return classMapper.selectClazz(classCourseDto.getId());
    }

    //

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertCourse(CourseDto courseDto) {
        if (courseDto==null){
            throw new AdminClassCourseException(-1,"对象不存在");
        }
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
    public List<Course> selectCourse() {
        return courseMapper.findAllCourse();
    }

    @Override
    public Course selectCourseById(CourseDto courseDto) {
        if (courseDto==null){
            throw new AdminClassCourseException(-1,"对象不存在");
        }
        return courseMapper.findByCourseId(courseDto.getId());
    }
}
