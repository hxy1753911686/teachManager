package com.school.estimate.controller;

import com.alibaba.fastjson.JSONArray;
import com.school.estimate.domain.Permission;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.Role_Permission;
import com.school.estimate.service.PermissionService;
import com.school.estimate.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

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

    @RequestMapping(value = "/addRole", method = RequestMethod.GET)
    public String gotoAddUser() {
        return "/manage/role/addRole";
    }

    @RequestMapping(value = "/permissionList")
    @ResponseBody
    public String permissionList() {
        List<Permission> permissions = permissionService.findAllPermission();
        List list = getTreeData(permissions);
        Map map = new HashMap();
        map.put("trees", list);

        Map returnMap = new LinkedHashMap<>();
        returnMap.put("code", "0");
        returnMap.put("msg", "获取成功");
        returnMap.put("data", map);
        String jsonString = JSONArray.toJSONString(returnMap);
        return jsonString;
    }

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public String addRole(Role role, String[] authArr) {
        Long aLong = roleService.saveRole(role, authArr);
        if (aLong < 1) {
            return aLong.toString();
        }

        return aLong.toString();
    }

    @RequestMapping(value = "/roleDetail", method = RequestMethod.GET)
    @ResponseBody
    public String roleDetail(Long id, Model model) {
        Role role = roleService.findRoleById(id);
//        List<Role_Permission> list = roleService.findPermissionByRoleId(id);

        model.addAttribute("role", role);
        return "/manage/role/addRole";
    }

    private List<Object> getTreeData(List<Permission> permissions) {
        Map<String, Object> map = new LinkedHashMap<>();
        String[] perParArr = new String[permissions.size()];
        for (int i = 0; i < permissions.size(); i++) {
            perParArr[i] = String.valueOf(permissions.get(i).getParentId());
        }
        List perParlist = Arrays.asList(perParArr);

        ArrayList<Object> list = new ArrayList<>();
        for (Permission permission : permissions) {
            if (permission.getParentId() == 0) {
                Map<String, Object> parMap = new LinkedHashMap<>();
                parMap.put("name", permission.getName());
                parMap.put("value", permission.getId());

                parMap = getChildPermission(parMap, permission, permissions, perParlist);
                list.add(parMap);

            }
        }
        return list;
    }

    private Map<String, Object> getChildPermission(Map<String, Object> map, Permission tPer, List<Permission> permissions, List perParlist) {
        ArrayList<Object> list = new ArrayList<>();
        for (Permission permission : permissions) {
            Map<String, Object> childMap = new LinkedHashMap<>();
            if (permission.getParentId() == tPer.getId()) {
                childMap.put("name", permission.getName());
                childMap.put("value", permission.getId());
                String id = String.valueOf(permission.getId());
                if (perParlist.contains(id)) {
                    childMap = getChildPermission(childMap, permission, permissions, perParlist);
                }

            }
            if (!childMap.isEmpty()) {
                list.add(childMap);
            }
        }
        if (!list.isEmpty()) {
            map.put("list", list);
        }
        return map;
    }
}
