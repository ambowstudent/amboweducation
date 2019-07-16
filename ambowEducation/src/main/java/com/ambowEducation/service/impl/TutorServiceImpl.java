package com.ambowEducation.service.impl;
import com.google.common.collect.Lists;

import com.ambowEducation.Exception.TutorServiceException;
import com.ambowEducation.dao.*;
import com.ambowEducation.dto.*;
import com.ambowEducation.po.*;
import com.ambowEducation.service.TutorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private TutorMapper tutorMapper;


    @Override
    public Tutor queryTutorByEmpNo(String EmpNo) {
        return tutorMapper.queryTutorByEmpNo(EmpNo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addStudents(List<StudentBaseInfoDto> list) throws Exception {
        int i = studentMapper.insertStudents(list);
        if(i!=list.size()){
            throw new TutorServiceException(-1,"批量添加失败");
        }
    }

    @Override
    public void deleteStudent(String sNo) {
        int i = studentMapper.changeStatus(sNo);
        if (i != 1) {
            throw new TutorServiceException(-1,"删除学生失败");
        }
    }

    @Override
    public List<Student> queryStudentBysNo(String key, Integer tuId) {
        List<Student> list = studentMapper.queryStudentByKeyAndTuId(key,tuId);
        if(list == null){
            throw new TutorServiceException(-1,"加载学生信息失败");
        }
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateStudent(UpdateStudentInfoDto student) throws Exception {
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
            throw new TutorServiceException(-1,"修改学生信息失败");
        }
    }

    @Override
    public UpdateStudentInfoDto queryStudentBySId(Integer sId)  throws Exception{
        Student student = studentMapper.queryStudentById(sId);
        UpdateStudentInfoDto dto = new UpdateStudentInfoDto();
        BeanUtils.copyProperties(student,dto);
        dto.setDNumber(dormitoryMapper.selectBySNo(student.getSNo()));
        dto.setCName(classMapper.selectClazz(student.getCId()).getName());
        dto.setAllClazzes(classMapper.selectClazzList());


        if(student == null){
            throw new TutorServiceException(-1,"加载学生信息失败");
        }
        return dto;
    }

    @Override
    public Student queryStudentBysNo(String sNo) throws Exception{
        Student student = studentMapper.findStudentBySno(sNo);
        if(student == null){
            throw new TutorServiceException(-1,"加载学生信息失败");
        }
        return student;
    }

    @Override
    public List<Student> queryAllStudent(Integer tuId) throws Exception {
        List<Student> list = studentMapper.findStudentsByTuId(tuId);
        if(list == null){
            throw new TutorServiceException(-1,"加载学生信息失败！");
        }
        return list;
    }

    @Override
    public void addDormitory(List<StudentDormitoryDto> list)  throws Exception {
        int i = dormitoryMapper.insertStudentDormitory(list);
        if(i != list.size()){
            throw new TutorServiceException(-1,"导入宿舍信息失败");
        }
    }
    @Override
    public List<Work> queryWorks(Integer sId)  throws Exception {
        List<Work> list = workMapper.selectListBySId(sId);
        if(list == null){
            throw new TutorServiceException(-1,"加载就业追踪信息失败");
        }
        return list;
    }

    @Override
    public void addWork(Work work)  throws Exception {
        int i = workMapper.insertWork(work);
        if(i != 1){
            throw new TutorServiceException(-1,"添加就业追踪信息失败");
        }
    }

    @Override
    public List<Position> queryAllPositions(String tuEmpNo)  throws Exception {
        List<Position> list = positionMapper.queryPositionsByTuEmpNo(tuEmpNo);
        if(list == null){
            throw new TutorServiceException(-1,"加载职位信息失败");
        }
        return list;
    }

    @Override
    public void addPosition(Position p) throws Exception {
        int i = positionMapper.insPosition(p);
        if(i != 1){
            throw new TutorServiceException(-1,"添加职位信息失败");
        }
    }

    @Override
    public Position queryPositionById(Integer id)  throws Exception {
        Position position = positionMapper.queryPositionById(id);
        if(position == null){
            throw new TutorServiceException(-1,"查看职位信息失败");
        }
        return position;
    }

    @Override
    public void updatePosition(Position p)  throws Exception {
        int i = positionMapper.updatePosition(p);
        if(i != 1){
            throw new TutorServiceException(-1,"更新职位失败");
        }
    }

    @Override
    public void overPosition(Integer pId)  throws Exception {
        int i = positionMapper.updatePositionStatus(pId);
        if(i != 1){
            throw new TutorServiceException(-1,"更新状态失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deletePosition(Integer id) throws Exception {
        int i1=positionMapper.deletePosition(id);
        int i2=signupPositionMapper.deleteSignupInfo(id);
        if(i1 != 1 || i2 == 0){
            throw new TutorServiceException(-1,"删除职位失败");
        }
    }

    @Override
    public List<Student> queryStudentSignup(Integer pId)  throws Exception {
        List<SignupPosition> list = signupPositionMapper.queryStudentsBypId(pId);
        if(list == null){
            throw new TutorServiceException(-1,"加载报名信息失败");
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
    public List<Position> queryPositionsByKey(String key) {
        List<Position> list = positionMapper.queryPositionsByKey(key);
        if(list == null){
            throw new TutorServiceException(-1,"加载职位信息失败");
        }
        return list;
    }

    @Override
    public List<Position> queryPositionsByKeyAndTuEmpNo(String key, String tuEmpNo) {
        List<Position> list = positionMapper.queryPositionsByKeyAndTuEmpNo(key,tuEmpNo);
        if(list == null){
            throw new TutorServiceException(-1,"加载学生信息失败");
        }
        return list;
    }

    @Override
    public List<StudentsHoursInfoDto> queryStudentsHoursInfo(Integer tuId) throws Exception {

        List<Student> students = studentMapper.findStudentsByTuId(tuId);
        if(students == null){
            throw new TutorServiceException(-1,"加载学生信息失败");
        }
        List<StudentsHoursInfoDto> list = new ArrayList<>();
        for(Student s:students){
            StudentsHoursInfoDto dto = new StudentsHoursInfoDto();
            BeanUtils.copyProperties(s,dto);
            dto.setCName(classMapper.selectClazz(s.getCId()).getName());
            list.add(dto);
        }
        return list;
    }


    /*@Override
    public Map<String,Object> queryStudentsHoursInfo(Integer tuId) throws Exception {
        Map<String,Object> map=new HashMap<>();
        List<Student> students = studentMapper.findStudentsByTuId(tuId);
        if(students == null){
            throw new TutorServiceException(-1,"加载学生信息失败");
        }
        List<StudentsHoursInfoDto> list = new ArrayList<>();
        for(Student s:students){
            StudentsHoursInfoDto dto = new StudentsHoursInfoDto();
            BeanUtils.copyProperties(s,dto);
            dto.setCName(classMapper.selectClazz(s.getCId()).getName());
            list.add(dto);
        }
        map.put("students",list);
        map.put("clazzes",classMapper.selectClazzList());
        return map;
    }*/

    @Override
    public List<HoursHistoryDto> queryHourHistory(Integer tuId,String key) throws Exception {
        List<History> histories = historyMapper.findAll(tuId, key);
        List<HoursHistoryDto> list = new ArrayList<>();
        if(histories == null){
            throw new TutorServiceException(-1,"加载学时记录失败");
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
                }else{
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
    public void updateClassHours(History history)  throws Exception {
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
            throw new TutorServiceException(-1,"学时操作失败");
        }
    }

    @Override
    public List<StudentsHoursInfoDto> queryStudentsHoursInfoByKey(Integer id, String key) {
        List<Student> students = studentMapper.queryStudentByKeyAndTuId(key,id);
        if(students == null){
            throw new TutorServiceException(-1,"加载学生信息失败");
        }
        List<StudentsHoursInfoDto> list = new ArrayList<>();
        for(Student s:students){
            StudentsHoursInfoDto dto = new StudentsHoursInfoDto();
            BeanUtils.copyProperties(s,dto);
            dto.setCName(classMapper.selectClazz(s.getCId()).getName());
            list.add(dto);
        }
        return list;
    }
}






















