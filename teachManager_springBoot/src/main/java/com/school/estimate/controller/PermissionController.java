package com.school.estimate.controller;

import com.alibaba.fastjson.JSONArray;
import com.school.estimate.domain.Permission;
import com.school.estimate.domain.Student;
import com.school.estimate.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String gotoPermission() {
        return "/manage/permission/permissionList";
    }

    @RequestMapping(value = "/permissionList")
    @ResponseBody
    public String permissionList() {
        List<Student> allPermission = permissionService.findAllPermission();
        Map<String, Object> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "ok");
        map.put("data", allPermission);
        map.put("count", allPermission.size());
        String jsonString = JSONArray.toJSONString(map);
        return jsonString;
    }

    @RequestMapping(value = "addPermission", method = RequestMethod.GET)
    public String gotoAddPermission() {
        return "/manage/permission/addPermission";
    }

    @RequestMapping(value = "savePermission", method = RequestMethod.POST)
    public String savePermission(Permission permission) {
        Long aLong = permissionService.savePermission(permission);
        return aLong.toString();
    }

}
