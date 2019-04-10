package com.school.estimate.service;

import com.school.estimate.domain.Permission;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.Student;

import java.util.List;

public interface PermissionService {
    List<Permission> findByName(String name);

    Student findPermissionById(Long id);

    List<Student> findAllPermission();

    Long savePermission(Permission permission);

    Long updatePermission(Permission permission);

    Long deletePermission(Long id);

    //根据角色ID获取角色所有权限
    List<Permission> findPermissionsOfRoleId(Long id);

    Permission getPermissionByUrl(String url);

    List<Role> getRolesByPermissionId(Long id);

    //根据roleName获取
    List<Permission> findFirstPermissByRoleName(String roleName);
}
