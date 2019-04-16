package com.school.estimate.dao;

import com.school.estimate.domain.Permission;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionDao {
    List<Permission> findByName(@Param("name") String name);
    Student findPermissionById(@Param("id") Long id);
    List<Student> findAllPermission();
    Long savePermission(Permission permission);
    Long updatePermission(Permission permission);
    Long deletePermission(@Param("id") Long id);

    //根据角色ID获取角色所有权限
    List<Permission> findPermissionsOfRoleId(@Param("id") Long id);
    Permission getPermissionByUrl(@Param("url") String url);
    List<Role> getRolesByPermissionId(@Param("id")Long id);

    //根据roleName获取一级菜单
    List<Permission> findPermissByRoleName(@Param("roleName") String roleName);

}
