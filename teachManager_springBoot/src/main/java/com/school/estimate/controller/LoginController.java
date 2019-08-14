package com.school.estimate.controller;

import com.school.estimate.domain.Permission;
import com.school.estimate.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "/login";
    }

    @RequestMapping(value = {"/index",""}, method = RequestMethod.GET)
    public String gotoIndex(String username, Model model) {
        //获取当前登录的用户
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        List<Permission> permissionList = new ArrayList<>();
        for (GrantedAuthority authoritie : authorities) {
            //获取角色名称
            String roleName = authoritie.getAuthority();

            //存放一级菜单
            List<Permission> list = permissionService.findPermissByRoleName(roleName);

            for (Permission permission : list) {
                if (permission.getPermissionLevel() != 1) {
                    continue;
                } else {
                    if (permissionList.contains(permission)) {
                        continue;
                    } else {
                        permissionList.add(permission);
                    }
                }

            }

        }
        model.addAttribute("permissionList", permissionList);

        return "/index";
    }

    @RequestMapping(value = "/loginSuccess", method = RequestMethod.POST)
    public String loginSuccess(String username, Model model) {
        return "redirect:/index";
    }

    @RequestMapping(value = "/loginError", method = RequestMethod.POST)
    public String loginError(Model model) {
//        model.addAttribute("loginError", "用户名或密码错误");
        return "/login";
    }
}
