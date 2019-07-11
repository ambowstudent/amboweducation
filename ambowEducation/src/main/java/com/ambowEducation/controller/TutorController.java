package com.ambowEducation.controller;

import com.ambowEducation.Exception.TutorException;
import com.ambowEducation.dto.HoursHistoryDto;
import com.ambowEducation.dto.StudentsHoursInfoDto;
import com.ambowEducation.dto.UpdateStudentInfoDto;
import com.ambowEducation.po.History;
import com.ambowEducation.po.Position;
import com.ambowEducation.po.Student;
import com.ambowEducation.po.Work;
import com.ambowEducation.service.TutorService;
import com.ambowEducation.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tutor")
public class TutorController {

    @Autowired
    private TutorService service;


    /**
     * 学生管理
     */

    @GetMapping("/toStuIndex")//学生管理界面
    public JsonData toStuIndex(@RequestParam("tu_id") Integer tuId){
        try {
            List<Student> students = service.queryAllStudent(tuId);
            return JsonData.buildSuccess(students);
        }catch (TutorException e){
            return JsonData.buildError(e);
        }catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    /*@GetMapping("/getStuById")
    public JsonData getStuById(@RequestParam("s_id") Integer sId){
        try {
            Student student = service.queryStudentBySId(sId);
            return JsonData.buildSuccess(student);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }*/

    @GetMapping("/getStuBySNo")//学业导师通过关键字进行查询
    public JsonData getStuBySNo(@RequestParam("key") String key,
                                @RequestParam("tu_id") Integer tuId){
        try {
            List<Student> list = service.queryStudentBysNo(key,tuId);
            return JsonData.buildSuccess(list);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    @GetMapping("/toUpdateStu") //去到更新界面
    public JsonData toUpdateStu(@RequestParam("s_id") Integer id){
        try {
            UpdateStudentInfoDto dto = service.queryStudentBySId(id);
            return JsonData.buildSuccess(dto);
        } catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @PutMapping("/updateStu")//更新学生基本信息
    public JsonData updateStu(@RequestBody UpdateStudentInfoDto stu){
        try {
            service.updateStudent(stu);
            return JsonData.buildSuccess(0);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @GetMapping("/getWorkInfo")//获取就业信息
    public JsonData getWorkInfo(@RequestParam("s_id") Integer id){
        try {
            List<Work> works = service.queryWorks(id);
            return JsonData.buildSuccess(works);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @PostMapping("/addWorkInfo")//添加就业信息
    public JsonData addWorkInfo(@RequestBody Work work){
        try {
            service.addWork(work);
            return JsonData.buildSuccess(0);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    /**
     * 招聘信息管理
     */

    @GetMapping("/toPositionIndex")//招聘信息管理首页
    public JsonData toPositionIndex(@RequestParam("tu_empno") String tuEmpNo){
        try {
            List<Position> positions = service.queryAllPositions(tuEmpNo);
            return JsonData.buildSuccess(positions);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @GetMapping("/queryPositionByKey")
    public JsonData queryPositionByKey(@RequestParam("key") String key,
                                        @RequestParam("tu_empno") String tuEmpNo){
        try {
            List<Position> positions = service.queryPositionsByKeyAndTuEmpNo(key,tuEmpNo);
            return JsonData.buildSuccess(positions);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @PostMapping("/addPosition")//添加职位
    public JsonData addPosition(@RequestBody Position p){
        try {
            p.setCreatetime(new Date());
            p.setStatus(0);
            service.addPosition(p);
            return JsonData.buildSuccess(0);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @GetMapping("/toUpdatePosition")//去到职位修改界面
    public JsonData toUpdatePosition(@RequestParam("p_id") Integer id){
        try {
            Position position = service.queryPositionById(id);
            return JsonData.buildSuccess(position);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @PutMapping("/overPosition")
    public JsonData overPosition(@RequestParam("p_id") Integer id){
        try {
            service.overPosition(id);
            return JsonData.buildSuccess(0);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @PutMapping("/updatePosition")//修改职位
    public JsonData updatePosition(@RequestBody Position p){
        try {
            p.setCreatetime(new Date());
            service.updatePosition(p);
            return JsonData.buildSuccess(0);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @DeleteMapping("/deletePosition")//删除职位
    public JsonData deletePosition(@RequestParam("p_id") Integer id){
        try {
            service.deletePosition(id);
            return JsonData.buildSuccess(0);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    @GetMapping("/getSignupStus")//获取报名的学生
    public JsonData getSignupStus(@RequestParam("p_id") Integer id){
        try {
            List<Student> students = service.queryStudentSignup(id);
            return JsonData.buildSuccess(students);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    /**
     * 学时管理模块
     */
    @GetMapping("/toHoursIndex")//所有学生的学时信息
    public JsonData toHoursIndex(@RequestParam("tu_id") Integer tuId){
        try {
            List<StudentsHoursInfoDto> dtos = service.queryStudentsHoursInfo(tuId);
            return JsonData.buildSuccess(dtos);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    @GetMapping("/queryStuHoursByKey")
    public JsonData queryStuHoursByKey(@RequestParam("tu_id") Integer id,
                                    @RequestParam("key") String key){
        try {
            List<StudentsHoursInfoDto> dtos = service.queryStudentsHoursInfoByKey(id,key);
            return JsonData.buildSuccess(dtos);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    @PostMapping("/editHours")
    public JsonData editHours(@RequestBody History h){
        try {
            h.setEditTime(new Date());
            service.updateClassHours(h);
            return JsonData.buildSuccess(0);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    @GetMapping("/getHistory")
    public JsonData getHistory(@RequestParam("tu_id")Integer id,
                              @RequestParam("key") String key){
        try {
            List<HoursHistoryDto> dtos = service.queryHourHistory(id, key);
            return JsonData.buildSuccess(dtos);
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }










}
