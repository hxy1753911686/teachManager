package com.school.estimate.domain;

public class User_Role {
    private Integer id;
    private Integer userId;
    private String roleIds;

    public User_Role() {
    }

    public User_Role(Integer id, Integer userId, String roleIds) {
        this.id = id;
        this.userId = userId;
        this.roleIds = roleIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
