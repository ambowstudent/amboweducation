package com.ambowEducation.controller;

import com.ambowEducation.Exception.StudentException;
import com.ambowEducation.dto.UserDto;
import com.ambowEducation.po.User;
import com.ambowEducation.service.UserService;
import com.ambowEducation.utils.JsonData;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("modify_pass")
    public JsonData modifyPass(@RequestBody UserDto userDto){

        try {
            User user= (User) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
            userDto.setUsername(user.getUsername());
            userService.modifyUserPassword(userDto);
            return JsonData.buildSuccess("密码修改成功");
        }catch (StudentException e){
            return JsonData.buildError(e.getMessage());
        }catch (Exception e) {
            return JsonData.buildError(e.getMessage());

        }
    }
}
