package com.school.estimate.controller;

import com.school.estimate.domain.Permission;
import com.school.estimate.domain.Student;
import com.school.estimate.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
    public String loginPage(String username,Model model) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        List<Permission> permissionList = new ArrayList<>();
        for (GrantedAuthority authoritie : authorities) {
            //获取角色名称
            String roleName = authoritie.getAuthority();

            //存放一级菜单
            List<Permission> list = permissionService.findFirstPermissByRoleName(roleName);

            for(Permission permission:list){
                //TODO：
                if(permissionList.contains(permission)){
                    continue;
                }
            }




        }
        return "/login";
    }

    @RequestMapping(value = "/loginError",method = RequestMethod.POST)
    public String loginError(Model model) {
        model.addAttribute("loginError","用户名或密码错误");
        return "/login";
    }
}
