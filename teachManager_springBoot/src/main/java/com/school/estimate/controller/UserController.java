package com.school.estimate.controller;

import com.alibaba.fastjson.JSONArray;
import com.school.estimate.domain.User;
import com.school.estimate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/user")
public class UserController {

    @Autowired
    private UserService userService;

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
//        System.err.println(idList);
        try{
            String[] idArr = idList.split(",");
            for (String s : idArr) {
                userService.deleteUser(Long.parseLong(s));
            }
        }catch (Exception e){
            throw e;
        }

        return "200";
    }

    @RequestMapping(value = "delUser",method = RequestMethod.POST)
    @ResponseBody
    public String delUser(Long id) throws Exception {
        userService.deleteUser(id);
        return "200";
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.GET)
    public String gotoAddUser(){
        return "manage/user/addUser";
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(User user){
         Long successLine = userService.saveUser(user);
        //service中存储通用密码
        return successLine.toString();
    }
}
