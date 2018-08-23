package com.school.estimate.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Classes implements Serializable{
	private String id;
	private String name;
	private Integer banId;
	private Date startTime;	//开班时间
	private Integer isUse;	//是否使用,1为使用,0为未使用
	
	private Set<Student> students = new HashSet<>();	//班级和学生,多对多
//	private Set<Teacher> teachers = new HashSet<>();	//班级和教师,多对多
	
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
	public Integer getBanId() {
		return banId;
	}
	public void setBanId(Integer banId) {
		this.banId = banId;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	/*public Set<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	*/
	
}
