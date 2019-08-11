package com.school.estimate.service;

import com.school.estimate.domain.Role;
import com.school.estimate.domain.Role_Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoleService {
    List<Role> findByName(String name);

    Role findRoleById(Long id);

    List<Role> findAllRole();

    @Transactional
    Long saveRole(Role role,String[] permissionArr);

    Long updateRole(Role role,String[] permissionArr);

    Long deleteRole(Long id);

    //根据用户ID获取用户所有角色
    List<Role> findRolesOfUserId(Long id);
    List<Role_Permission> findPermissionByRoleId(@Param("id")Long id);

}
