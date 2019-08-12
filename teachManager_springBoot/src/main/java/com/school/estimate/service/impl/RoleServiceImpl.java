package com.school.estimate.service.impl;

import com.school.estimate.dao.RoleDao;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.Role_Permission;
import com.school.estimate.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//TODO: 加入redis
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleDao.findRoleById(id);
    }

    @Override
    public List<Role> findAllRole() {
        return roleDao.findAllRole();
    }

    @Override
    public Long saveRole(Role role,String[] permissionArr) {
        role.setRoleCode("ROLE_" + role.getRoleCode());
        Long aLong = roleDao.saveRole(role);
        if (aLong < 1) {
            return aLong;
        }

        Long newId = roleDao.getNewId();
        String strlist = "";
        for (int i = 0; i < permissionArr.length; i++) {
            strlist += permissionArr[i] + ",";
        }
        strlist = strlist.substring(0,strlist.length() - 1);
        Role_Permission role_permission = new Role_Permission(newId.intValue(), strlist);
        return roleDao.saveRolePer(role_permission);
    }

    @Override
    public Long updateRole(Role role,String[] permissionArr) {
        Long aLong = roleDao.updateRole(role);
        if (aLong < 1) {
            return aLong;
        }
        //修改permission字符串
        Role_Permission role_permission = roleDao.findRolePerByRoleId(role.getId().longValue());
        String strlist = "";
        for (int i = 0; i < permissionArr.length; i++) {
            strlist += permissionArr[i] + ",";
        }
        strlist = strlist.substring(0,strlist.length() - 1);
        role_permission.setPermissionIds(strlist);
        return roleDao.updateRolePer(role_permission);
    }

    @Override
    public Long deleteRole(Long id) {
        Long aLong = roleDao.deleteRolePre(id);
        if(aLong < 1){
            return aLong;
        }
        return roleDao.deleteRole(id);
    }

    @Override
    public List<Role> findRolesOfUserId(Long id) {
        return roleDao.findRolesOfUserId(id);
    }

    @Override
    public String findPermissionByRoleId(Long id) {
        return roleDao.findPermissionByRoleId(id);
    }

}
