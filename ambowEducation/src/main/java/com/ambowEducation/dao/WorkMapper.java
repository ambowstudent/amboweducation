package com.ambowEducation.dao;


import com.ambowEducation.po.Work;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface WorkMapper {

//    查询总条数
    @Select("select count(*) from t_work ")
    public int selectAllCount();

//    查询各个工作类型对应是数量
    @Select("select type , count(*) as num from t_work group by type")
    public List<Map<String,Object>> selectEveryTypeCount();

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