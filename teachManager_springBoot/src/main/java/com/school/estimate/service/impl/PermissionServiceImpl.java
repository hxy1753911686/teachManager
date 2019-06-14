package com.school.estimate.service.impl;

import com.school.estimate.dao.PermissionDao;
import com.school.estimate.domain.Permission;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.Student;
import com.school.estimate.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findByName(String name) {
        return permissionDao.findByName(name);
    }

    @Override
    public Permission findPermissionById(Long id) {
        return permissionDao.findPermissionById(id);
    }

    @Override
    public List<Permission> findAllPermission() {
        return permissionDao.findAllPermission();
    }

    @Override
    public Long savePermission(Permission permission) {

        Long aLong;
        aLong = permissionDao.savePermission(permission);
        if(aLong < 1){
            return aLong;
        }

        Long newId = permissionDao.getNewId();
        return newId;
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
    public List<Permission> getPermissionByLevel(Long parentId, Long level) {
        Map<String, Object> map = new HashMap<>();
        if (parentId != null && parentId.intValue() > 0) {
            map.put("parentId", parentId);
        }
        if (level != null && level.intValue() > 0) {
            map.put("level", level);
        }

        if (level == null && parentId == null) {
            return null;
        }

        return permissionDao.getPermissionByLevel(map);
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
    public List<Permission> findPermissByRoleName(String roleName) {
        return permissionDao.findPermissByRoleName(roleName);
    }


}
