package com.school.estimate.dao;

import com.school.estimate.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {
    List<Role> findByName(@Param("name") String name);
    Role findRoleById(@Param("id") Long id);
    List<Role> findAllRole();
    Long saveRole(Role role);
    Long updateRole(Role role);
    Long deleteRole(@Param("id") Long id);

    //根据用户ID获取用户所有角色
    List<Role> findRolesOfUserId(@Param("id") Long id);
}
