package com.school.estimate.action.manage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.school.estimate.action.BaseAction;
import com.school.estimate.domain.Classes;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.Student;
import com.school.estimate.domain.User;
import com.school.estimate.service.ClassesService;
import com.school.estimate.service.RoleService;
import com.school.estimate.service.StudentService;
import com.school.estimate.service.UserService;

public class StudentAction extends BaseAction implements ModelDriven<Student>{

	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private RoleService roleService;
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	private ClassesService classesService;
	
	
	public ClassesService getClassesService() {
		return classesService;
	}

	public void setClassesService(ClassesService classesService) {
		this.classesService = classesService;
	}

	private StudentService studentService;

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	private Student model = new Student();
	
	@Override
	public Student getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	public String toView() throws Exception{
		//通过id查找相应的学生
		Student student = studentService.get(Student.class, model.getId());
		ActionContext.getContext().getValueStack().push(student);
		return "toView";
	}
	
	private String classesId;
	
	public String getClassesId() {
		return classesId;
	}

	public void setClassesId(String classesId) {
		this.classesId = classesId;
	}

	public String toCreate() throws Exception{
		//通过classesId找到对应班级
		
		Classes classes = classesService.get(Classes.class, classesId);
		ActionContext.getContext().getValueStack().push(classes);
		return "toCreate";
	}
	
	public String create() throws Exception{
		//为id为classesId的班级添加新学生
		Classes classes = classesService.get(Classes.class, classesId);
		model.setStartTime(classes.getStartTime());
		model.setClassName(classes.getName());
		studentService.saveOrUpdate(model);
		
		String hql = "from Student where no = '" + model.getNo() +"'";
		List<Student> studentList = studentService.find(hql, Student.class, null);
		Student student = studentList.get(0);
		
		Set<Student> students = classes.getStudents();
		students.add(student);
		
		classes.setStudents(students);
		classesService.saveOrUpdate(classes);
		
		//添加学生后,同步添加user,并赋予user的角色为role
		User user = new User();
		user.setName(model.getNo());
		user.setNumber(model.getNumber());
		String password = new Md5Hash("111111",model.getNo(),2).toString();
		user.setPassword(password);
		
		Role role = roleService.get(Role.class, "657a4847b2667g69");
		Set roles = new HashSet();
		roles.add(role);
		user.setRoles(roles);
		
		userService.saveOrUpdate(user);
		
		return "toClass";
	}
	
	public String delete() throws Exception{
		studentService.deleteById(Student.class, model.getId());
		return "toClass";
	}
	
	public String toUpdate() throws Exception{
		Classes classes = classesService.get(Classes.class, classesId);
		classesId = classes.getId();
		ActionContext.getContext().put("classesName", classes.getName());
		
		Student student = studentService.get(Student.class, model.getId());
		ActionContext.getContext().getValueStack().push(student);
		
		return "toUpdate";
	}
	
	public String update() throws Exception{
		Student student = studentService.get(Student.class, model.getId());
		
		student.setName(model.getName());
		student.setNo(model.getNo());
		student.setTel(model.getTel());
		student.setBirthday(model.getBirthday());
		student.setNumber(model.getNumber());
		student.setGender(model.getGender());
		student.setBackground(model.getBackground());
		student.setJob(model.getJob());
		student.setViliage(model.getViliage());
		student.setAddress(model.getAddress());
		student.setParentName(model.getParentName());
		student.setParentTel(model.getParentTel());
		student.setIsSingle(model.getIsSingle());
		
		studentService.saveOrUpdate(student);
		
		return "toClass";
	}
	
}
