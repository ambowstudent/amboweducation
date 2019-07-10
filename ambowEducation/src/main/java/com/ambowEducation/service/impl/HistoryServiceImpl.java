package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.HistoryException;
import com.ambowEducation.dao.HistoryMapper;
import com.ambowEducation.po.History;
import com.ambowEducation.service.HistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Resource
    private HistoryMapper historyMapper;

    //新增历史表
    @Override
    public void insHistory(History h) {
        int flag = historyMapper.insHistory(h);
        //判断是否添加成功
        if (flag <= 0){
            throw new HistoryException(1, "添加失败");
        }
        System.out.println("历史表添加成功");
    }

    //学生通过自己的学号查看自己历史（可以模糊查询）
    @Override
    public List<History> findMyHistory(String key, String sno) {
        return historyMapper.findMyHistory(key, sno);
    }
}
