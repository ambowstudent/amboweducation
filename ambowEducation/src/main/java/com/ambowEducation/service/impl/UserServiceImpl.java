package com.ambowEducation.service.impl;

import com.ambowEducation.dao.RoleMapper;
import com.ambowEducation.dao.UserMapper;
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
        User user = userMapper.findByName(username);
        if(user==null){
            return null;
        }
        List<Role> roles = roleMapper.findByUserId(user.getId());
        user.setRoles(roles);
        return user;
    }
}
