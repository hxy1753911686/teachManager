package com.school.estimate.action.manage;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.school.estimate.action.BaseAction;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.Student;
import com.school.estimate.domain.Teacher;
import com.school.estimate.domain.User;
import com.school.estimate.service.RoleService;
import com.school.estimate.service.StudentService;
import com.school.estimate.service.TeacherService;
import com.school.estimate.service.UserService;

public class UserAction extends BaseAction implements ModelDriven<User>{

	private StudentService studentService;
	
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	private TeacherService teacherService;
	
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private RoleService roleService;
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	private String roleStr;
	
	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}

	private User model = new User();
	
	public User getModel() {
		return model;
	}
	
	
	public String update() throws Exception{
		//通过传来的id获取user
		User user = userService.get(User.class, model.getId());
		
		//通过user获取对应teacher
		Teacher teacher = null;
		String hql = "from Teacher where number = " + user.getNumber();
		List<Teacher> teacherList = teacherService.find(hql, Teacher.class, null);
		if(teacherList != null && teacherList.size() != 0){
			teacher = teacherList.get(0);
		}
		ActionContext.getContext().getValueStack().push(teacher);
		
		
		String hql1 = "from Role";
		List<Role> roleList = roleService.find(hql1, Role.class, null);
		ActionContext.getContext().put("roleList", roleList);
		
		Set<Role> roles = user.getRoles();
		StringBuilder sb = new StringBuilder();
		for (Role role : roles) {
			sb.append(role.getName());
		}
		roleStr = sb.toString();
		
		
		return "toRole";
	}
	
	public String toUserSetting() throws Exception{
		//获取当前session的用户
		User user = (User)session.get("_CURRENT_USER");
		String number = user.getNumber();
		
		String hql = "from Teacher where number = " + number;
		List<Teacher> teacherList = teacherService.find(hql, Teacher.class, null);
		Teacher teacher = null;
		if(teacherList != null && teacherList.size() != 0){
			teacher = teacherList.get(0);
		}
		
		if(teacher != null){
			ActionContext.getContext().getValueStack().push(teacher);
		}
		
		String hql1 = "from Student where number = " + number;
		List<Student> studentList = studentService.find(hql1, Student.class, null);
		Student student = null;
		if(studentList != null && studentList.size() != 0){
			student = studentList.get(0);
		}
		
		if(student != null){
			ActionContext.getContext().getValueStack().push(student);
		}
		
		
		return "toUserSetting";
	}

	public String saveUser() throws Exception{
		//获取当前session的用户
		User user = (User)session.get("_CURRENT_USER");
		String number = user.getNumber();
		
		String newPassword = new Md5Hash(model.getPassword(),user.getName(),2).toString();
		
		user.setPassword(newPassword);
		userService.saveOrUpdate(user);
		
		return "toIndex";
	}
}
