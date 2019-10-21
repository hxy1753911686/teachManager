package com.school.estimate.service.impl;

import com.school.estimate.dao.RoleDao;
import com.school.estimate.domain.Role;
import com.school.estimate.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

        Integer newId = role.getId();
        List<String> permissionList = Arrays.asList(permissionArr);
        return roleDao.saveRolePer(newId.longValue(),permissionList);
    }

    @Override
    public Long updateRole(Role role,String[] permissionArr) {
        Long aLong = roleDao.updateRole(role);
        if (aLong < 1) {
            return aLong;
        }

        //删除原中间表中关联的permission，并插入新的数据
        roleDao.deleteRolePre(role.getId().longValue());

        Integer updateId = role.getId();
        List<String> permissionList = Arrays.asList(permissionArr);
        return roleDao.saveRolePer(updateId.longValue(),permissionList);
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
    public List<String> findPermissionByRoleId(Long id) {
        return roleDao.findPermissionByRoleId(id);
    }

    @Override
    public List<Role> getRolesByPermissionId(Long id) {
        return roleDao.getRolesByPermissionId(id);
    }


}
