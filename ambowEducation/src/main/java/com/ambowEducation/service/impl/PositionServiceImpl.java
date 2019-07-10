package com.ambowEducation.service.impl;

import com.ambowEducation.dao.PositionMapper;
import com.ambowEducation.po.Position;
import com.ambowEducation.service.PositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    @Resource
    private PositionMapper positionMapper;

    //查询所有招聘信息
    @Override
    public List<Position> findAll() {
        return positionMapper.queryPositions();
    }

    //通过id查询单个招聘信息
    @Override
    public Position findById(int id) {
        return positionMapper.queryPositionById(id);
    }

    //学生通过position,location,company_name查询招聘信息
    @Override
    public List<Position> findAllByLike(String key) {
        return positionMapper.queryPositionsByKey(key);
    }
}
