package com.school.estimate.service.impl;

import com.school.estimate.dao.PermissionDao;
import com.school.estimate.domain.Permission;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.Student;
import com.school.estimate.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findByName(String name) {
        return permissionDao.findByName(name);
    }

    @Override
    public Student findPermissionById(Long id) {
        return permissionDao.findPermissionById(id);
    }

    @Override
    public List<Student> findAllPermission() {
        return permissionDao.findAllPermission();
    }

    @Override
    public Long savePermission(Permission permission) {
        return permissionDao.savePermission(permission);
    }

    @Override
    public Long updatePermission(Permission permission) {
        return permissionDao.updatePermission(permission);
    }

    @Override
    public Long deletePermission(Long id) {
        return permissionDao.deletePermission(id);
    }

    @Override
    public List<Permission> findPermissionsOfRoleId(Long id) {
        return permissionDao.findPermissionsOfRoleId(id);
    }

    @Override
    public Permission getPermissionByUrl(String url) {
        return permissionDao.getPermissionByUrl(url);
    }

    @Override
    public List<Role> getRolesByPermissionId(Long id) {
        return permissionDao.getRolesByPermissionId(id);
    }

    @Override
    public List<Permission> findFirstPermissByRoleName(String roleName) {
        return permissionDao.findFirstPermissByRoleName(roleName);
    }
}
