package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.TutorServiceException;
import com.ambowEducation.dao.*;
import com.ambowEducation.dto.*;
import com.ambowEducation.po.*;
import com.ambowEducation.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.List;
//@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
@Service
public class TutorServiceImpl implements TutorService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private DormitoryMapper dormitoryMapper;

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private PositionMapper positionMapper;

    @Autowired
    private SignupPositionMapper signupPositionMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private ClassMapper classMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addStudents(List<StudentBaseInfoDto> list) {
        int i = studentMapper.insertStudents(list);
        if(i!=list.size()){
            throw new TutorServiceException(0,"批量添加失败");
        }
    }

    @Override
    public void deleteStudent(String sNo) {
        int i = studentMapper.changeStatus(sNo);
        if (i != 1) {
            throw new TutorServiceException(1,"删除学生失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateStudent(UpdateStudentInfoDto student) {
        StudentDormitoryDto dto=new StudentDormitoryDto();
        dto.setDNumber(student.getDNumber());
        dto.setSchool(student.getSchool());
        dto.setSName(student.getName());
        dto.setSNo(student.getSNo());

        int i1 = studentMapper.updateStudentAllInfo(student);
        int i2 = dormitoryMapper.updateDormitoryBySNo(dto);
        int i3 = studentMapper.updateStudentClassBySno(student.getSNo(),classMapper.
                selectByClassname(student.getCName()).getId());
        if(i1 != 1 || i2 != 1 || i3!=1){
            throw new TutorServiceException(2,"修改学生信息失败");
        }
    }

    @Override
    public Student queryStudentBySId(Integer sId) {
        Student student = studentMapper.queryStudentById(sId);
        if(student == null){
            throw new TutorServiceException(3,"查询学生信息失败");
        }
        return student;
    }

    @Override
    public List<Student> queryAllStudent(Integer tuId) {
        List<Student> list = studentMapper.findStudentsByTuId(tuId);
        if(list == null){
            throw new TutorServiceException(4,"加载学生失败");
        }
        return list;
    }

    @Override
    public void addDormitory(List<StudentDormitoryDto> list) {
        int i = dormitoryMapper.insertStudentDormitory(list);
        if(i != list.size()){
            throw new TutorServiceException(5,"导入宿舍信息失败");
        }
    }
    @Override
    public List<Work> queryWorks(Integer sId) {
        List<Work> list = workMapper.selectListBySId(sId);
        if(list == null){
            throw new TutorServiceException(6,"加载就业追踪信息失败");
        }
        return list;
    }

    @Override
    public void addWork(Work work) {
        int i = workMapper.insertWork(work);
        if(i != 1){
            throw new TutorServiceException(7,"添加就业追踪信息失败");
        }
    }

    @Override
    public List<Position> queryAllPositions(String tuEmpNo) {
        List<Position> list = positionMapper.queryPositionsByTuEmpNo(tuEmpNo);
        if(list == null){
            throw new TutorServiceException(8,"加载职位信息失败");
        }
        return list;
    }

    @Override
    public Position queryPositionById(Integer id) {
        Position position = positionMapper.queryPositionById(id);
        if(position == null){
            throw new TutorServiceException(9,"查看职位信息失败");
        }
        return position;
    }

    @Override
    public void updatePosition(Position p) {
        int i = positionMapper.updatePosition(p);
        if(i != 1){
            throw new TutorServiceException(10,"更新职位失败");
        }
    }

    @Override
    public void overPosition(Integer pId) {
        int i = positionMapper.updatePositionStatus(pId);
        if(i != 1){
            throw new TutorServiceException(11,"更新状态失败");
        }
    }

    @Override
    public List<Student> queryStudentSignup(Integer pId) {
        List<SignupPosition> list = signupPositionMapper.queryStudentsBypId(pId);
        if(list == null){
            throw new TutorServiceException(12,"加载报名信息失败");
        }else {
            List<Student> students = new ArrayList<>();
            for(SignupPosition p:list){
                Student student = studentMapper.queryStudentById(p.getSId());
                students.add(student);
            }
            return students;
        }
    }

    @Override
    public List<HoursHistoryDto> queryHourHistory(Integer tuId,String key) {
        List<History> histories = historyMapper.findAll(tuId, key);
        List<HoursHistoryDto> list = new ArrayList<>();
        if(histories == null){
            throw new TutorServiceException(13,"加载学时记录失败");
        }else {
            for(History history:histories){
                Student student = studentMapper.findStudentBySno(history.getStudent().getSNo());
                HoursHistoryDto h=new HoursHistoryDto();
                h.setSName(student.getName());
                h.setSchool(student.getSchool());
                h.setEditDate(history.getEditTime());
                h.setCName(history.getClazz().getName());
                if(Integer.parseInt(history.getDetail())<0){
                    h.setDetail("扣除");
                    h.setHours(Math.abs(Integer.parseInt(history.getDetail())));
                }else {
                    h.setDetail("奖励");
                    h.setHours(Integer.parseInt(history.getDetail()));
                }
                h.setReason(history.getReason());
                list.add(h);
            }
        }
        return list;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateClassHours(History history) {
        int hours = Integer.parseInt(history.getDetail());
        int i1=0;
        if(hours>0){
            StudentHoursDto studentHoursDto = new StudentHoursDto(history.getSId(),hours);
            i1=studentMapper.updateStudentHoursAdd(studentHoursDto);
        }else {
            StudentHoursDto studentHoursDto = new StudentHoursDto(history.getSId(),Math.abs(hours));
            i1=studentMapper.updateStudentHoursReduce(studentHoursDto);
        }
        int i2 = historyMapper.insHistory(history);
        if(i1 != 1 || i2 != 1){
            throw new TutorServiceException(14,"学时操作失败");
        }
    }
}
