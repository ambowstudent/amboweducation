package com.ambowEducation.dao;


import com.ambowEducation.po.Work;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface WorkMapper {

    //    查询总条数
    @Select("select count(*) from t_work ")
    public int selectAllCount();

    //    查询各个工作类型对应是数量
    @Select("select count(*) as num,w.type\n" +
            "from t_work w,(select  min(id) as min_id from t_work group by s_id) a,t_student s,t_user u\n" +
            "where w.id=a.min_id and s.id=w.s_id and s.s_no=u.username and u.createtime>DATE_SUB(NOW(),INTERVAL  3 YEAR)\n" +
            "group by w.type;")
    public List<Map<String,Object>> selectEveryTypeCount();

    //求出近三年平均工资第一次就业学生
    @Select("select w.type,avg(w.salary) avg_sal,year(u.createtime) as year\n" +
            "from t_work w,(select  min(id) as min_id from t_work group by s_id) a,t_student s,t_user u\n" +
            "where w.id=a.min_id and s.id=w.s_id and s.s_no=u.username and u.createtime>DATE_SUB(NOW(),INTERVAL  3 YEAR)\n" +
            "group by w.type,year;")
    public List<Map<String,Object>> selectThreeYearSal();
    //查询老师管理下工作类型对应的数量
    @Select("select count(*) num from\n" +
            " t_technical_teacher tech,t_clazz clazz,t_student s,t_user u\n" +
            " where tech.id=clazz.te_id and  clazz.id=s.c_id  and s.s_no=u.username and s.status=1\n" +
            " and u.createtime> DATE_SUB(NOW(),INTERVAL  3 YEAR) and tech.id=#{techId};")
    int selectEveryTypeCountByTechId(@Param("techId") int techId);




    //    查询有多少个学生S_ID
    @Select("select COUNT(DISTINCT s_id) from t_work ;")
    public int selectSIdCount();

    //根据 s_no，公司名，工作类型进行模糊查询
    @Select("select * from t_work where concat(s_id,company_name,type) like concat('%' ,#{arg0 },'%')")
    public List<Work> selectListByKey(String keyWord);

    //根据 s_no 查询
    @Select("select * from t_work where s_id = #{arg0}")
    public List<Work> selectListBySId(Integer s_id);

    //    添加新的就业追踪
    @Insert("insert into t_work values(null,#{sId},#{companyName},#{salary},#{type})")
    public int insertWork(Work work);

    //    修改就业追踪
    @Update("update t_work set s_id = #{sId} ,company_name = #{companyName} , salary = #{salary} , type = #{type} where id = #{id }")
    public int updateWork(Work work);

    //    删除就业追踪
    @Delete("delete from t_work where id = #{arg0 }")
    public int deleteWork(Integer workId);

}