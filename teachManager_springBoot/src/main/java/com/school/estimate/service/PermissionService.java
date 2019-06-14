package com.school.estimate.service;

import com.school.estimate.domain.Permission;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface PermissionService {
    List<Permission> findByName(String name);

    Permission findPermissionById(Long id);

    List<Permission> findAllPermission();

    @Transactional
    Long savePermission(Permission permission);

    Long updatePermission(Permission permission);

    Long deletePermission(Long id);

    List<Permission> getPermissionByLevel(Long parentId,Long level);



    //根据角色ID获取角色所有权限
    List<Permission> findPermissionsOfRoleId(Long id);

    Permission getPermissionByUrl(String url);

    List<Role> getRolesByPermissionId(Long id);

    //根据roleName获取
    List<Permission> findPermissByRoleName(String roleName);

}
