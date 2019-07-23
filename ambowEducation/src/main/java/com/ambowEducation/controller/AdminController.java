package com.ambowEducation.controller;

import com.ambowEducation.Exception.TechnicalTeacherException;
import com.ambowEducation.Exception.TutorException;
import com.ambowEducation.dto.*;
import com.ambowEducation.po.*;
import com.ambowEducation.service.AdminClassCourseService;
import com.ambowEducation.service.AdminOtherService;
import com.ambowEducation.service.AdminTeacherService;
import com.ambowEducation.utils.JsonData;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    private AdminTeacherService adminTeacherService;

    @Autowired
    private AdminClassCourseService adminClassCourseService;

    @Autowired
    private AdminOtherService adminOtherService;

    /**
     * 技术老师管理
     * @param technicalTeacherInfoDto
     * @return
     */

    //添加技术老师
    @PostMapping("/insert_technicalTeacher")
    public JsonData insertTechnicalTeacher(@RequestBody TechnicalTeacherInfoDto technicalTeacherInfoDto){
        try {
            System.out.println(technicalTeacherInfoDto);
            adminTeacherService.insertTechinicalTeacher(technicalTeacherInfoDto);
            return JsonData.buildSuccess("技术教师添加成功");
        }catch (TechnicalTeacherException tte){
            return JsonData.buildError(tte.getMessage());
        }catch (Exception ex){
            return JsonData.buildError(ex.getMessage());
        }
    }

//    修改技术教师
    @PutMapping("/update_technicalTeacher")
    public JsonData updateTechnicalTeacher(@RequestBody TechnicalTeacherInfoDto technicalTeacherInfoDto){
        try {
            adminTeacherService.updateTechnicalTeacher(technicalTeacherInfoDto);
            return JsonData.buildSuccess("技术教师修改成功");
        }catch (TechnicalTeacherException tte){
            return JsonData.buildError(tte.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //    删除技术教师
    @DeleteMapping("/delete_technicalTeacher")
    public JsonData deleteTechnicalTeacher(@RequestParam("id") Integer id){
         try {
             TechnicalTeacherInfoDto technicalTeacherInfoDto = new TechnicalTeacherInfoDto();
             technicalTeacherInfoDto.setId(id);
             adminTeacherService.deleteTechnicalTeacher(technicalTeacherInfoDto);
             return JsonData.buildSuccess("技术教师删除成功");
         }catch (TechnicalTeacherException tte){
             return JsonData.buildError(tte.getMessage());
         }catch (Exception ex) {
             return JsonData.buildError(ex.getMessage());
         }
    }


    //查询技术老师
    @GetMapping("/select_technicalTeacher_list")
    public JsonData selectTechnicalTeacher(@RequestParam(value = "page_no",defaultValue = "1") Integer page,@RequestParam(value = "key",defaultValue = "") String empNo ){
        try {
            PageHelper.startPage(page,9);
            List<TechnicalTeacher> list = adminTeacherService.selectTechnicalTeacher(empNo);
            return JsonData.buildSuccess(new PageInfo<>(list));
        }catch (TechnicalTeacherException tte){
            return JsonData.buildError(tte.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //通过ID查询技术老师
    @GetMapping("/select_technicalTeacher")
    public JsonData selectTechnicalTeacherById(@RequestParam(value = "id",defaultValue = "null") Integer id ){
        try {
            TechnicalTeacherInfoDto technicalTeacherInfoDto = new TechnicalTeacherInfoDto();
            technicalTeacherInfoDto.setId(id);
            return JsonData.buildSuccess(adminTeacherService.selectTechnicalTeacherById(technicalTeacherInfoDto));
        }catch (TechnicalTeacherException tte){
            return JsonData.buildError(tte.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    /**
     * 学业导师部分
     * @param tutorInfoDto
     * @return
     */

    //添加学业导师
    @PostMapping("/insert_tutor")
    public JsonData insertTutor(@RequestBody TutorInfoDto tutorInfoDto){
        try {
            adminTeacherService.insertTutor(tutorInfoDto);
            return JsonData.buildSuccess("学业导师添加成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //修改学业导师
    @PutMapping("/update_tutor")
    public JsonData updateTutor(@RequestBody TutorInfoDto tutorInfoDto){
        try {
            adminTeacherService.updateTutor(tutorInfoDto);
            return JsonData.buildSuccess("学业导师修改成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //删除学业导师
    @DeleteMapping("/delete_tutor")
    public JsonData deleteTutor(@RequestParam("id") Integer id){
        try {
            TutorInfoDto tutorInfoDto = new TutorInfoDto();
            tutorInfoDto.setId(id);
            adminTeacherService.deleteTutor(tutorInfoDto);
            return JsonData.buildSuccess("学业导师删除成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //查询学业导师
    @GetMapping("/select_tutor_list")
    public JsonData selectTutor(@RequestParam(value = "page_no",defaultValue = "1") Integer page,@RequestParam(value = "key",defaultValue = "") String empNo){
        try {
            PageHelper.startPage(page,9);
            List<Tutor> list = adminTeacherService.selectTutors(empNo);
            return JsonData.buildSuccess(new PageInfo<>(list));
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //通过ID查询学业导师
    @GetMapping("/select_tutor")
    public JsonData selectTutorById(@RequestParam(value = "id",defaultValue = "null") Integer id){
        try {
            TutorInfoDto tutorInfoDto = new TutorInfoDto();
            tutorInfoDto.setId(id);
            return JsonData.buildSuccess(adminTeacherService.selectTutorById(tutorInfoDto));
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    /**
     * 班级管理
     * @param classCourseDto
     * @return
     */

    //添加班级信息
    @PostMapping("/insert_class")
    public JsonData insertClazz(@RequestBody ClassCourseDto classCourseDto){
        try {
            adminClassCourseService.insertClazz(classCourseDto);
            return JsonData.buildSuccess("新班级添加成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //修改班级信息
    @PutMapping("/update_class")
    public JsonData updateClazz(@RequestBody ClassCourseDto classCourseDto){
        try {
            adminClassCourseService.updateClazz(classCourseDto);
            return JsonData.buildSuccess("班级信息修改成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //删除班级信息
    @DeleteMapping("/delete_class")
    public JsonData deleteClazz(@RequestParam("id") Integer id){
        try {
            ClassCourseDto classCourseDto = new ClassCourseDto();
            classCourseDto.setId(id);
            adminClassCourseService.deleteClazz(classCourseDto);
            return JsonData.buildSuccess("班级信息删除成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //查询班级信息
    @GetMapping("/select_class_list")
    public JsonData selectClazz(@RequestParam(value = "key",defaultValue = "") String name){
        try {
            List<Clazz> list = adminClassCourseService.selectClazz(name);
            return JsonData.buildSuccess(list);
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //通过ID查询班级信息
    @GetMapping("/select_class")
    public JsonData selectClazzById(@RequestParam(value = "id",defaultValue = "null") Integer id){
        try {
            ClassCourseDto classCourseDto = new ClassCourseDto();
            classCourseDto.setId(id);
            return JsonData.buildSuccess(adminClassCourseService.selectClazzById(classCourseDto));
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    /**
     * 课程信息管理
     * @param courseDto
     * @return
     */

    //添加课程信息
    @PostMapping("/insert_course")
    public JsonData insertCourse(@RequestBody CourseDto courseDto){
        try {
            adminClassCourseService.insertCourse(courseDto);
            return JsonData.buildSuccess("新课程添加成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //修改课程信息
    @PutMapping("/update_course")
    public JsonData updateCourse(@RequestBody CourseDto courseDto){
        try {
            adminClassCourseService.updateCourse(courseDto);
            return JsonData.buildSuccess("课程信息修改成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //删除课程信息
    @DeleteMapping("/delete_course")
    public JsonData deleteCourse(@RequestParam("id") Integer id){
        try {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(id);
            adminClassCourseService.deleteCourse(courseDto);
            return JsonData.buildSuccess("课程信息删除成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //查询课程信息
    @GetMapping("/select_course_list")
    public JsonData selectCourse(@RequestParam(value = "key",defaultValue = "") String name){
        try {
            List<Course> list = adminClassCourseService.selectCourse(name);
            return JsonData.buildSuccess(list);
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //通过ID查询课程信息
    @GetMapping("/select_course")
    public JsonData selectCourseById(@RequestParam(value = "id",defaultValue = "null") Integer id){
        try {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(id);
            return JsonData.buildSuccess(adminClassCourseService.selectCourseById(courseDto));
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    /**
     * 教室信息管理
     * @param classroomDto
     * @return
     */

    //添加教室信息
    @PostMapping("/insert_classroom")
    public JsonData insertClassroom(@RequestBody ClassroomDto classroomDto){
        try {
            adminOtherService.insertClassroom(classroomDto);
            return JsonData.buildSuccess("新教室添加成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

//    修改教室信息
    @PutMapping("/update_classroom")
    public JsonData updateClassroom(@RequestBody ClassroomDto classroomDto){
        try {
            adminOtherService.updateClassroom(classroomDto);
            return JsonData.buildSuccess("教室信息修改成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

//    删除教室信息
    @DeleteMapping("/delete_classroom")
    public JsonData deleteClassroom(@RequestParam("id") Integer id){
        try {
            ClassroomDto classroomDto = new ClassroomDto();
            classroomDto.setId(id);
            adminOtherService.deleteClassroom(classroomDto);
            return JsonData.buildSuccess("教室信息删除成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

//    查询教室信息
    @GetMapping("/select_classroom_list")
    public JsonData selectClassroom(@RequestParam(value = "key",defaultValue = "") String name){
        try {
            List<Classroom> list = adminOtherService.selectClassroom(name);
            return JsonData.buildSuccess(list);
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

 //    通过ID查询教室信息
    @GetMapping("/select_classroom")
    public JsonData selectClassroomById(@RequestParam(value = "id",defaultValue = "null") Integer id){
        try {
            ClassroomDto classroomDto = new ClassroomDto();
            classroomDto.setId(id);
            return JsonData.buildSuccess(adminOtherService.selectClassroomById(classroomDto));
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    /**
     * 扣分项管理
     * @param reduceHoursDto
     * @return
     */

    //添加扣分项信息
    @PostMapping("/insert_reduceHour")
    public JsonData insertReduceHour(@RequestBody ReduceHoursDto reduceHoursDto){
        try {
            adminOtherService.insertReduceHours(reduceHoursDto);
            return JsonData.buildSuccess("新扣分项添加成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //修改扣分项信息
    @PutMapping("/update_reduceHour")
    public JsonData updateReduceHour(@RequestBody ReduceHoursDto reduceHoursDto){
        try {
            adminOtherService.updateReduceHours(reduceHoursDto);
            return JsonData.buildSuccess("扣分项信息修改成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //删除扣分项信息
    @DeleteMapping("/delete_reduceHour")
    public JsonData deletetReduceHour(@RequestParam("id") Integer id){
        try {
            ReduceHoursDto reduceHoursDto = new ReduceHoursDto();
            reduceHoursDto.setId(id);
            adminOtherService.deleteReduceHours(reduceHoursDto);
            return JsonData.buildSuccess("扣分项信息删除成功");
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //查询扣分项信息
    @GetMapping("/select_reduceHour_list")
    public JsonData selectReduceHour(@RequestParam(value = "key",defaultValue = "") String name){
        try {
            List<ReduceHours> list = adminOtherService.selectReduceHours(name);
            return JsonData.buildSuccess(list);
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }

    //通过ID查询扣分项信息
    @GetMapping("/select_reduceHour")
    public JsonData selectReduceHourById(@RequestParam(value = "id",defaultValue = "null") Integer id){
        try {
            ReduceHoursDto reduceHoursDto = new ReduceHoursDto();
            reduceHoursDto.setId(id);
            return JsonData.buildSuccess(adminOtherService.selectReduceHoursById(reduceHoursDto));
        }catch (TutorException te){
            return JsonData.buildError(te.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }


}
