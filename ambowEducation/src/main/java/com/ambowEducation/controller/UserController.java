package com.ambowEducation.controller;

import com.ambowEducation.utils.JsonData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins="*",maxAge = 3600)
public class UserController {

    @GetMapping("/find")
    public JsonData find(String name) {
        System.out.println(name);
        return JsonData.buildSuccess();
    }
}
