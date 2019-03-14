package com.school.estimate.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class Role implements Serializable {
	
	private Set<Module> modules = new HashSet<Module>();	//角色和模块,多对多
	private Set<User> users = new HashSet<User>(0);	//角色和用户,多对多
	
	private String id;
	private String name;
	private String remark;
	private Date createTime;
	private Integer orderNo;
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Set<Module> getModules() {
		return modules;
	}
	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}
	
	
	
}
