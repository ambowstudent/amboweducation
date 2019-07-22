package com.ambowEducation.controller;

import com.ambowEducation.Exception.TutorException;
import com.ambowEducation.dao.ClassMapper;
import com.ambowEducation.dao.StudentMapper;
import com.ambowEducation.dto.*;
import com.ambowEducation.po.*;
import com.ambowEducation.service.AdminClassCourseService;
import com.ambowEducation.service.TutorService;
import com.ambowEducation.utils.ExcelUtil;
import com.ambowEducation.utils.FileSuffixUtil;
import com.ambowEducation.utils.JsonData;
import com.ambowEducation.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class TutorController {

    @Autowired
    private TutorService service;
    @Autowired
    private AdminClassCourseService classService;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 学生管理
     */

    //文件上传 批量添加学生信息
    @PostMapping("/addStudents")
    public JsonData addStudents(@RequestParam("uploadfile") MultipartFile file) {
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            boolean flag = FileSuffixUtil.checkFile(suffix);
            if (flag != true) {
                return JsonData.buildError("不支持的文件类型");
            }
            File excel = File.createTempFile(System.currentTimeMillis()+"",suffix);
            file.transferTo(excel);
            List<StudentBaseInfoDto> list = ExcelUtil.getStudentsFromExcel(excel);
            service.addStudents(list);
            ExcelUtil.deleteFile(excel);
            return JsonData.buildSuccess("导入信息成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("文件解析失败");
        }
    }

    @PostMapping("/addStuClass")//文件上传 批量给学生添加班级
    public JsonData addStuClass(@RequestParam("file") MultipartFile file){
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            boolean flag = FileSuffixUtil.checkFile(suffix);
            if (flag != true) {
                return JsonData.buildError("不支持的文件类型");
            }
            File excel = File.createTempFile(System.currentTimeMillis()+"",suffix);
            file.transferTo(excel);
            String cName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("-") + 1, file.getOriginalFilename().lastIndexOf("."));
            Integer cId = classMapper.selectByClassname(cName).getId();
            List<StudentClassDto> list = ExcelUtil.getStudentList(excel,cId);
            service.addClazz(list);
            ExcelUtil.deleteFile(excel);
            return JsonData.buildSuccess("导入信息成功");
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("文件解析失败...");
        }
    }

    @PostMapping("/addStuDormitory")//文件上传 批量给学生添加宿舍
    public JsonData addStuDormitory(@RequestParam("file") MultipartFile file){
        try {
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            boolean flag = FileSuffixUtil.checkFile(suffix);
            if (flag != true) {
                return JsonData.buildError("不支持的文件类型");
            }
            File excel = File.createTempFile(System.currentTimeMillis()+"",suffix);
            file.transferTo(excel);
            service.addDormitory(ExcelUtil.getStudentDormitory(excel,studentMapper));
            ExcelUtil.deleteFile(excel);
            return JsonData.buildSuccess("导入信息成功");
        }catch (TutorException e){
            return JsonData.buildError(e);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError("文件解析失败...");
        }
    }



    @GetMapping("/toStuIndex")//学生管理界面
    public JsonData toStuIndex(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo,
                               @RequestParam("key") String key) {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        User user = (User) principals.getPrimaryPrincipal();
        int id = user.getTutor().getId();
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            List<Student> list;
            if ("".equals(key)) {
                list = service.queryAllStudent(id);
            } else {
                list = service.queryStudentBysNo(key, id);
            }
            return JsonData.buildSuccess(new PageInfo<Student>(list));
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
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
    }

    @GetMapping("/getStuBySNo")//学业导师通过关键字进行查询
    public JsonData getStuBySNo(@RequestParam(value = "page_no",defaultValue = "1") Integer pageNo,
                                @RequestParam("key") String key,
                                HttpServletRequest request){

    }*/


    @GetMapping("/toUpdateStu") //去到更新界面
    public JsonData toUpdateStu() {
        try {
            List<Clazz> list = classService.selectClazz();
            return JsonData.buildSuccess(list);
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @PutMapping("/updateStu")//更新学生基本信息
    public JsonData updateStu(@RequestBody UpdateStudentInfoDto stu) {
        try {
            service.updateStudent(stu);
            return JsonData.buildSuccess(0);
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @GetMapping("/getWorkInfo")//获取就业信息
    public JsonData getWorkInfo(@RequestParam("s_id") Integer id) {
        try {
            List<Work> list = service.queryWorks(id);
            return JsonData.buildSuccess(list);
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @PostMapping("/addWorkInfo")//添加就业信息
    public JsonData addWorkInfo(@RequestBody Work work) {
        try {
            service.addWork(work);
            return JsonData.buildSuccess(0);
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    /**
     * 招聘信息管理
     */

    @GetMapping("/toPositionIndex")//招聘信息管理首页
    public JsonData toPositionIndex(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo,
                                    @RequestParam("key") String key) {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        User user = (User) principals.getPrimaryPrincipal();
        //int id=user.getTutor().getId();
        String empNo = user.getTutor().getEmpNo();
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            List<Position> list;
            if("".equals(key)){
                 list = service.queryAllPositions(empNo);
            }else {
                list = service.queryPositionsByKeyAndTuEmpNo(key, empNo);
            }
            return JsonData.buildSuccess(new PageInfo<Position>(list));
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

   /* @GetMapping("/queryPositionByKey")
    public JsonData queryPositionByKey(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo,
                                       @RequestParam("key") String key,
                                       HttpServletRequest request) {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        User user = (User) principals.getPrimaryPrincipal();
        String empNo = user.getTutor().getEmpNo();
        try {
            List<Position> list = service.queryPositionsByKeyAndTuEmpNo(key, empNo);
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            return JsonData.buildSuccess(
                    new PageInfo<Position>(list));
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }*/

    @PostMapping("/addPosition")//添加职位
    public JsonData addPosition(@RequestBody Position p,
                                HttpServletRequest request) {
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        String empNo = user.getTutor().getEmpNo();
        try {
            p.setTuEmpNo(empNo);
            p.setCreatetime(new Date());
            p.setStatus(0);
            service.addPosition(p);
            return JsonData.buildSuccess(0);
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @GetMapping("/toUpdatePosition")//去到职位修改界面
    public JsonData toUpdatePosition(@RequestParam("p_id") Integer id) {
        try {
            Position position = service.queryPositionById(id);
            return JsonData.buildSuccess(position);
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @PutMapping("/overPosition")
    public JsonData overPosition(@RequestParam("p_id") Integer id) {
        try {
            service.overPosition(id);
            return JsonData.buildSuccess(0);
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @PutMapping("/updatePosition")//修改职位
    public JsonData updatePosition(@RequestBody Position p) {
        try {
            p.setCreatetime(new Date());
            service.updatePosition(p);
            return JsonData.buildSuccess(0);
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }

    @DeleteMapping("/deletePosition")//删除职位
    public JsonData deletePosition(@RequestParam("p_id") Integer id) {
        try {
            service.deletePosition(id);
            return JsonData.buildSuccess(0);
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    @GetMapping("/getSignupStus")//获取报名的学生
    public JsonData getSignupStus(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo,
                                  @RequestParam("p_id") Integer id) {
        try {
            List<Student> list = service.queryStudentSignup(id);
            return JsonData.buildSuccess(list);
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    /**
     * 学时管理模块
     */
    @GetMapping("/toHoursIndex")//所有学生的学时信息
    public JsonData toHoursIndex(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo,
                                 @RequestParam("key") String key) {
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int id = user.getTutor().getId();
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            List<StudentsHoursInfoDto> list;
            if("".equals(key)){
                list = service.queryStudentsHoursInfo(id);
            }else {
                list = service.queryStudentsHoursInfoByKey(id, key);
            }
            return JsonData.buildSuccess(new PageInfo<>(list));
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


   /* @GetMapping("/queryStuHoursByKey")
    public JsonData queryStuHoursByKey(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo,
                                       HttpServletRequest request,
                                       @RequestParam("key") String key) {
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int id = user.getTutor().getId();
        try {
            List<StudentsHoursInfoDto> list = service.queryStudentsHoursInfoByKey(id, key);
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            return JsonData.buildSuccess(
                    new PageInfo<StudentsHoursInfoDto>(list));
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }*/


    @PostMapping("/editHours")
    public JsonData editHours(@RequestBody History h) {
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int id = user.getTutor().getId();
        String name = user.getTutor().getName();
        try {
            h.setTuId(id);
            h.setTuName(name);
            h.setEditTime(new Date());
            service.updateClassHours(h);
            return JsonData.buildSuccess(0);
        } catch (TutorException e) {
            return JsonData.buildError(e);
        } catch (Exception e) {
            return JsonData.buildError(e);
        }
    }


    @GetMapping("/getHistory")
    public JsonData getHistory(@RequestParam(value = "page_no", defaultValue = "1") Integer pageNo,
                               @RequestParam("key") String key) {
        User user = (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        int id = user.getTutor().getId();
        try {
            PageHelper.startPage(pageNo, PageUtil.PAGE_SIZE);
            List<HoursHistoryDto> list = service.queryHourHistory(id, key);
            return JsonData.buildSuccess(
                    new PageInfo<HoursHistoryDto>(list));
        } catch (TutorException e) {
            e.printStackTrace();
            return JsonData.buildError(e);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonData.buildError(e);
        }
    }

    @GetMapping("/downloadSignupInfo")
    public void downloadSignupInfo(@RequestParam("p_id") int pId, HttpServletResponse response, HttpServletRequest request) {
        service.downloadSignupInfo(pId, response, request);
    }


}
