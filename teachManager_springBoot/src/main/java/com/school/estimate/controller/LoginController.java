package com.school.estimate.controller;

import com.school.estimate.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "/login";
    }

    @RequestMapping(value = "/loginError",method = RequestMethod.POST)
    public String loginError(Model model) {
        model.addAttribute("loginError","用户名或密码错误");
        return "/login";
    }
}
