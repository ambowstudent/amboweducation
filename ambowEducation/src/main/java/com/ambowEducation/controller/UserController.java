package com.ambowEducation.controller;

import com.ambowEducation.utils.JsonData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @PostMapping("/find")
    public JsonData find(String name) {
        System.out.println(name);
        return JsonData.buildSuccess();
    }
}
