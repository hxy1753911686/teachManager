package com.school.estimate.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User implements Serializable{

	private Set<Role> roles = new HashSet<Role>();	//角色和用户,多对多
	
	private String id;			//用户ID
	private String name;		//用户名
	private String password;	//登陆密码
	private String number;		//身份证号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [roles=" + roles + ", id=" + id + ", name=" + name + ", password=" + password + ", number="
				+ number + "]";
	}
	
	
	
}
