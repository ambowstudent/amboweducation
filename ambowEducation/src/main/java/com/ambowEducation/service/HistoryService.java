package com.ambowEducation.service;

import com.ambowEducation.po.History;

import java.util.List;

public interface HistoryService {

    //新增历史表
    public void insHistory(History h);

    //学生通过自己的学号查看自己历史（可以模糊查询）
    public List<History> findMyHistory(String key, String sno);
}
