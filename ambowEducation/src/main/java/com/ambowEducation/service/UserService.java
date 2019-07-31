package com.ambowEducation.service;

import com.ambowEducation.dto.UserDto;
import com.ambowEducation.po.User;

public interface UserService {

    //查询用户基本信息
    User findByUsernameBasicInfo(String username);
    //查询用户的基本信息及权限
    User findAllUserAndRoleAndPer(String username);

    //修改密码
    void modifyUserPassword(UserDto userDto) throws Exception;
}
