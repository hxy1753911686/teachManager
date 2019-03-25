package com.school.estimate.service.impl;

import com.school.estimate.dao.RoleDao;
import com.school.estimate.domain.Role;
import com.school.estimate.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Long saveRole(Role role) {
        return roleDao.saveRole(role);
    }

    @Override
    public Long updateRole(Role role) {
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
}
