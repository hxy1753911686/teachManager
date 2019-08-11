package com.school.estimate.domain;

public class Role_Permission {
    private Integer id;
    private Integer roleId;
    private String permissionIds;

    public Role_Permission() {
    }

    public Role_Permission(Integer roleId, String permissionIds) {
        this.roleId = roleId;
        this.permissionIds = permissionIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionIds;
    }

    public void setPermissionId(String permissionIds) {
        this.permissionIds = permissionIds;
    }
}
