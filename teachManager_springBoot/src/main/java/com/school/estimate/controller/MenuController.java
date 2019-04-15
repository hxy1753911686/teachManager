package com.school.estimate.controller;

import com.school.estimate.domain.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MenuController {

    @RequestMapping(value = "/makeMenu",method = RequestMethod.POST)
    @ResponseBody
    public List<Permission> makeMenu(Long id,Integer level){
        System.err.println("farId:" + id + "farLevel:"+level);
        List list = new ArrayList<>();
        Permission permission = new Permission();
        list.add(permission);
        return list;
    }
}
