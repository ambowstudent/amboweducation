package com.ambowEducation.service;

import com.ambowEducation.po.Position;

import java.util.List;

public interface PositionService {

    //查询所有招聘信息
    public List<Position> findAll();

    //通过id查询单个招聘信息
    public Position findById(int id);

    //学生通过position,location,company_name查询招聘信息
    public List<Position> findAllByLike(String key);
}
