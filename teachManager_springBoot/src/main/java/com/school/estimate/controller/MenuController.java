package com.school.estimate.controller;

import com.school.estimate.domain.Permission;
import com.school.estimate.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/makeMenu", method = RequestMethod.POST)
    @ResponseBody
    public List<Permission> makeMenu(Long id, Integer level) {
        System.err.println("farId:" + id + "farLevel:" + level);

        //获取当前登录的用户
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        List<Permission> permissionList = new ArrayList<>();
        for (GrantedAuthority authoritie : authorities) {
            //获取角色名称
            String roleName = authoritie.getAuthority();

            //获取并存放2/3级菜单
            List<Permission> list = permissionService.findPermissByRoleName(roleName);

            for (Permission permission : list) {
                int permissionLevel = permission.getPermissionLevel();
                if(permissionLevel == 2 || permissionLevel == 3){
                    if (permissionList.contains(permission)) {
                        continue;
                    } else {
                        permissionList.add(permission);
                    }
                }else{
                    continue;

                }
            }
        }

        return permissionList;
    }
}
