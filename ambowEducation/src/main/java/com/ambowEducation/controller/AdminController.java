package com.ambowEducation.controller;

import com.ambowEducation.Exception.TechnicalTeacherException;
import com.ambowEducation.dto.TechnicalTeacherInfoDto;
import com.ambowEducation.po.TechnicalTeacher;
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

    //添加技术老师
    @PostMapping("/insert_technical_teacher")
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
    @PutMapping("/update_technical_teacher")
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
    @DeleteMapping("/delete_technical_teacher")
    public JsonData deleteTechnicalTeacher(@RequestBody TechnicalTeacherInfoDto technicalTeacherInfoDto){
         try {
             adminTeacherService.deleteTechnicalTeacher(technicalTeacherInfoDto);
             return JsonData.buildSuccess("技术教师删除成功");
         }catch (TechnicalTeacherException tte){
             return JsonData.buildError(tte.getMessage());
         }catch (Exception ex) {
             return JsonData.buildError(ex.getMessage());
         }
    }


    //查询技术老师
    @GetMapping("/select_technical_teacher")
    public JsonData selectTechnicalTeacher(@RequestParam(value = "pageNo",defaultValue = "1") Integer page ){
        try {
            PageHelper.startPage(page,9);
            List<TechnicalTeacher> list = adminTeacherService.selectTechnicalTeacher();
//            PageInfo info = new PageInfo(list);
            System.out.println(list);
            return JsonData.buildSuccess(list);
        }catch (TechnicalTeacherException tte){
            return JsonData.buildError(tte.getMessage());
        }catch (Exception ex) {
            return JsonData.buildError(ex.getMessage());
        }
    }
}
