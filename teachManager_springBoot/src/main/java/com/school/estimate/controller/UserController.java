package com.school.estimate.controller;

import com.alibaba.fastjson.JSONArray;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.User;
import com.school.estimate.domain.User_Role;
import com.school.estimate.service.RoleService;
import com.school.estimate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String gotoIndex() {
        return "/manage/user/userList";
    }

    @RequestMapping(value = "/userList")
    @ResponseBody
    public String getUserList(int page, int limit) {
        int start = (page - 1) * limit;
        int size = limit;
        List<User> userList = userService.findUserByPage(start, size);
        List<User> allUser = userService.findAllUser();
        Map<String, Object> map = new HashMap<>();
        map.put("code", "0");
        map.put("msg", "");
        map.put("count", allUser.size());
        map.put("data", userList);
        String mapJson = JSONArray.toJSONString(map);
        return mapJson;
    }

    @RequestMapping(value = "/delMulUser", method = RequestMethod.POST)
    @ResponseBody
    public String delMulUser(String idList) throws Exception {
        return userService.delMulUser(idList).toString();
    }

    @RequestMapping(value = "delUser", method = RequestMethod.POST)
    @ResponseBody
    public String delUser(Long id) throws Exception {
        userService.deleteUser(id);
        return "200";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String gotoAddUser(Model model) {
        List<Role> allRole = roleService.findAllRole();
        model.addAttribute("roleList", allRole);
        return "manage/user/addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(User user,String roleIds) {
        Long successLine = userService.saveUser(user,roleIds);
        //service中存储通用密码
        return successLine.toString();
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.GET)
    public String gotoUpdateUser(Long id, Model model) {
        User user = userService.findUserById(id);
        List<Role> allRole = roleService.findAllRole();
        User_Role user_role = userService.findUserRoleOfUserId(id);
        List<Integer> list = new ArrayList<>();

        if(user_role != null){
            String[] split = user_role.getRoleIds().split(",");
            for (String s : split) {
                if(s != null && !"".equals(s)){
                    list.add(Integer.parseInt(s));
                }
            }
            model.addAttribute("userRoleId",user_role.getId());
        }

        model.addAttribute("user", user);
        model.addAttribute("roleList", allRole);
        model.addAttribute("roles",list);

        return "manage/user/updateUser";
    }

    @RequestMapping(value = "updateUser", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(User user,String roleIds,Integer userRoleId) {
        userService.updateUser(user,roleIds,userRoleId);
        return "200";
    }
}
