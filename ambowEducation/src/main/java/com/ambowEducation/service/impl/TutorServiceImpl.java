package com.ambowEducation.service.impl;
import com.ambowEducation.Exception.TutorException;
import com.ambowEducation.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

import com.ambowEducation.Exception.TutorServiceException;
import com.ambowEducation.dao.*;
import com.ambowEducation.dto.*;
import com.ambowEducation.po.*;
import com.ambowEducation.service.TutorService;
import javafx.geometry.Pos;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    ReduceHoursMapper reduceHoursMapper;


    @Override
    public Tutor queryTutorByEmpNo(String EmpNo) {
        return tutorMapper.queryTutorByEmpNo(EmpNo);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addStudents(List<StudentBaseInfoDto> list) throws Exception {
        int i = studentMapper.insertStudents(list);
        for(StudentBaseInfoDto s:list){
            User user=new User();
            user.setUsername(s.getSNo());
            user.setPassword(s.getIdNumber().substring(12,18));
            user.setCreatetime(new Date());
            userMapper.insertUser(user);
            int userId=userMapper.findByName(s.getSNo()).getId();
            roleMapper.insertUserRole(userId,4,"学生");
        }
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
        for(Student s:list){
            s.setClazz(classMapper.selectClazz(s.getCId()));
            s.setDormitoryNo(dormitoryMapper.selectBySNo(s.getSNo()));
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
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addClazz(List<StudentClassDto> list) throws Exception {
        int i = studentMapper.updateAllStudentClassBySno(list);
        for(StudentClassDto dto:list){
            System.out.println(dto);
        }
        if(i!=1){
            throw new TutorException(-1,"未知错误");
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
                Student student = studentMapper.findStudentBySnoOnlyStudent(history.getStudent().getSNo());
                HoursHistoryDto h=new HoursHistoryDto();
                h.setSName(student.getName());
                h.setSchool(student.getSchool());
                h.setEditDate(history.getEditTime());
                h.setCName(classMapper.selectClazz(student.getCId()).getName());
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

    @Override
    public String downloadSignupInfo(int pId, HttpServletResponse response, HttpServletRequest request) {
        Position position = positionMapper.queryPositionById(pId);
        response.setContentType("application/vnd.ms-excel");
        String filname = position.getCompanyName() + "报名表.xlsx";
        try {
            filname = URLEncoder.encode(filname,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + filname);
        List<SignupPosition> signupPositionList = signupPositionMapper.queryStudentsBypId(pId);
        List<StudentSignupInfo> studentSignupInfoList = new ArrayList<>();
        //将信息存入到studentSignupInfoList
        for (SignupPosition signupPosition:signupPositionList){
            StudentSignupInfo studentSignupInfo = new StudentSignupInfo();
            studentSignupInfo.setCompanyName(position.getCompanyName());
            Student student = studentMapper.queryStudentById(signupPosition.getSId());
            studentSignupInfo.setLocation(student.getNativePlace());
            studentSignupInfo.setName(student.getName());
            studentSignupInfo.setPhone(student.getPhone());
            studentSignupInfo.setPosition(position.getPosition());
            studentSignupInfo.setSchool(student.getSchool());
            if(student.getSex() == 0){
                studentSignupInfo.setSex("男");
            }else {
                studentSignupInfo.setSex("女");
            }
            studentSignupInfoList.add(studentSignupInfo);
        }
        // 创建excel工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建一个工作表sheet
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = null;
        String[] title = {"姓名", "联系方式", "性别", "籍贯", "学校", "职位", "公司名"};
        // 插入第一行数据
        for (int i = 0; i < title.length; i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }
        //追加行
        for (int i = 0; i < studentSignupInfoList.size(); i++ ){
            StudentSignupInfo studentSignupInfo = studentSignupInfoList.get(i);
            XSSFRow nextRow = sheet.createRow(i + 1);
            cell = nextRow.createCell(0);
            cell.setCellValue(studentSignupInfo.getName());
            cell = nextRow.createCell(1);
            cell.setCellValue(studentSignupInfo.getPhone());
            cell = nextRow.createCell(2);
            cell.setCellValue(studentSignupInfo.getSex());
            cell = nextRow.createCell(3);
            cell.setCellValue(studentSignupInfo.getLocation());
            cell = nextRow.createCell(4);
            cell.setCellValue(studentSignupInfo.getSchool());
            cell = nextRow.createCell(5);
            cell.setCellValue(studentSignupInfo.getPosition());
            cell = nextRow.createCell(6);
            cell.setCellValue(studentSignupInfo.getCompanyName());
        }

        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "导出失败";
        }

        return "导出成功";
    }

    @Override
    public List<ReduceHours> getReduceHours() {
        return reduceHoursMapper.selectList();
    }


}






















