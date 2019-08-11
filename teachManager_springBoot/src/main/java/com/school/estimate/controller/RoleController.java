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

    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public String addRole(Role role, String[] authArr) {
        Long aLong = roleService.saveRole(role, authArr);
        if (aLong < 1) {
            return aLong.toString();
        }

        return aLong.toString();
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.GET)
    public String gotoUpdateUser() {
        return "/manage/role/updateRole";
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    @ResponseBody
    public String updateRole(Role role, String[] authArr) {
        Long aLong = roleService.updateRole(role, authArr);
        if (aLong < 1) {
            return aLong.toString();
        }

        return aLong.toString();
    }

    @RequestMapping(value = "/permissionList")
    @ResponseBody
    public String permissionList(String showType) {
        List<Permission> permissions = permissionService.findAllPermission();
        List list = getTreeData(permissions,null,showType);
        Map map = new HashMap();
        map.put("trees", list);

        Map returnMap = new LinkedHashMap<>();
        returnMap.put("code", "0");
        returnMap.put("msg", "获取成功");
        returnMap.put("data", map);
        String jsonString = JSONArray.toJSONString(returnMap);
        return jsonString;
    }

    @RequestMapping(value = "/permissionDetail")
    @ResponseBody
    public String permissionDetail(Long roleId, String showType) {
        List<Permission> permissions = permissionService.findAllPermission();
        List<Role_Permission> rolePermission = roleService.findPermissionByRoleId(roleId);
        List list = getTreeData(permissions, rolePermission, showType);
        Map map = new HashMap();
        map.put("trees", list);

        Map returnMap = new LinkedHashMap<>();
        returnMap.put("code", "0");
        returnMap.put("msg", "获取成功");
        returnMap.put("data", map);
        String jsonString = JSONArray.toJSONString(returnMap);
        return jsonString;
    }



    @RequestMapping(value = "/roleDetail", method = RequestMethod.GET)
    public String roleDetail(Long id, Model model) {
        Role role = roleService.findRoleById(id);

        model.addAttribute("role", role);
        return "/manage/role/roleDetail";
    }

    private List<Object> getTreeData(List<Permission> permissions, List<Role_Permission> rolePermission, String showType) {
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
                if (showType.equals("2") || showType.equals("3")) {
                    for (Role_Permission role_permission : rolePermission) {
                        if (role_permission != null && role_permission.getPermissionId() == permission.getId()) {
                            parMap.put("checked", true);
                        }
                    }
                    if (showType.equals("3")) {
                        parMap.put("disabled", true);
                    }
                }

                parMap = getChildPermission(parMap, permission, permissions, perParlist, rolePermission, showType);
                list.add(parMap);

            }
        }
        return list;
    }

    private Map<String, Object> getChildPermission(Map<String, Object> map, Permission tPer, List<Permission> permissions, List perParlist, List<Role_Permission> rolePermission, String showType) {
        ArrayList<Object> list = new ArrayList<>();
        for (Permission permission : permissions) {
            Map<String, Object> childMap = new LinkedHashMap<>();
            if (permission.getParentId() == tPer.getId()) {
                childMap.put("name", permission.getName());
                childMap.put("value", permission.getId());
                if (showType.equals("2") || showType.equals("3")) {
                    for (Role_Permission role_permission : rolePermission) {
                        if (role_permission != null && role_permission.getPermissionId() == permission.getId()) {
                            childMap.put("checked", true);
                        }
                    }
                    if (showType.equals("3")) {
                        childMap.put("disabled", true);
                    }
                }
                String id = String.valueOf(permission.getId());
                if (perParlist.contains(id)) {
                    childMap = getChildPermission(childMap, permission, permissions, perParlist, rolePermission, showType);
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
