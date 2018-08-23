package com.school.estimate.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Teacher implements Serializable{

	private static final long serialVersionUID = 1L;
	
//	private List<Role> roles = new ArrayList<Role>(0);	//角色和教师,多对多
//	private Set<Classes> classes = new HashSet<Classes>(0);	//班级和教师,多对多
	
	private Integer id;			//教师id
	private String name;	//教师名称
	private String tel;		//教师联系方式
	private Date birthday; 	//教师生日
	private String number; 	//教师身份证号
	private Integer gender;		//教师性别,1为男,2为女
	
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	
	/*
	public Set<Classes> getClasses() {
		return classes;
	}
	public void setClasses(Set<Classes> classes) {
		this.classes = classes;
	}*/
	

}
