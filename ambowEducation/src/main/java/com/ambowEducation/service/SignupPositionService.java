package com.ambowEducation.service;

import com.ambowEducation.po.SignupPosition;

import java.util.List;

public interface SignupPositionService {

    //通过学生id来查看已经报名的职位
    public List<SignupPosition> findHasPositionBySid(int sid);

    //通过职位id来查看报名的学生
    public List<SignupPosition> findHasPositionByPid(int pid);

    //查看学生是否已报名当前职位 如果已经报名 则提示不可重复报名
    public void IsHasPosition(int sid, int pid);

}
