package com.school.estimate.action.manage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.school.estimate.action.BaseAction;
import com.school.estimate.domain.Role;
import com.school.estimate.domain.Teacher;
import com.school.estimate.domain.User;
import com.school.estimate.service.RoleService;
import com.school.estimate.service.TeacherService;
import com.school.estimate.service.UserService;


public class TeacherAction extends BaseAction implements ModelDriven<Teacher>{

	private static final long serialVersionUID = 1L;
	
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

	private Teacher model = new Teacher();
	
	@Override
	public Teacher getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	private String[] roleIds;
	
	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	private String roleStr;
	
	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}

	private List<Teacher> teacherList = new ArrayList<>();
	
	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}
	
	List allRole = new ArrayList();
	
	public List getAllRole() {
		return allRole;
	}

	public void setAllRole(List allRole) {
		this.allRole = allRole;
	}

	public String toList() throws Exception{
		String hql = "from Teacher";
		teacherList = teacherService.find(hql, Teacher.class, null);
		
		return "list";
	}
	
	public String toCreate() throws Exception{
		return "toCreate";
	}
	
	public String create() throws Exception{
		teacherService.saveOrUpdate(model);
		
		//添加新教师时,同时创建一个新用户
		User user = new User();
		//教师的手机号为登陆用户名
		user.setName(model.getTel());
		user.setNumber(model.getNumber());
		//密码为MD5加密,初始密码6个1,salt用联系方式Tel
		String password = new Md5Hash("111111",model.getTel(),2).toString();
		System.err.println(password);
		user.setPassword(password);
		
		userService.saveOrUpdate(user);
		return "tolist";
	}
	
	public String toUpdate() throws Exception{
		Teacher teacher = teacherService.get(Teacher.class, model.getId());
		ActionContext.getContext().getValueStack().push(teacher);
		return "toUpdate";
	}
	
	public String update() throws Exception{
		//先通过id查教师的联系方式和身份证号,如果改变,则需要在用户中一并修改
		Teacher teacher = teacherService.get(Teacher.class, model.getId());
		if(teacher.getTel() != model.getTel() || teacher.getNumber() != model.getNumber()){
			String hql = "from User u where u.number = " + teacher.getNumber();
			List<User> list = userService.find(hql, User.class, null);
			if(list.size() != 0 && !list.isEmpty()){
				User user = list.get(0);
				
				user.setName(model.getTel());
				user.setNumber(model.getNumber());
				userService.saveOrUpdate(user);
			}
			
		}
		//修改teacher,防止出现A different object with the same identifier value was already associated with the session 异常
		teacher.setBirthday(model.getBirthday());
		teacher.setGender(model.getGender());
		teacher.setName(model.getName());
		teacher.setNumber(model.getNumber());
		teacher.setTel(model.getTel());
		
		teacherService.saveOrUpdate(teacher);
		return "tolist";
	}
	
	public String delete() throws Exception{
		Teacher teacher = teacherService.get(Teacher.class, model.getId());
		teacherService.deleteById(Teacher.class, model.getId());
		//删除教师时要一并删除用户
		String hql = "from User u where u.number = " + teacher.getNumber();
		List<User> list = userService.find(hql, User.class, null);
		if(list.size() != 0 && !list.isEmpty()){
			User user = list.get(0);
			userService.deleteById(User.class, user.getId());
		}
		
		return "tolist";
	}

	
	public String toRole() throws Exception{
		
		//获取选择的Teacher
		Teacher teacher = teacherService.get(Teacher.class, model.getId());
		ActionContext.getContext().getValueStack().push(teacher);
		
		//获取
		String hql1 = "from Role";
		List<Role> roleList = roleService.find(hql1, Role.class, null);
		ActionContext.getContext().put("roleList", roleList);
		
		//获取Teacher所对应的user
		User user = null;
		String hql2 = "from User where number = '" + teacher.getNumber() + "'";
		List<User> userRole = userService.find(hql2, User.class, null);
		if(userRole != null && userRole.size() != 0){
			user = userRole.get(0);
		}
		
		Set<Role> roles = user.getRoles();
		StringBuilder sb = new StringBuilder();
		for (Role role : roles) {
			sb.append(role.getName());
		}
		roleStr = sb.toString();
		
		
		return "role";
	}
	
	public String updateRole() throws Exception{
		//获取操作的Teacher
		Teacher teacher = teacherService.get(Teacher.class, model.getId());
		ActionContext.getContext().getValueStack().push(teacher);
		
		//获取Teacher所对应的user
		User user = null;
		String hql2 = "from User where number = '" + teacher.getNumber() + "'";
		List<User> userRole = userService.find(hql2, User.class, null);
		if(userRole != null && userRole.size() != 0){
			user = userRole.get(0);
		}
		
		//通过roleIds获取到用户要修改的所有角色的id
		//修改user中的roles
		Set<Role> roles = new HashSet<>();
		if(roleIds != null && roleIds.length != 0){
			for (String id : roleIds) {
				Role role = roleService.get(Role.class, id);
				roles.add(role);
			}
		}
		
		user.setRoles(roles);
		userService.saveOrUpdate(user);
		return "tolist";
	}
	
}
