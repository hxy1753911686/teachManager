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

        Long aLong = roleDao.saveRole(role);
        if (aLong < 1) {
            return aLong;
        }

        Long newId = roleDao.getNewId();
        List<Role_Permission> list = new ArrayList<>();
        for (int i = 0; i < permissionArr.length; i++) {
            Role_Permission r = new Role_Permission(newId.intValue(), Integer.parseInt(permissionArr[i]));
            list.add(r);
        }
        return roleDao.saveRolePer(list);
    }

    @Override
    public Long updateRole(Role role,String[] permissionArr) {
        Long aLong = roleDao.updateRole(role);
        if (aLong < 1) {
            return aLong;
        }
        //修改permission字符串
        return roleDao.updateRole(role);
    }

    @Override
    public Long deleteRole(Long id) {
        return roleDao.deleteRole(id);
    }

    @Override
    public List<Role> findRolesOfUserId(Long id) {
        return roleDao.findRolesOfUserId(id);
    }

    @Override
    public List<Role_Permission> findPermissionByRoleId(Long id) {
        return roleDao.findPermissionByRoleId(id);
    }

}
