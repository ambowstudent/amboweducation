package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.StudentGradeException;
import com.ambowEducation.dao.StudentCourseGradeMapper;
import com.ambowEducation.dao.StudentMapper;
import com.ambowEducation.dao.TechnicalTeacherMapper;
import com.ambowEducation.dao.WorkMapper;
import com.ambowEducation.dto.StudentGradeDto;
import com.ambowEducation.po.Student;
import com.ambowEducation.po.StudentCourseGrade;
import com.ambowEducation.service.StudentCourseGradeService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentCourseGradeServiceImpl implements StudentCourseGradeService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentCourseGradeMapper studentCourseGradeMapper;

    @Autowired
    private WorkMapper workMapper;

    @Autowired
    private TechnicalTeacherMapper technicalTeacherMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insertStudentCourse(StudentGradeDto studentGradeDto) throws Exception {
        if(studentGradeDto==null||studentGradeDto.getStuNo()==null){
            //该对象不存在直接抛异常
            throw new StudentGradeException(-1,"对象不存在");
        }
        //根据学生学号查询学生的id
        Student student = studentMapper.findStudentBySnoOnlyStudent(studentGradeDto.getStuNo());
        if(student==null){
            throw new StudentGradeException(-2,"学生不存在");
        }
        //调用mapper封装
        StudentCourseGrade studentCourseGrade=new StudentCourseGrade();
        try {
            PropertyUtils.copyProperties(studentCourseGrade,studentGradeDto);
            studentCourseGrade.setSId(student.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i = studentCourseGradeMapper.insertStudentGrade(studentCourseGrade);
        if(i<1){
            throw new StudentGradeException(-3,"学生成绩添加失败");
        }
    }

    @Override
    public Student findStudentByStudentNo(String stuNo) {

        Student student = studentMapper.findStudentBySnoOnlyStudent(stuNo);
        if(student==null){
            throw new StudentGradeException(-2, "学生不存在");
        }
        return student;
    }

    @Override
    public void modifyStudentCourseByStuId(StudentGradeDto studentGradeDto) throws Exception{
        if(studentGradeDto==null&&studentGradeDto.getSId()==null
        ){
            throw new StudentGradeException(-1, "对象不存在");
        }
        StudentCourseGrade courseGrade=new StudentCourseGrade();
        try {
            PropertyUtils.copyProperties(courseGrade,studentGradeDto );
        } catch (Exception e) {
            e.printStackTrace();
        }

        int i = studentCourseGradeMapper.updateStudentGrade(courseGrade);
        if(i<1){
            throw new StudentGradeException(-4, "成绩更新失败");
        }
        System.out.println("成绩更新成功");
    }

    @Override
    public List<StudentCourseGrade> findAllByManyCondition(String sNo, String studentName, String school, String courseName, Integer clazzId) throws Exception{
        List<StudentCourseGrade> studentCourseGrades = studentCourseGradeMapper.findAllByManyCondition(sNo, studentName, school, courseName,clazzId);
        return studentCourseGrades;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Map<String,Integer> findStudentWorkRateOfEmployment(int teachId) throws Exception{

        //查询出所有的类型的人数
       int workcount= workMapper.selectEveryTypeCountByTechId(teachId);

        Map<String,Integer> map = new HashMap<>();
        //求出总条数
        int count = technicalTeacherMapper.findTechnicalTeacherInStudentCount(teachId);

        if(count==0){
            throw new StudentGradeException(-5, "该老师没有管理任何学生");
        }
        map.put("已就业", workcount);
        map.put("未就业", count-workcount);
        return map;
    }

    @Override
    public List<StudentCourseGrade> findMyGrade(int sId) {
        return studentCourseGradeMapper.findOneStudentAllGrade(sId);
    }

    @Override
    public StudentCourseGrade findMyGradeByKey(int sid, int cid) {
        return studentCourseGradeMapper.findOneStudentOneGrade(sid, cid);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public List<Map<String, Object>> findAllStudentWorkPercent() throws Exception {
        //查询所有学生的个数
        int count = studentMapper.findAllStudentCount();
        if(count==0){
            throw new StudentGradeException(-5,"没有任何学生");
        }
        //查询所有就业学生个数
        List<Map<String, Object>> maps = workMapper.selectEveryTypeCount();
        int num=0;
        for (Map map:
                maps) {
           num+= new Integer(map.get("num").toString()) ;

        }
        Map<String,Object> newMap=new HashMap<>();
        newMap.put("type", "未就业");
        newMap.put("num", count-num);
        maps.add(newMap);
        return maps;
    }

    @Override
    public Map<String,List<Map<String,Object>>> selectThreeYearSal() throws Exception {

        List<Map<String, Object>> maps = workMapper.selectThreeYearSal();
        Map<String,List<Map<String,Object>>> newmap=new HashMap<>();
        for(Map<String, Object> map:maps){
            String year = map.get("year").toString();
            List<Map<String,Object>> list = null;
            if(!newmap.containsKey(year)){
                list=new ArrayList<>();
                Map<String,Object> objectMap=new HashMap<>();
                objectMap.put("type", map.get("type"));
                objectMap.put("avg_sal", map.get("avg_sal"));
                list.add(objectMap);
                newmap.put(year, list);
            }else{
                list=newmap.get(year);
                Map<String,Object> objectMap=new HashMap<>();
                objectMap.put("type", map.get("type"));
                objectMap.put("avg_sal", map.get("avg_sal"));
                list.add(objectMap);
            }


        }
        return newmap;
    }


}
