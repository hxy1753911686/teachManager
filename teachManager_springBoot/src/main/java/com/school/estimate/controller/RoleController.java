package com.school.estimate.controller;

import com.alibaba.fastjson.JSONArray;
import com.school.estimate.domain.Permission;
import com.school.estimate.domain.Role;
import com.school.estimate.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String gotoRole() {
        return "/manage/role/roleList";
    }

    @RequestMapping(value = "/roleList")
    @ResponseBody
    public String roleList() {
        List<Role> allRole = roleService.findAllRole();
        Map<String, Object> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "ok");
        map.put("data", allRole);
        map.put("count", allRole.size());
        String jsonString = JSONArray.toJSONString(map);
        return jsonString;
    }
}
