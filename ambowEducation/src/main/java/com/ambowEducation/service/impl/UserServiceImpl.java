package com.ambowEducation.service.impl;

import com.ambowEducation.Exception.UserException;
import com.ambowEducation.dao.RoleMapper;
import com.ambowEducation.dao.UserMapper;
import com.ambowEducation.dto.UserDto;
import com.ambowEducation.po.Role;
import com.ambowEducation.po.User;
import com.ambowEducation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User findByUsernameBasicInfo(String username) {
       return userMapper.findByName(username);

    }

    @Override
    public User findAllUserAndRoleAndPer(String username) {
        User user = userMapper.findByName(username);
        if(user==null){
            return null;
        }
        List<Role> roleList = roleMapper.findByUserId(user.getId());
        user.setRoles(roleList);
        return user;
    }

    @Override
    public void modifyUserPassword(UserDto userDto) throws Exception{

        User user = userMapper.findByUserByOldPassword(userDto);
        if(user==null){
            throw new UserException(-1,"原密码错误，请重新输入");
        }
        userMapper.updatePasswordByUsername(userDto);
    }
}
