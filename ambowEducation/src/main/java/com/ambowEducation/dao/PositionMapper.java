package com.ambowEducation.dao;


import com.ambowEducation.po.Position;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 数据库中添加字段， 招聘已完成
 */
public interface PositionMapper {
    //发布招聘信息
    @Insert("insert into t_position values(default, #{position},#{salary},#{companyName},#{location},#{detail},#{createtime},#{tuEmpNo},#{status})")
    public int insPosition(Position p);

    //删除招聘信息
    @Delete("delete from t_position where id=#{id}")
    public int deletePosition(Integer id);

    //修改招聘信息，记录修改时间并更新到createtime字段中
    @Update("update t_position set position=#{position},salary=#{salary},company_name=#{companyName}," +
            "location=#{location},detail=#{detail},createtime=#{createtime} where id=#{id}")
    public int updatePosition(Position p);

    //通过ID查询某条招聘信息
    @Select("select * from t_position where id=#{id}")
    public Position queryPositionById(Integer id);

    //查询某个班主任所发布的职位(班主任查看自己所发布的职位)
    @Select("select * from t_position where tu_empno=#{tuEmpNo}")
    public List<Position> queryPositionsByTuEmpNo(String tuEmpNo);

    //查询所有的职位
    @Select("select * from t_position")
    public List<Position> queryPositions();

    //模糊查询
    @Select("select * from t_position where CONCAT(position,location,company_name) like concat('%',#{key},'%')")
    public List<Position> queryPositionsByKey(String key);

    @Select("select status from t_position where id=#{pId}")
    public int queryPositionStatusById(Integer pId);

    @Update("update t_position set status=1 where id=#{pId}")
    public int updatePositionStatus(Integer pId);


    @Select("select * from t_position where CONCAT(position,location,company_name) " +
            "like concat('%',#{key},'%') and tu_empno=#{tuEmpNo}")
    List<Position> queryPositionsByKeyAndTuEmpNo(@Param("key") String key,
                                                 @Param("tuEmpNo") String tuEmpNo);
}