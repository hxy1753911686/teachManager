package com.school.estimate.domain;

import java.io.Serializable;
import java.sql.Date;

public class Student implements Serializable {

    private Integer id;
    private String name;
    private Integer gender; //性别,1为男，0为女
    private String accessId;

    //java.sql.Date只储存到日，适用
    private Date birth;
    private Boolean is_out;

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Boolean getIs_out() {
        return is_out;
    }

    public void setIs_out(Boolean is_out) {
        this.is_out = is_out;
    }
}
