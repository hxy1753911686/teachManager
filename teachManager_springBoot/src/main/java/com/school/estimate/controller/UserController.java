package com.school.estimate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manage")
public class UserController{

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String gotoIndex(){
        System.err.print("进来了");
        return "/manage/user/userList";
    }
}
