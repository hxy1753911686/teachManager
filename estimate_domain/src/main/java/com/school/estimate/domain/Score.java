package com.school.estimate.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Score implements Serializable{
	private String id;
	private String estimateId;	//评价Id	
	private Integer studentId;	//评价学生的Id
	private Integer term;	//学期
	private Double grade;	//评价分数
	private Integer teacherId;	//是哪位老师评价的
	private Date time;		//评价时间
	private String sorbName;	//是班主任评价或者科目名称
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEstimateId() {
		return estimateId;
	}
	public void setEstimateId(String estimateId) {
		this.estimateId = estimateId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
	}
	public Double getGrade() {
		return grade;
	}
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getSorbName() {
		return sorbName;
	}
	public void setSorbName(String sorbName) {
		this.sorbName = sorbName;
	}
	@Override
	public String toString() {
		return "Score [id=" + id + ", estimateId=" + estimateId + ", studentId=" + studentId + ", term=" + term
				+ ", grade=" + grade + ", teacherId=" + teacherId + ", time=" + time + ", sorbName=" + sorbName + "]";
	}
	
	
}
