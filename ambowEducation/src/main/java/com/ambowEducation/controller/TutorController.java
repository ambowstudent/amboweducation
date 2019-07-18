package com.ambowEducation.controller;

import com.ambowEducation.Exception.TutorException;
import com.ambowEducation.dto.HoursHistoryDto;
import com.ambowEducation.dto.StudentBaseInfoDto;
import com.ambowEducation.dto.StudentsHoursInfoDto;
import com.ambowEducation.dto.UpdateStudentInfoDto;
import com.ambowEducation.po.*;
import com.ambowEducation.service.TutorService;
import com.ambowEducation.utils.ExcelUtil;
import com.ambowEducation.utils.FileSuffixUtil;
import com.ambowEducation.utils.JsonData;
import com.ambowEducation.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tutor")
@CrossOrigin(origins="*",maxAge = 3600)
public class TutorController {

    @Autowired
    private TutorService service;

    /**
     * 学生管理
     */

    //文件上传 批量添加学生信息
    @PostMapping("/addStudents")
    public JsonData addStudents(@RequestParam("uploadfile") MultipartFile file
                                /*HttpServletRequest request*/){
        /*String contextPath = request.getServletContext().getContextPath();
        System.out.println(contextPath);*/
        try {
            String name = file.getOriginalFilename();
            String[] split = name.split("\\.");
            boolean flag = FileSuffixUtil.checkFile(name);
            if(flag!=true){
                return JsonData.buildError("不支持的文件类型");
            }
            System.out.println(name);
            String path="tempFile/"+name;
            System.out.println(path);
            File excel=new File(path);
            FileUtils.copyInputStreamToFile(file.getInputStream(),excel);
            List<StudentBaseInfoDto> list = ExcelUtil.getStudentsFromExcel(excel);
            service.addStudents(list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("文件解析失败");
        }
        return JsonData.buildSuccess("导入信息成功");
    }




    @GetMapping("/toStuIndex")//学生管理界面
    public JsonData toStuIndex(@RequestParam(value = "page_no",defaultValue = "1") Integer pageNo,
                               HttpServletRequest request){
       // Tutor tutor= (Tutor) request.getSession().getAttribute("user");
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        User user=(User)principals.getPrimaryPrincipal();
        int id=user.getTutor().getId();
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            return JsonData.buildSuccess(new PageInfo<Student>(service.queryAllStudent(id)));
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
    public JsonData getStuBySNo(@RequestParam(value = "page_no",defaultValue = "1") Integer pageNo,
                                @RequestParam("key") String key,
                                HttpServletRequest request){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        User user=(User)principals.getPrimaryPrincipal();
        int id=user.getTutor().getId();
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            return JsonData.buildSuccess(new PageInfo<Student>(service.queryStudentBysNo(key,id)));
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
    public JsonData getWorkInfo(@RequestParam(value = "page_no",defaultValue = "1") Integer pageNo,
                                @RequestParam("s_id") Integer id){
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            return JsonData.buildSuccess(new PageInfo<Work>(service.queryWorks(id)));
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
    public JsonData toPositionIndex(@RequestParam(value = "page_no",defaultValue = "1") Integer pageNo,
                                    HttpServletRequest request){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        User user=(User)principals.getPrimaryPrincipal();
        //int id=user.getTutor().getId();
        String empNo=user.getTutor().getEmpNo();
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            return JsonData.buildSuccess(new PageInfo<Position>(service.queryAllPositions(empNo)));
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @GetMapping("/queryPositionByKey")
    public JsonData queryPositionByKey(@RequestParam(value = "page_no",defaultValue = "1") Integer pageNo,
                                       @RequestParam("key") String key,
                                       HttpServletRequest request){
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        User user=(User)principals.getPrimaryPrincipal();
        String empNo=user.getTutor().getEmpNo();
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            return JsonData.buildSuccess(
                    new PageInfo<Position>(service.queryPositionsByKeyAndTuEmpNo(key,empNo)));
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @PostMapping("/addPosition")//添加职位
    public JsonData addPosition(@RequestBody Position p,
                                HttpServletRequest request){
        User user= (User)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        String empNo=user.getTutor().getEmpNo();
        try {
            p.setTuEmpNo(empNo);
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
    public JsonData getSignupStus(@RequestParam(value = "page_no",defaultValue = "1") Integer pageNo,
                                  @RequestParam("p_id") Integer id){
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            return JsonData.buildSuccess(new PageInfo<Student>(service.queryStudentSignup(id)));
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
    public JsonData toHoursIndex(@RequestParam(value = "page_no",defaultValue = "1") Integer pageNo,
                                 HttpServletRequest request){
        User user= (User)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int id=user.getTutor().getId();
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            return JsonData.buildSuccess(
                    new PageInfo<StudentsHoursInfoDto>(service.queryStudentsHoursInfo(id)));
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    @GetMapping("/queryStuHoursByKey")
    public JsonData queryStuHoursByKey(@RequestParam(value = "page_no",defaultValue = "1") Integer pageNo,
                                       HttpServletRequest request,
                                       @RequestParam("key") String key){
        User user= (User)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int id=user.getTutor().getId();
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            return JsonData.buildSuccess(
                    new PageInfo<StudentsHoursInfoDto>(service.queryStudentsHoursInfoByKey(id,key)));
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    @PostMapping("/editHours")
    public JsonData editHours(@RequestBody History h,
                              HttpServletRequest request){
        User user= (User)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int id=user.getTutor().getId();
        String name=user.getTutor().getName();
        try {
            h.setTuId(id);
            h.setTuName(name);
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
    public JsonData getHistory(@RequestParam(value = "page_no",defaultValue = "1") Integer pageNo,
                               HttpServletRequest request,
                               @RequestParam("key") String key){
        User user= (User)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int id=user.getTutor().getId();
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            return JsonData.buildSuccess(
                    new PageInfo<HoursHistoryDto>(service.queryHourHistory(id, key)));
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @GetMapping("/downloadSignupInfo")
    public void downloadSignupInfo(@RequestParam("p_id") int pId, HttpServletResponse response, HttpServletRequest request){
        service.downloadSignupInfo(pId, response, request);
    }


}
