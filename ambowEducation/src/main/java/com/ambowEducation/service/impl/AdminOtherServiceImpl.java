package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.AdminOtherException;
import com.ambowEducation.dao.ClassroomMapper;
import com.ambowEducation.dao.ReduceHoursMapper;
import com.ambowEducation.dto.ClassroomDto;
import com.ambowEducation.dto.ReduceHoursDto;
import com.ambowEducation.po.Classroom;
import com.ambowEducation.po.ReduceHours;
import com.ambowEducation.service.AdminOtherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminOtherServiceImpl implements AdminOtherService {

    @Autowired
    private ReduceHoursMapper reduceHoursMapper;

    @Autowired
    private ClassroomMapper classroomMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertReduceHours(ReduceHoursDto reduceHoursDto) {
        if(reduceHoursDto==null){
            throw new AdminOtherException(-1,"对象不存在");
        }
        ReduceHours rh = reduceHoursMapper.selectByName(reduceHoursDto.getName());
        if (rh!=null){
            throw new AdminOtherException(-2,"此扣分项已存在");
        }

        ReduceHours reduceHours = new ReduceHours();
        BeanUtils.copyProperties(reduceHoursDto,reduceHours);

        int result = reduceHoursMapper.insert(reduceHours);
        if (result<1){
            throw new AdminOtherException(-3,"添加失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateReduceHours(ReduceHoursDto reduceHoursDto) {
        if(reduceHoursDto==null){
            throw new AdminOtherException(-1,"对象不存在");
        }
        ReduceHours rh = reduceHoursMapper.selectByName(reduceHoursDto.getName());
        if(rh!=null&&!rh.getId().equals(reduceHoursDto.getId())){
            throw new AdminOtherException(-1,"此扣分项已存在");
        }
        ReduceHours reduceHours = new ReduceHours();
        BeanUtils.copyProperties(reduceHoursDto,reduceHours);

        int result = reduceHoursMapper.update(reduceHours);
        if (result<1){
            throw new AdminOtherException(-4,"修改失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteReduceHours(ReduceHoursDto reduceHoursDto) {
        if(reduceHoursDto==null){
            throw new AdminOtherException(-1,"对象不存在");
        }
        ReduceHours reduceHours = new ReduceHours();
        BeanUtils.copyProperties(reduceHoursDto,reduceHours);

        int result = reduceHoursMapper.delete(reduceHours.getId());
        if (result<1){
            throw new AdminOtherException(-5,"删除失败");
        }
    }

    @Override
    public List<ReduceHours> selectReduceHours() {

        return reduceHoursMapper.selectList();
    }

    @Override
    public ReduceHours selectReduceHoursById(ReduceHoursDto reduceHoursDto) {
        if(reduceHoursDto==null){
            throw new AdminOtherException(-1,"对象不存在");
        }
        return reduceHoursMapper.select(reduceHoursDto.getId());
    }

    //==========================

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertClassroom(ClassroomDto classroomDto) {
        if (classroomDto==null){
            throw new AdminOtherException(-1,"对象不存在");
        }
        Classroom cr = classroomMapper.selectByRoomNumber(classroomDto.getRoomNumber());
        if(cr!=null){
            throw new AdminOtherException(-2,"该房间号已存在");
        }
        Classroom classroom = new Classroom();
        BeanUtils.copyProperties(classroomDto,classroom);
        int result = classroomMapper.insert(classroom);
        if(result<1){
            throw new AdminOtherException(-3,"添加失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateClassroom(ClassroomDto classroomDto) {
        if (classroomDto==null){
            throw new AdminOtherException(-1,"对象不存在");
        }
        Classroom cr = classroomMapper.selectByRoomNumber(classroomDto.getRoomNumber());
        if(cr!=null&&!cr.getId().equals(classroomDto.getId())){
            throw new AdminOtherException(-2,"该房间号已存在");
        }
        Classroom classroom = new Classroom();
        BeanUtils.copyProperties(classroomDto,classroom);
        int result = classroomMapper.update(classroom);
        if(result<1){
            throw new AdminOtherException(-4,"修改失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteClassroom(ClassroomDto classroomDto) {
        if (classroomDto==null){
            throw new AdminOtherException(-1,"对象不存在");
        }
        Classroom classroom = new Classroom();
        BeanUtils.copyProperties(classroomDto,classroom);
        int result = classroomMapper.delete(classroom.getId());
        if(result<1){
            throw new AdminOtherException(-5,"删除失败");
        }
    }

    @Override
    public List<Classroom> selectClassroom() {

        return classroomMapper.selectList();
    }

    @Override
    public Classroom selectClassroomById(ClassroomDto classroomDto) {
        if (classroomDto==null){
            throw new AdminOtherException(-1,"对象不存在");
        }
        return classroomMapper.selectById(classroomDto.getId());
    }
}
