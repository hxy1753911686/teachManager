package com.school.estimate.domain;

import java.io.Serializable;

public class Subject implements Serializable{
	private String id;
	private String subjectName;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
}
