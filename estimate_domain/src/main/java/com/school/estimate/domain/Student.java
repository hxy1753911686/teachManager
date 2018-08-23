package com.school.estimate.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Student {
	private Integer id;
	private String name;
	private Integer gender;	//学生性别,1为男,0为女
	private String no;		//学生学号,用来登陆
	private Date birthday;	//学生出生日期
	private String number;	//学生身份证号,作为user标识
	private String background;	//学生背景(团员等)
	private String className;	//学生所在班级名称
	private String job;			//学生在所在班级的职务
	private String viliage;		//学生所在村
	private String address;		//学生现住址
	private String hobby;		//学生爱好
	private String tel;			//学生电话
	private String parentName;	//学生父母名称(其一即可)
	private String parentTel;	//学生父母联系方式(其一即可)
	private String isSingle;	//是否独生子女,1为是,0为否
	private Date startTime;		//入学时间
	
	private Set<Classes> classes = new HashSet<Classes>(0);	//班级和教师,多对多
	
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
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
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
	
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getViliage() {
		return viliage;
	}
	public void setViliage(String viliage) {
		this.viliage = viliage;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getParentTel() {
		return parentTel;
	}
	public void setParentTel(String parentTel) {
		this.parentTel = parentTel;
	}
	public String getIsSingle() {
		return isSingle;
	}
	public void setIsSingle(String isSingle) {
		this.isSingle = isSingle;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Set<Classes> getClasses() {
		return classes;
	}
	public void setClasses(Set<Classes> classes) {
		this.classes = classes;
	}
	
	
}
