package com.ambowEducation.dao;


import com.ambowEducation.po.History;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface HistoryMapper {
    //增加历史
    @Insert("insert into t_history values(default,#{tuId},#{tuName},#{sId},#{sName},#{reason},#{detail},#{editTime})")
    public int insHistory(History h);

    //查询全部也可模糊查询（学业导师看所有学生的历史）
    public List<History> findAll(String key);

    //学生看自己的历史（可以通过原因模糊查询）
    public List<History> findMyHistory(String key, int sno);
}