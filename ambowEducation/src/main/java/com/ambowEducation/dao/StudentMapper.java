package com.ambowEducation.dao;



import com.ambowEducation.dto.*;
import com.ambowEducation.po.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentMapper {



    public int insertStudents(List<StudentBaseInfoDto> list);//批量增加学生信息

    @Update("update t_student set name=#{name}, school=#{school}," +
            "status=#{status}  where s_no=#{sNo}")
    public int updateStudentAllInfo(UpdateStudentInfoDto student);

    @Update("update t_student set class_hour=class_hour+#{classHour} where id=#{sId}")
    public int updateStudentHoursAdd(StudentHoursDto student);//奖励学时

    @Update("update t_student set class_hour=class_hour-#{classHour} where id=#{sId}")
    public int updateStudentHoursReduce(StudentHoursDto student);//扣学时

    @Update("update t_student set first_employment=#{firstEmployment}," +
            "first_salary=#{firstSalary} where id=#{sId}")
    public int updateStudentFirstWork(StudentFirstWorkDto student);//添加学生第一次的就业信息

    @Select("select first_employment,first_salary from t_student where id=#{id}")
    public StudentFirstWorkDto queryStudentFirstWork(Integer id);


    @Update("update t_student set status=#{status} where s_no=#{sNo}")
    public int updateStudentStatus(@Param("sNo") String sNo,
                                   @Param("status") Integer status);//更新学生的状态

    @Select("select interview_history from t_student where s_no=#{sNo}")
    public String queryInterviewHistoryBySNo(String sNo);//查询学生曾经面试的公司

    @Delete("delete from t_student where s_no=#{sNo}")
    public int deleteStudentBySNO(String sNo);//通过学生的学号来删除一条学生信息

    @Update("update t_student set status=-1 where s_no=#{sNo}")
    public int changeStatus(String sNo);


    //更新学生的班级
    @Update("update t_student set c_id=#{cId} where s_no=#{sNo}")
    public int updateStudentClassBySno(@Param("sNo") String sNo,
                                       @Param("cId") Integer cId);
    //批量插入学生的班级
    public int updateAllStudentClassBySno(@Param("list") List<StudentClassDto> list);

    //查询学生所有信息
    public List<Student> findAll();

    //查询单个学生信息
    public Student findStudentBySno(String sno);

    //查询单个学生信息（只查学生表）
    @Select("select * from t_student where s_no=#{arg0}")
    public Student findStudentBySnoOnlyStudent(String sno);

    //查询所有学生信息（只查学生表）
    @Select("select * from t_student")
    public Student findAllOnlyStudent();

    //查询学业导师带的学生
    @Select("select s.* from " +
            "t_student s,t_clazz c,t_tutor tu " +
            "where s.c_id=c.id and c.tu_id=tu.id and tu.id=#{tuId} ")
    public List<Student> findStudentsByTuId(Integer tuId);

    @Select("select s.* from " +
            "t_student s,t_clazz c,t_tutor tu " +
            "where s.c_id=c.id and c.tu_id=tu.id and tu.id=#{tuId} " +
            "and concat(s.s_no,s.name,c.name,s.school) like concat('%',#{key},'%')")
    List<Student> queryStudentByKeyAndTuId(@Param("key") String key,
                                           @Param("tuId") Integer tuId);

    //通过学生id查询学生
    @Select("select * from t_student where id=#{id}")
    public Student queryStudentById(Integer id);

    //通过学生的状态进行查询(正常实训，已就业，未就业，退学)
    @Select("select * from t_student where status=#{status}")
    public List<Student> findStudentsByStatus(Integer status);

    /**
     * 文件上传，更新数据库的字段
     */
    @Update("update t_student set photo=#{photo} where s_no=#{sNo}")
    public int updateStudentPhoto(@Param("photo") String photo,
                                  @Param("sNo") String sNo);//上传照片


    @Update("update t_student set resume=#{resume} where s_no=#{sNo}")
    public int updateStudentResume(@Param("resume") String resume,
                                  @Param("sNo") String sNo);//上传简历

    @Select("select photo from t_student where s_no=#{sNo}")
    public String queryPhotoUrlBySNo(String sNo);//查询照片的URL

    @Select("select resume from t_student where s_no=#{sNo}")
    public String queryResumeUrlBySNo(String sNo);//查询简历的url

    //学生修改自己的图片
    @Update("update t_student set photo=#{arg0} where id=#{arg1}")
    public int updStudentPhoto(String path, int id);


    @Select("select s_no from t_student where name=#{name} and phone=#{phone}")
    String querySNoByPhoneAndName(@Param("name") String name,
                                  @Param("phone") String phone);

    //查询学生所有的个数
    @Select("select count(*) from t_student")
    int findAllStudentCount();
}