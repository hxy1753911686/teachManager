package com.school.estimate.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Module implements Serializable{

	private static final long serialVersionUID = 1L;

	private Set<Role> roles = new HashSet<Role>(0);//模块与角色   多对多
	
	private String id;		//模块ID
	private String name;	//模块名称
	private String ico;
	private String url;		//模块url
	private Integer state;	//是否启用
	private String remark;	//模块描述
	private Integer orderNo;//排序号
	
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
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
}
