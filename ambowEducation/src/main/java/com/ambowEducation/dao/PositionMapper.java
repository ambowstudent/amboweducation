package com.ambowEducation.dao;


import com.ambowEducation.po.Position;
import org.apache.ibatis.annotations.Insert;

public interface PositionMapper {
    //发布招聘信息
    @Insert("insert into t_position values(default, #{position},#{salary},#{companyName},#{location},#{detail},#{createtime})")
    public int insPosition(Position p);

}