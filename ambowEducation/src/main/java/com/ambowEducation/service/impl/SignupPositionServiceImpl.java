package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.SignupPositionException;
import com.ambowEducation.dao.SignupPositionMapper;
import com.ambowEducation.po.SignupPosition;
import com.ambowEducation.service.SignupPositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SignupPositionServiceImpl implements SignupPositionService {

    @Resource
    private SignupPositionMapper signupPositionMapper;

    //通过学生id来查看已经报名的职位
    @Override
    public List<SignupPosition> findHasPositionBySid(int sid) {
        return signupPositionMapper.queryPositionsBysId(sid);
    }

    //通过职位id来查看报名的学生
    @Override
    public List<SignupPosition> findHasPositionByPid(int pid) {
        return signupPositionMapper.queryStudentsBypId(pid);
    }

    //查看学生是否已报名当前职位 如果已经报名 则提示不可重复报名
    @Override
    public void IsHasPosition(int sid, int pid) {
        SignupPosition signupPosition = signupPositionMapper.queryRecord(sid, pid);
        if (signupPosition == null){
            throw new SignupPositionException(0, "未报名");
        }else{
            throw new SignupPositionException(1, "已报名");
        }
    }
}
