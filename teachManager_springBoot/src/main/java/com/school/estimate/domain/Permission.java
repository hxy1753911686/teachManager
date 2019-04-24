package com.school.estimate.domain;

import java.io.Serializable;

public class Permission implements Serializable {
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer permissionLevel;       //three level
    private String url;
    private String icon;
    private String htmlID;
    private Integer dataID;
    private String description;

    public Permission() {
    }

    public Permission(Integer id, String name, Integer parentId, Integer permissionLevel, String url, String icon, String htmlID, Integer dataID, String description) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.permissionLevel = permissionLevel;
        this.url = url;
        this.icon = icon;
        this.htmlID = htmlID;
        this.dataID = dataID;
        this.description = description;
    }

    public Integer getDataID() {
        return dataID;
    }

    public void setDataID(Integer dataID) {
        this.dataID = dataID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(Integer permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHtmlID() {
        return htmlID;
    }

    public void setHtmlID(String htmlID) {
        this.htmlID = htmlID;
    }
}
