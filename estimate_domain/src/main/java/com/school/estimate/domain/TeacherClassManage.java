package com.school.estimate.domain;

import java.io.Serializable;

public class TeacherClassManage implements Serializable{
	private String id;
	private String classId;
	private String subjectId;
	private Integer teacherId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	@Override
	public String toString() {
		return "TeacherClassManage [id=" + id + ", classId=" + classId + ", subjectId=" + subjectId + ", teacherId="
				+ teacherId + "]";
	}
	
	
}
