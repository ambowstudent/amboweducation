package com.ambowEducation.service;

import com.ambowEducation.po.User;

public interface UserService {

    //查询用户基本信息
    User findByUsernameBasicInfo(String username);
}
