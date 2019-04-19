package com.school.estimate.controller.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/manage/user")
public class UserController extends manageBaseController{

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String gotoIndex(){
        System.err.print("进来了");
        return "manage/user/userManage";
    }
}
